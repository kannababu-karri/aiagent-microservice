package com.restful.aiagent.exception;

public class AuditLogsNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuditLogsNotFoundException(String message) {
        super(message);
    }
}