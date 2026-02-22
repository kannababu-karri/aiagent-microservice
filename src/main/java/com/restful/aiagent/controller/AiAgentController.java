package com.restful.aiagent.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful.aiagent.entities.AuditLogs;
import com.restful.aiagent.entities.BatchRecords;
import com.restful.aiagent.entities.ComplianceResults;
import com.restful.aiagent.entities.ComplianceResultsDto;
import com.restful.aiagent.entities.PageResponseDto;
import com.restful.aiagent.service.AuditLogsService;
import com.restful.aiagent.service.BatchRecordsService;
import com.restful.aiagent.service.ComplianceResultsService;
import com.restful.aiagent.utils.AiReportAnalyzer;
import com.restful.aiagent.utils.ILConstants;

@RestController
@RequestMapping("/api/aiagent")
@CrossOrigin(origins = {ILConstants.ANGULAR_URL_DEV, ILConstants.ANGULAR_URL_PROD})
public class AiAgentController {
	
	private static final Logger _LOGGER = LoggerFactory.getLogger(AiAgentController.class);
	
	private final BatchRecordsService batchRecordsService;
	private final ComplianceResultsService complianceResultsService;
	private final AuditLogsService auditLogsService;
    private final ObjectMapper mapper;
	
	private final RestTemplate restTemplate;
	
	public AiAgentController(RestTemplate restTemplate,
			BatchRecordsService batchRecordsService, 
			ComplianceResultsService complianceResultsService, 
			AuditLogsService auditLogsService, 
			ObjectMapper mapper) {
		this.restTemplate = restTemplate;
		this.batchRecordsService = batchRecordsService;
		this.complianceResultsService = complianceResultsService;
		this.auditLogsService = auditLogsService;
		this.mapper = mapper;
	}
	
	@PostMapping(
		    value = "/upload-pdf",
		    consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
		    produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<?> uploadPdf(@RequestPart("file") MultipartFile file) throws Exception {
		
		_LOGGER.info(">>> Inside uploadPdf <<<");

	    if (file.isEmpty()) {
	        return ResponseEntity.badRequest().body("File is empty");
	    }

	    // Call Python API
	    String result = callPythonService(file, Long.valueOf(0));

	    Map<String, Object> response = new HashMap<>();
	    response.put("fileName", file.getOriginalFilename());
	    response.put("report", result);

	    return ResponseEntity.ok(response);
	}
	
	@PostMapping(
	        value = "/upload-batch-process-pdf",
	        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
	    )
	public ResponseEntity<?> uploadBatchProcessPdf( @RequestPart("file") MultipartFile file,
											        @RequestPart("batchRecords") String batchJson,
											        @RequestHeader("Authorization") String token) throws Exception {
		
		_LOGGER.info(">>> Inside uploadBatchProcessPdf <<<");
		
		_LOGGER.info(">>> Inside file <<<"+file);
		_LOGGER.info(">>> Inside batchJson <<<"+batchJson);
		_LOGGER.info(">>> Inside token <<<"+token);

	    if (file.isEmpty()) {
	        return ResponseEntity.badRequest().body("File is empty");
	    }

        // 1. Convert JSON → Object
        BatchRecords batch = mapper.readValue(batchJson, BatchRecords.class);
        batch.setUploadDate(LocalDateTime.now());

        // 2. INSERT or UPDATE
        BatchRecords savedBatch = batchRecordsService.saveOrUpdate(batch);

        // 3. Call Python AI
        String aiReport = callPythonService(file, savedBatch.getId());

        // 4. Save Compliance Result
        ComplianceResults complianceResults = new ComplianceResults();
        complianceResults.setBatchRecords(savedBatch);
        complianceResults.setFindings(aiReport);
        complianceResults.setReviewedAt(LocalDateTime.now());
        
        String aiReportText = aiReport; // your JSON "report" field
        
        int score = AiReportAnalyzer.calculateScore(aiReportText);
        String riskLevel = AiReportAnalyzer.calculateRiskLevel(score);

        //then save like above
        complianceResults.setScore(score);
        complianceResults.setRiskLevel(riskLevel);
        complianceResultsService.saveOrUpdate(complianceResults);

        // 5. Audit Log
        AuditLogs auditLogs = new AuditLogs();
        auditLogs.setUserId(savedBatch.getUploadedBy());
        auditLogs.setAction("Processed batch " + savedBatch.getBatchNo());
        auditLogs.setCreatedAt(LocalDateTime.now());
        auditLogsService.saveOrUpdate(auditLogs);

        // 6. Response
        Map<String, Object> result = new HashMap<>();
        result.put("batchId", savedBatch.getId());
        result.put("report", aiReport);

        return ResponseEntity.ok(result);
	}
	
	@GetMapping("/angular-batch-records")
    public ResponseEntity<PageResponseDto<ComplianceResultsDto>> getAngularBatchRecords(
    		@PageableDefault(size = 5, sort = "productName")
    	    Pageable pageable
    		) {
    	_LOGGER.info(">>> Inside getAngularBatchRecords. <<<");
    	
    	Page<BatchRecords> page = batchRecordsService.findAllBatchRecords(pageable);
    	
    	List<ComplianceResultsDto> complianceResultsDto = new ArrayList<ComplianceResultsDto>();
    	
    	if(page != null && page.getContent() != null && page.getContent().size() > 0) {
    		
    		Pageable unsortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.unsorted());
    		
    		_LOGGER.info(">>> Inside page.getContent().size. <<<"+page.getContent().size());
    		
		    List<Long> batchIds = page.getContent().stream()
		            .map(BatchRecords::getId)
		            .toList();
		    
		    _LOGGER.info(">>> Inside batchIds <<<"+batchIds.size());
		    
		    Page<ComplianceResultsDto> pageComplianceResults = complianceResultsService.getComplianceResultsForBatches(batchIds, unsortedPageable);
		    
		    complianceResultsDto = pageComplianceResults.getContent();
		    
		    _LOGGER.info(">>> Inside complianceResultsDto <<<"+complianceResultsDto.size());
	    }
    	
    	PageResponseDto<ComplianceResultsDto> dto = new PageResponseDto<>();
    	
    	if(page != null) {
	    	dto.setContent(complianceResultsDto);
	        dto.setTotalPages(page.getTotalPages());
	        dto.setTotalElements(page.getTotalElements());
	        dto.setPageNumber(page.getNumber());
	        dto.setPageSize(page.getSize());
    	}

        return ResponseEntity.ok(dto);
    	
    }
	
