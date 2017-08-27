package com.softserve.edu.resources.service;

import java.util.List;

import com.softserve.edu.entity.Property;

public interface PropertyServiceRead {

	List <Property> getImportantPropertiesFromSpecialResourceToSearch(String resourceTableName);
	
	
}
