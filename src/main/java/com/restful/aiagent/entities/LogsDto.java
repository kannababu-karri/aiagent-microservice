package com.restful.aiagent.entities;

import java.util.List;

public class LogsDto {
	private String severity;
    private String affectedComponent;
    private List<String> rootCauses;
    private List<String> solutions;
    private List<String> fixCommands;
    private double confidenceScore;
    
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getAffectedComponent() {
		return affectedComponent;
	}
	public void setAffectedComponent(String affectedComponent) {
		this.affectedComponent = affectedComponent;
	}
	public List<String> getRootCauses() {
		return rootCauses;
	}
	public void setRootCauses(List<String> rootCauses) {
		this.rootCauses = rootCauses;
	}
	public List<String> getSolutions() {
		return solutions;
	}
	public void setSolutions(List<String> solutions) {
		this.solutions = solutions;
	}
	public List<String> getFixCommands() {
		return fixCommands;
	}
	public void setFixCommands(List<String> fixCommands) {
		this.fixCommands = fixCommands;
	}
	public double getConfidenceScore() {
		return confidenceScore;
	}
	public void setConfidenceScore(double confidenceScore) {
		this.confidenceScore = confidenceScore;
	}
	
    
}
