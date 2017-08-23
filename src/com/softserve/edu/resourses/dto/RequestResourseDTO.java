package com.softserve.edu.resourses.dto;

import java.sql.Date;

import com.softserve.edu.resourses.entities.Status;
import com.softserve.edu.resourses.entities.User;

public class RequestResourseDTO {
	private long id;
    private String requestedCategory;
    private User register;
    private User resourcesAdmin;
    private String documentLink;
    private Status status;
    private Date date;
    private String infoAboutNewResourse;
    
    
	public RequestResourseDTO(long id, String requestedCategory, User register, User resourcesAdmin,
			String documentLink, Status status, Date date, String infoAboutNewResourse) {
		super();
		this.infoAboutNewResourse =infoAboutNewResourse;
		this.date = date;
		this.id = id;
		this.requestedCategory = requestedCategory;
		this.register = register;
		this.resourcesAdmin = resourcesAdmin;
		this.documentLink = documentLink;
		this.status = status;
	}
	public String getInfoAboutNewResourse() {
		return infoAboutNewResourse;
	}
	public void setInfoAboutNewResourse(String infoAboutNewResourse) {
		this.infoAboutNewResourse = infoAboutNewResourse;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRequestedCategory() {
		return requestedCategory;
	}
	public void setRequestedCategory(String requestedCategory) {
		this.requestedCategory = requestedCategory;
	}
	public User getRegister() {
		return register;
	}
	public void setRegister(User register) {
		this.register = register;
	}
	public User getResourcesAdmin() {
		return resourcesAdmin;
	}
	public void setResourcesAdmin(User resourcesAdmin) {
		this.resourcesAdmin = resourcesAdmin;
	}
	public String getDocumentLink() {
		return documentLink;
	}
	public void setDocumentLink(String documentLink) {
		this.documentLink = documentLink;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