	@GetMapping("/batch-records")
    public ResponseEntity<PageResponseDto<BatchRecords>> getBatchRecords(
    		@PageableDefault(size = 5, sort = "productName")
    	    Pageable pageable
    		) {
    	_LOGGER.info(">>> Inside getBatchRecords. <<<");
    	
    	Page<BatchRecords> page = batchRecordsService.findAllBatchRecords(pageable);
    	
    	PageResponseDto<BatchRecords> dto = new PageResponseDto<>();
    	
    	if(page != null) {
	    	dto.setContent(page.getContent());
	        dto.setTotalPages(page.getTotalPages());
	        dto.setTotalElements(page.getTotalElements());
	        dto.setPageNumber(page.getNumber());
	        dto.setPageSize(page.getSize());
    	}

        return ResponseEntity.ok(dto);
    	
    }
	
	@GetMapping("/compliance-results")
	public ResponseEntity<PageResponseDto<ComplianceResultsDto>> getComplianceResults(
	        @PageableDefault(size = 5, sort = "riskLevel") Pageable pageable,
	        @RequestParam("batchRecordIds") List<Long> batchIds) {

	    _LOGGER.info(">>> Inside getComplianceResults. <<<");

	    Page<ComplianceResultsDto> page =
	            complianceResultsService.getComplianceResultsForBatches(batchIds, pageable);
	    
	    //_LOGGER.info(">>> Inside page.getContent().size(). <<<"+page.getContent().size());
	    //for (Iterator<ComplianceResultsDto> iterator = page.getContent().iterator(); iterator.hasNext();) {
	    //	ComplianceResultsDto complianceResultsDto = (ComplianceResultsDto) iterator.next();
	    //	_LOGGER.info(">>> Inside complianceResultsDto.getFindings(). <<<"+complianceResultsDto.getFindings());	
		//}

	    PageResponseDto<ComplianceResultsDto> dto =
	            new PageResponseDto<>();

	    if (page != null) {
	        dto.setContent(page.getContent());
	        dto.setTotalPages(page.getTotalPages());
	        dto.setTotalElements(page.getTotalElements());
	        dto.setPageNumber(page.getNumber());
	        dto.setPageSize(page.getSize());
	    }

	    return ResponseEntity.ok(dto);
	}
    
    /**
     * Retrieve python service
     * 
     * @param file
     * @return
     * @throws Exception
     */
    private String callPythonService(MultipartFile file, Long batchId) throws Exception {   
    	
    	_LOGGER.info(">>> Inside callPythonService <<<");
        
        //File resource
        /*InputStreamResource resource =
                new InputStreamResource(file.getInputStream()) {

            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }

            @Override
            public long contentLength() {
                return file.getSize();
            }
        };*/
    	
    	// Save file to temp location (optional)
        File tempFile = File.createTempFile("aiagent-", ".pdf");
        file.transferTo(tempFile); // Streams to disk
        
        // Multipart body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(tempFile));  // stream large file
        body.add("batchId", String.valueOf(batchId));        // pass batchId

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(body, headers);

        // Call FastAPI
        ResponseEntity<String> response =
                restTemplate.postForEntity(
                		ILConstants.AI_PYTHON_URL,
                        requestEntity,
                        String.class
                );


        return response.getBody();
    }
}
