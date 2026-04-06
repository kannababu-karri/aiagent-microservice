package com.restful.aiagent.entities;

public class AppointmentsDto {
	private String message;
	private String patientId;
    private int step;
    private String selectedDate;
    private int specializationId;
    private String doctorId;
    private int slotId;
    
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getPatientId() {
		return patientId;
	}
	
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public int getStep() {
		return step;
	}
	
	public void setStep(int step) {
		this.step = step;
	}
	
	public String getSelectedDate() {
		return selectedDate;
	}
	
	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}
	
	public int getSpecializationId() {
		return specializationId;
	}
	
	public void setSpecializationId(int specializationId) {
		this.specializationId = specializationId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}
	
	@Override
	public String toString() {
	    return "AppointmentsDto {" +
	            "patientId='" + patientId + '\'' +
	            ", step=" + step +
	            ", selectedDate='" + selectedDate + '\'' +
	            ", specializationId=" + specializationId +
	            ", doctorId='" + doctorId + '\'' +
	            ", slotId=" + slotId +
	            ", message='" + message + '\'' +
	            '}';
	}
}
