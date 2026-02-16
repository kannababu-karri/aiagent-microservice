package com.restful.aiagent.service;

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
import com.restful.aiagent.repository.BatchRecordsRepository;

@Service
public class BatchRecordsService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(BatchRecordsService.class);
	
	@Autowired
    private BatchRecordsRepository batchRecordsRepository;

	@Transactional
    public BatchRecords saveOrUpdate(BatchRecords BatchRecords) throws ServiceException {
    	try {
    		return batchRecordsRepository.save(BatchRecords);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in saveOrUpdate."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in saveOrUpdate."+exp.toString());
		}
    }

    public Page<BatchRecords> findAllBatchRecords(Pageable pageable) throws ServiceException {
    	try {
    		return batchRecordsRepository.findAll(pageable);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findAllBatchRecords."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findAllBatchRecords."+exp.toString());
		}
    }
    
	/**
	 * Retrieve by manufacturer id
	 * @param manufacturerId
	 * @return
	 */
	public Optional<BatchRecords> findById(Long id) throws ServiceException {
		try {
	        return batchRecordsRepository.findById(id);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findById."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findById."+exp.toString());
		}
    }
	
	@Transactional
	public void deleteById(Long id) throws ServiceException {
		try {
			if (!batchRecordsRepository.existsById(id)) {
		        throw new ServiceException("BatchRecords not found with id: " + id);
		    }
			batchRecordsRepository.deleteById(id);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in deleteById."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in deleteById."+exp.toString());
		}
	}
	
   public Page<BatchRecords> findByBatchNo(String batchNo, Pageable pageable) throws ServiceException {
    	try {
    		return batchRecordsRepository.findByBatchNoIgnoreCase(batchNo, pageable);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByBatchNo."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByBatchNo."+exp.toString());
		}
    }
	
    public Page<BatchRecords> findByProductName(String productName, Pageable pageable) throws ServiceException {
    	try {
    		return batchRecordsRepository.findByProductNameIgnoreCase(productName, pageable);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByProductName."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByProductName."+exp.toString());
		}
    }
}
