package com.restful.aiagent.exception;

public class BatchRecordsNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BatchRecordsNotFoundException(String message) {
        super(message);
    }
}