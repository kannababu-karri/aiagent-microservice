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

import com.restful.aiagent.entities.AuditLogs;
import com.restful.aiagent.repository.AuditLogsRepository;

@Service
public class AuditLogsService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AuditLogsService.class);
	
	@Autowired
    private AuditLogsRepository auditLogsRepository;

	@Transactional
    public AuditLogs saveOrUpdate(AuditLogs auditLogs) throws ServiceException {
    	try {
    		return auditLogsRepository.save(auditLogs);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in saveOrUpdate."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in saveOrUpdate."+exp.toString());
		}
    }

    public Page<AuditLogs> findAllAuditLogs(Pageable pageable) throws ServiceException {
    	try {
    		return auditLogsRepository.findAll(pageable);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findAllAuditLogs."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findAllAuditLogs."+exp.toString());
		}
    }
    
	/**
	 * Retrieve by manufacturer id
	 * @param manufacturerId
	 * @return
	 */
	public Optional<AuditLogs> findById(Long id) throws ServiceException {
		try {
	        return auditLogsRepository.findById(id);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findById."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findById."+exp.toString());
		}
    }
	
	@Transactional
	public void deleteById(Long id) throws ServiceException {
		try {
			if (!auditLogsRepository.existsById(id)) {
		        throw new ServiceException("AuditLogs not found with id: " + id);
		    }
			auditLogsRepository.deleteById(id);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in deleteById."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in deleteById."+exp.toString());
		}
	}
	
    public Page<AuditLogs> findByUserId(String userId, Pageable pageable) throws ServiceException {
    	try {
    		return auditLogsRepository.findByUserIdIgnoreCase(userId, pageable);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByUserId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByUserId."+exp.toString());
		}
    }
}
