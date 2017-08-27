package com.softserve.edu.resources.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.softserve.edu.entity.GenericResource;
import com.softserve.edu.entity.Property;

public interface ResourceServiceRead {

	// 1) search with special ResourceType and few important fields
	List <GenericResource> findByResourceAndEssentialFields (String resourceTableName, Map <String, String> fields);
	
	/* 2) search by Owner in every resource type (Company Or Individual owner, on view we can choose)
	 (fields to search about owner: name or IPN (to choose)) with steps:
	- a) search among owners (OwnerService)
	- b) choose special owner
	- c) search among all resources with special owner, which was chosen in "b" step*/
	/* TODO: which fields to show for result of such search?and how?
		-1 variant: show result for every resource with two fields: ResourceTypeName and NameOfResourceRecord
	 	-2 variant: show result with Essential(marked) fields of every resource by grouping every resource */
	List <GenericResource> findByOwnerWithoutSpecialCategory(Integer ownerID, String ownerType);
	
	// 3) search resources with special registration date without special category
	/* TODO:  which fields to show for result of such search?and how?
		-1 variant: show result for every resource with two fields: ResourceTypeName and NameOfResourceRecord
	 	-2 variant: show result with Essential(marked) fields of every resource by grouping every resource*/
	List <GenericResource> findByRegistrationDateField(Date dateOfRegistration);
	
	
	

	
	
	
	
	
	
}
