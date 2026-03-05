package com.restful.aiagent.entities;

public class ResumeDto {
	private boolean match;
	private String reason;
	
	public boolean isMatch() {
		return match;
	}
	public void setMatch(boolean match) {
		this.match = match;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

}
