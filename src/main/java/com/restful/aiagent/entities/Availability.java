package com.restful.aiagent.entities;

import java.util.List;

public class Availability {
	private String day;
    private List<String> slots;
    
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public List<String> getSlots() {
		return slots;
	}
	public void setSlots(List<String> slots) {
		this.slots = slots;
	}
}
