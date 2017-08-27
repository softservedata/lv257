package com.softserve.edu.resources.serviceImpl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.softserve.edu.resources.entity.GenericResource;
import com.softserve.edu.resources.entity.PropertyValue;
import com.softserve.edu.resources.entity.ResourceProperty;
import com.softserve.edu.resources.entity.ResourceType;
import com.softserve.edu.resources.service.PropertyService;
import com.softserve.edu.resources.service.ResourceTypeService;

public class ResourceServiceTest {

	private ResourceServiceImpl resourceManager;
	private ResourceTypeService typeManager = new ResourceTypeServiceImpl();
	private final Set<PropertyValue> carPropertyValues = new HashSet<>();

	@BeforeMethod
	public void setUp() throws Exception {
		resourceManager = new ResourceServiceImpl();
		PropertyService propertyManager = new PropertyServiceImpl();
		ResourceTypeService resourceTypeManager = new ResourceTypeServiceImpl();
		ResourceType carType = new ResourceType("car");
		ResourceProperty colorProperty = new ResourceProperty("color");
		carType.addProperty(colorProperty);
		carPropertyValues.add(new PropertyValue(colorProperty, "blue"));
		ResourceProperty modelProperty = new ResourceProperty("model");
		carType.addProperty(modelProperty);
		carPropertyValues.add(new PropertyValue(modelProperty, "Civic"));
		ResourceProperty bodyProperty = new ResourceProperty("body type");
		carType.addProperty(bodyProperty);
		carPropertyValues.add(new PropertyValue(bodyProperty, "sedan"));
		resourceTypeManager.add(carType);
		resourceManager.addResource(carType, carPropertyValues);
	}

	@Test
	public void getResources() throws Exception {
    Collection<GenericResource> resources = resourceManager.getResources();
		Assert.assertNotNull(resources);
	}

	@Test
	public void getResourceTypes() throws Exception {
		Collection<ResourceType> resourceTypes = resourceManager.getResourceTypes();
		Assert.assertNotNull(resourceTypes);
	}

	@Test
	public void addExistingResource() throws Exception {
		Collection<ResourceType> resourceTypes = resourceManager.getResourceTypes();
		Assert.assertNotEquals(0, resourceTypes.size(), "Add at least one resource type");
		ResourceType resourceType = resourceTypes.iterator().next();
		int typesCount = resourceTypes.size();
		GenericResource resource = resourceManager.addResource(resourceType, carPropertyValues);
		assertEquals(typesCount, resourceManager.getResourceTypes().size());
	}

	@Test
	public void addResource() throws Exception {
		int typesCount = resourceManager.getResourceTypes().size();
		Optional<ResourceType> resourceType = typeManager.get("airplane");
		Set<PropertyValue> resourceValues = new HashSet<>();
		resourceType.ifPresent(rType -> resourceManager.addResource(rType, resourceValues));
		assertFalse(true);

		/*
		  - add resourceType if absent
		  - check resource values conform resourceType - throw InvalidArgumentException
		  - else add resource
		*/
	}

	@Test
	public void createResource() throws Exception {

	}
}