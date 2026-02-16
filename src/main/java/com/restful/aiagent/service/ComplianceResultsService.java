package com.restful.aiagent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restful.aiagent.entities.BatchRecords;
import com.restful.aiagent.entities.ComplianceResults;
import com.restful.aiagent.entities.ComplianceResultsDto;
import com.restful.aiagent.repository.ComplianceResultsRepository;

@Service
public class ComplianceResultsService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ComplianceResultsService.class);
	
	@Autowired
    private ComplianceResultsRepository complianceResultsRepository;

	@Transactional
    public ComplianceResults saveOrUpdate(ComplianceResults ComplianceResults) throws ServiceException {
    	try {
    		return complianceResultsRepository.save(ComplianceResults);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in saveOrUpdate."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in saveOrUpdate."+exp.toString());
		}
    }

    public Page<ComplianceResults> findAllComplianceResults(Pageable pageable) throws ServiceException {
    	try {
    		return complianceResultsRepository.findAll(pageable);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findAllComplianceResults."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findAllComplianceResults."+exp.toString());
		}
    }
    
	/**
	 * Retrieve by id
	 * @param id
	 * @return
	 */
	public Optional<ComplianceResults> findById(Long id) throws ServiceException {
		try {
	        return complianceResultsRepository.findById(id);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findById."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findById."+exp.toString());
		}
    }
	
	@Transactional
	public void deleteById(Long id) throws ServiceException {
		try {
			if (!complianceResultsRepository.existsById(id)) {
		        throw new ServiceException("ComplianceResults not found with id: " + id);
		    }
			complianceResultsRepository.deleteById(id);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in deleteById."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in deleteById."+exp.toString());
		}
	}
	
    public Page<ComplianceResults> findByBatchRecords_Id(Long batchId, Pageable pageable) throws ServiceException {
    	try {
    		return complianceResultsRepository.findByBatchRecords_Id(batchId, pageable);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByBatchId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByBatchId."+exp.toString());
		}
    }
    
    public Page<ComplianceResultsDto> getComplianceResultsForBatches(
            List<Long> batchIds,
            Pageable pageable) {

        Page<ComplianceResults> page =
                complianceResultsRepository.findByBatchRecords_IdIn(batchIds, pageable);

        return page.map(this::mapToDto);   // BEST WAY
    }
    
    /**
     * Returns a map: batchId -> ComplianceResult (latest per batch)
     */
    public Map<Long, ComplianceResults> getComplianceResultsForBatches1(List<Long> batchIds, Pageable pageable) {

        //Fetch all compliance results for these batches
        List<ComplianceResults> results = null;//complianceResultsRepository.findByBatchRecords_IdIn(batchIds, pageable);

        // 3️⃣ Convert to Map<batchId, latest ComplianceResults>
        Map<Long, ComplianceResults> complianceMap = new HashMap<>();

        for (ComplianceResults cr : results) {
            Long batchId = cr.getBatchRecords().getId();

            // Keep latest reviewedAt if multiple per batch
            if (!complianceMap.containsKey(batchId)
                    || cr.getReviewedAt().isAfter(complianceMap.get(batchId).getReviewedAt())) {
                complianceMap.put(batchId, cr);
            }
        }

        return complianceMap;
    }
    
    private ComplianceResultsDto mapToDto(ComplianceResults entity) {

        ComplianceResultsDto dto = new ComplianceResultsDto();

        dto.setId(entity.getId());
        dto.setScore(entity.getScore());
        dto.setRiskLevel(entity.getRiskLevel());
        dto.setFindings(entity.getFindings());
        dto.setReviewedAt(entity.getReviewedAt());

        BatchRecords batch = entity.getBatchRecords();

        if (batch != null) {
            dto.setBatchId(batch.getId());
            dto.setBatchNo(batch.getBatchNo());
            dto.setProductName(batch.getProductName());
        }

        return dto;
    }
}
