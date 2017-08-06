package com.softserve.edu.task90;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FindNumbersTest {

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
    public void testFindNumbers1() {
        
        FindNumbers numbers = new FindNumbers();
        numbers.findNumbers(6, 4);
        
        int actual1 = numbers.getP();
        int actual2 = numbers.getQ();
        
        int expected1 = 3;
        int expected2 = 2;
        
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        
    }

    @Test
    public void testFindNumbers2() {
        
        FindNumbers numbers = new FindNumbers();
        numbers.findNumbers(10, 4);
        
        int actual1 = numbers.getP();
        int actual2 = numbers.getQ();
        
        int expected1 = 5;
        int expected2 = 2;
        
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        
    }
    
    @Test
    public void testFindNumbers3() {
        
        FindNumbers numbers = new FindNumbers();
        numbers.findNumbers(100, 30);
        
        int actual1 = numbers.getP();
        int actual2 = numbers.getQ();
        
        int expected1 = 10;
        int expected2 = 3;
        
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        
    }
    
    @Test
    public void testFindNumbers4() {
        
        FindNumbers numbers = new FindNumbers();
        numbers.findNumbers(250, 136);
        
        int actual1 = numbers.getP();
        int actual2 = numbers.getQ();
        
        int expected1 = 125;
        int expected2 = 68;
        
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        
    }
    
    @Test
    public void testFindNumbers5() {
        
        FindNumbers numbers = new FindNumbers();
        numbers.findNumbers(20154, 136);
        
        int actual1 = numbers.getP();
        int actual2 = numbers.getQ();
        
        int expected1 = 10077;
        int expected2 = 68;
        
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        
    }
}
