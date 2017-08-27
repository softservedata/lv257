
package com.softserve.edu.resources.service;

import java.util.List;

import com.softserve.edu.resources.entity.ResourceProperty;

public interface PropertyServiceRead {

	List <ResourceProperty> getImportantPropertiesFromSpecialResourceToSearch(String resourceTableName);


}

