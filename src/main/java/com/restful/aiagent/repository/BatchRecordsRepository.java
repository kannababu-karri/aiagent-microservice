package com.restful.aiagent.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.aiagent.entities.BatchRecords;

@Repository
public interface BatchRecordsRepository extends JpaRepository<BatchRecords, Long> {
	
	Page<BatchRecords> findByBatchNoIgnoreCase(String batchNo, Pageable pageable);
	
	Page<BatchRecords> findByProductNameIgnoreCase(String productName, Pageable pageable);
}
