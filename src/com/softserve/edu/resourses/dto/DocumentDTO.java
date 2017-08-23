package com.softserve.edu.resourses.dto;

public class DocumentDTO {
	private long id;
	private String linkForDocument;
	
	public DocumentDTO(long id, String linkForDocument) {
		super();
		this.linkForDocument=linkForDocument;
		this.id = id;
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
