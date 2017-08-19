package com.softserve.edu.task88b;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReverseTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRevers1() {
        
        Reverse reverse = new Reverse();
        String expected = "1";
        String actual = reverse.revers(1);
        Assert.assertEquals(expected, actual); 
    }
    
    @Test
    public void testRevers2() {
        
        Reverse reverse = new Reverse();
        String expected = "01";
        String actual = reverse.revers(10);
        Assert.assertEquals(expected, actual); 
    }
    
    @Test
    public void testRevers3() {
        
        Reverse reverse = new Reverse();
        String expected = "654321";
        String actual = reverse.revers(123456);
        Assert.assertEquals(expected, actual); 
    }
    
    @Test
    public void testRevers4() {
        
        Reverse reverse = new Reverse();
        String expected = "5";
        String actual = reverse.revers(5);
        Assert.assertEquals(expected, actual); 
    }
    
    @Test
    public void testRevers5() {
        
        Reverse reverse = new Reverse();
        String expected = "123456";
        String actual = reverse.revers(654321);
        Assert.assertEquals(expected, actual); 
    }
}
