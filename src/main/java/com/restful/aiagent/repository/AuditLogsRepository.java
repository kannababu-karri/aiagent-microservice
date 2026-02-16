package com.restful.aiagent.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.aiagent.entities.AuditLogs;

@Repository
public interface AuditLogsRepository extends JpaRepository<AuditLogs, Long> {
	
	 Page<AuditLogs> findByUserIdIgnoreCase(String userId, Pageable pageable);

}
