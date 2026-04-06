package com.restful.aiagent.entities;

public class DoctorsDTO {

    private String doctorId;
    private String name;
    private String specializationName;

    public DoctorsDTO(String doctorId, String name, String specializationName) {
        this.doctorId = doctorId;
        this.name = name;
        this.specializationName = specializationName;
    }

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}
}
