package com.softserve.edu.resources.serviceImpl;

import static org.testng.Assert.assertEquals;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.softserve.edu.resources.entity.ResourceProperty;
import com.softserve.edu.resources.entity.ResourceType;
import com.softserve.edu.resources.service.ResourceTypeService;

public class ResourceTypeServiceTest {

	private ResourceTypeService resourceTypeManager;
	private PropertyServiceImpl propertyManager;

	@BeforeMethod
	public void setUp() throws Exception {
		resourceTypeManager = new ResourceTypeServiceImpl();
		resourceTypeManager.add(new ResourceType("Motorcycle"));
		propertyManager = new PropertyServiceImpl();
		propertyManager.addProperty(new ResourceProperty("color"));
		propertyManager.addProperty(new ResourceProperty("color"));
		propertyManager.addProperty(new ResourceProperty("power"));
		propertyManager.addProperty(new ResourceProperty("length"));
		propertyManager.addProperty(new ResourceProperty("weight"));
		propertyManager.addProperty(new ResourceProperty("width"));
		propertyManager.addProperty(new ResourceProperty("height"));
		propertyManager.addProperty(new ResourceProperty("max load"));
		propertyManager.addProperty(new ResourceProperty("passengers"));
		propertyManager.addProperty(new ResourceProperty("engine capacity"));
		propertyManager.addProperty(new ResourceProperty("registration #"));
	}

	@Test
	public void listModels() throws Exception {
		Collection<ResourceType> metaModels = resourceTypeManager.getTypes();
		Assert.assertNotNull(metaModels);
		metaModels.forEach(resourceType -> System.out.println(resourceType.getName()));
	}

	@Test
	public void addResourceType() throws Exception {
		assertEquals(resourceTypeManager.getTypes().size(), 1);
		ResourceType resourceType = new ResourceType("Vehicle");
		resourceTypeManager.add(resourceType);
		assertEquals(resourceTypeManager.getTypes().size(), 2);
	}

	@Test
	public void addExistingType() throws Exception {
		int rmCount = resourceTypeManager.getTypes().size();
		ResourceType mmResource = new ResourceType("Aircraft");
		resourceTypeManager.add(mmResource);
		assertEquals(rmCount + 1, resourceTypeManager.getTypes().size());
		resourceTypeManager.add(mmResource);
		assertEquals(rmCount + 1, resourceTypeManager.getTypes().size());
	}

	@Test
	public void removeExistingType() throws Exception {
		int rmCount = resourceTypeManager.getTypes().size();
		ResourceType rmExisting = resourceTypeManager.getTypes().iterator().next();
		resourceTypeManager.remove(rmExisting);
		assertEquals(rmCount - 1, resourceTypeManager.getTypes().size());
	}

	@Test
	public void removeMissingType() throws Exception {
		ResourceType rmPlane = new ResourceType("Plane");
		int rmCount = resourceTypeManager.getTypes().size();
		resourceTypeManager.remove(rmPlane);
		assertEquals(rmCount, resourceTypeManager.getTypes().size());
	}

	@Test
	public void instantiateResourceType() throws Exception {

	  final int resTypesCount = resourceTypeManager.getTypeCount();
    ResourceType resourceType = new ResourceType("Truck");
		resourceTypeManager.add(resourceType);
		assertEquals(resourceTypeManager.getTypeCount(), resTypesCount + 1);

		propertyManager.getProperty("color").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("power").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("length").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("weight").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("width").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("height").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("max load").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("engine capacity").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("registration #").ifPresent(resourceType::addProperty);

    final int typeInstancesCount = resourceTypeManager.getInstancesCount();
    resourceTypeManager.create("Truck");
    assertEquals(resourceTypeManager.getInstancesCount(), typeInstancesCount + 1);
	}

}