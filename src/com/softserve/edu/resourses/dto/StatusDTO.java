package com.softserve.edu.resourses.dto;

public class StatusDTO {

	private String currentStatus;

	public StatusDTO(String currentStatus) {
		super();
		this.currentStatus = currentStatus;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	
	
}
