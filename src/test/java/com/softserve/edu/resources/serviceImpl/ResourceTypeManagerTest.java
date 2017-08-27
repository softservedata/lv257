package com.softserve.edu.resources.serviceImpl;

import com.softserve.edu.resources.entity.ResourceProperty;
import com.softserve.edu.resources.entity.ResourceType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.testng.Assert.assertEquals;

public class ResourceTypeManagerTest {
	
	private ResourceTypeManager typeManager;
	private PropertyManager propertyManager;
	
	@BeforeMethod
	public void setUp() throws Exception {
		typeManager = new ResourceTypeManager();
		typeManager.add(new ResourceType("Motorcycle"));
		propertyManager = new PropertyManager();
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
		Collection<ResourceType> metaModels = typeManager.getTypes();
		Assert.assertNotNull(metaModels);
		metaModels.forEach(resourceType -> System.out.println(resourceType.getName()));
	}
	
	@Test
	public void addResourceType() throws Exception {
		assertEquals(1, typeManager.getTypes().size());
		ResourceType resourceType = new ResourceType("Vehicle");
		typeManager.add(resourceType);
		assertEquals(2, typeManager.getTypes().size());
	}
	
	@Test
	public void addExistingType() throws Exception {
		int rmCount = typeManager.getTypes().size();
		ResourceType mmResource = new ResourceType("Aircraft");
		typeManager.add(mmResource);
		assertEquals(rmCount + 1, typeManager.getTypes().size());
		typeManager.add(mmResource);
		assertEquals(rmCount + 1, typeManager.getTypes().size());
	}
	
	@Test
	public void removeExistingType() throws Exception {
		int rmCount = typeManager.getTypes().size();
		ResourceType rmExisting = typeManager.getTypes().iterator().next();
		typeManager.remove(rmExisting);
		assertEquals(rmCount - 1, typeManager.getTypes().size());
	}
	
	@Test
	public void removeMissingType() throws Exception {
		ResourceType rmPlane = new ResourceType("Plane");
		int rmCount = typeManager.getTypes().size();
		typeManager.remove(rmPlane);
		assertEquals(rmCount, typeManager.getTypes().size());
	}
	
	@Test
	public void defineResourceType() throws Exception {
		ResourceType resourceType = new ResourceType("Truck");
		typeManager.add(resourceType);
		propertyManager.getProperty("color").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("power").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("length").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("weight").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("width").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("height").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("max load").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("engine capacity").ifPresent(resourceType::addProperty);
		propertyManager.getProperty("registration #").ifPresent(resourceType::addProperty);
		typeManager.create("Truck");
	}
	
}