package com.softserve.edu.resourses.entities;

public class Document {

	private String name;
	private long id;
	private String linkForDocument;
	
	public Document() {
	}

	public String getLinkForDocument() {
		return linkForDocument;
	}

	public void setLinkForDocument(String linkForDocument) {
		this.linkForDocument = linkForDocument;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
