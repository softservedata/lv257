package com.softserve.edu.resources.entity;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;

public class ResourceTypeTest {
	
	private ResourceType resourceType;
	
	@BeforeMethod
	public void setUp() throws Exception {
		resourceType = new ResourceType("Automobile");
		resourceType.addProperty(new ResourceProperty("size"));
	}
	
	@Test
	public void getProperties() throws Exception {
		Assert.assertNotNull(resourceType);
		Collection<ResourceProperty> resProperties = resourceType.getProperties();
		Assert.assertNotNull(resProperties);
	}
	
	@Test
	public void addProperty() throws Exception {
		int propsCount = resourceType.getProperties().size();
		ResourceProperty property = new ResourceProperty("height");
		resourceType.addProperty(property);
		Assert.assertEquals(propsCount + 1, resourceType.getProperties().size());
    Assert.assertTrue(resourceType.getProperty("height").isPresent(),"Property is missing");
    Assert.assertEquals(property, resourceType.getProperty("height").get());
	}
	
	@Test
	public void addExistingProperty() throws Exception {
		resourceType.addProperty(new ResourceProperty("color"));
		int propsCount = resourceType.getProperties().size();
		resourceType.addProperty(new ResourceProperty("color"));
		Assert.assertEquals(propsCount, resourceType.getProperties().size());
	}
	
	@Test
	public void removeProperty() throws Exception {
		int propsCount = resourceType.getProperties().size();
		resourceType.removeProperty(new ResourceProperty("weight"));
		Assert.assertEquals(propsCount, resourceType.getProperties().size());
		resourceType.removeProperty(new ResourceProperty("size"));
		Assert.assertEquals(propsCount - 1, resourceType.getProperties().size());
		Assert.assertFalse(resourceType.getProperty("size").isPresent());
	}
	
	@Test
	public void getPropertyByName() throws Exception {
		Assert.assertTrue(resourceType.getProperty("size").isPresent());
		Assert.assertFalse(resourceType.getProperty("weight").isPresent());
	}
	
	
	
}