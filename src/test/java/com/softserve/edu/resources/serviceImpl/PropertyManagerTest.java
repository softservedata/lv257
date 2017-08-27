package com.softserve.edu.resources.serviceImpl;

import com.softserve.edu.resources.entity.ResourceProperty;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.testng.Assert.*;


public class PropertyManagerTest {

	private PropertyManager propertyManager;
  private String[] propNames = {"color", "flavour", "power", "length",
                                "weight", "width", "height", "max load",
                                "engine capacity", "registration #", "volume"};

	@BeforeMethod
	public void setUp() throws Exception {
    Arrays.sort(propNames);
    propertyManager = new PropertyManager();
    for(String propName: propNames) {
      propertyManager.addProperty(new ResourceProperty(propName));
    }
	}
	
	@Test
	public void addProperty() throws Exception {
		int propCount = propertyManager.propertiesCount();
		ResourceProperty property = new ResourceProperty("age");
		propertyManager.addProperty(property);
		assertEquals(propCount + 1, propertyManager.propertiesCount());
		assertTrue(propertyManager.getProperty("age").isPresent(), "Property is missing");
		assertEquals(property, propertyManager.getProperty("age").get());
	}

  @Test
  public void addExistingProperty() throws Exception {
    ResourceProperty property = new ResourceProperty("age");
    propertyManager.addProperty(property);
    int propCount = propertyManager.propertiesCount();
    assertEquals(propCount,propertyManager.propertiesCount());
    assertTrue(propertyManager.getProperty("age").isPresent(), "Property is missing");
    assertEquals(property, propertyManager.getProperty("age").get());
  }
	
	@Test
	public void getPropertyByName() throws Exception {
		assertFalse(propertyManager.getProperty("age").isPresent());
		Optional<ResourceProperty> property = propertyManager.getProperty("width");
		assertTrue(property.isPresent() && property.get().equals(new ResourceProperty("width")));
	}

  @Test
  public void getPropertyNames() throws Exception {
    Assert.assertEquals(propNames, propertyManager.propertyDescriptions().toArray(new String[]{}));
  }

  @Test
  public void getProperiesWithSameName() throws Exception {
    final String volumeLowerC = "volume";
    final String volumeUpperC = "VOLUME";
    assertTrue(propertyManager.getProperty(volumeLowerC).isPresent(), "Property is missing");
    ResourceProperty volProp = propertyManager.getProperty(volumeLowerC).get();
    assertTrue(propertyManager.getProperty(volumeUpperC).isPresent(), "Property is missing");
    Assert.assertEquals(volProp, propertyManager.getProperty(volumeUpperC).get());
    assertTrue(propertyManager.getProperty("age").isPresent(), "Property is missing");
    int propCount = propertyManager.propertiesCount();
    int volumePropCount = propertyManager.getProperties(volumeLowerC).size();
    propertyManager.addProperty(new ResourceProperty(volumeLowerC).setUnits("cc"));
    Assert.assertEquals(propCount + 1, propertyManager.propertiesCount());
    Assert.assertEquals(volumePropCount + 1, propertyManager.getProperties(volumeLowerC).size());
  }
}