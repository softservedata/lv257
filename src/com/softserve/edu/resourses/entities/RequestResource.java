package com.softserve.edu.resourses.entities;

import java.sql.Date;

public class RequestResource {
	private long id;
    private String requestedResource;
    private User register;
    private User resourcesAdmin;
    private String documentLink;
    private Status status;
    private Date date;
    private String infoAboutNewResourse;
    
	public RequestResource() {
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
	public String getRequestedResource() {
		return requestedResource;
	}
	public void setRequestedResource(String requestedResource) {
		this.requestedResource = requestedResource;
	}
	
	public String getInfoAboutNewResourse() {
		return infoAboutNewResourse;
	}
	public void setInfoAboutNewResourse(String infoAboutNewResourse) {
		this.infoAboutNewResourse = infoAboutNewResourse;
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
