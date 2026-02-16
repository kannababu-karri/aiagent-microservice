package com.restful.aiagent.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.aiagent.entities.ComplianceResults;

@Repository
public interface ComplianceResultsRepository extends JpaRepository<ComplianceResults, Long> {
	
	Page<ComplianceResults> findByBatchRecords_Id(Long batchId, Pageable pageable);
	
	Page<ComplianceResults> findByBatchRecords_IdIn(List<Long> batchIds, Pageable pageable);
	
}
