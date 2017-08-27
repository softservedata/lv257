package com.softserve.edu.resources.service;

import com.softserve.edu.resources.entity.ResourceProperty;

import java.util.List;

public interface PropertyServiceRead {

	List <ResourceProperty> getImportantPropertiesFromSpecialResourceToSearch(String resourceTableName);
	
	
}
