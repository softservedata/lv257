package com.softserve.edu.task330;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PerfectNumberTest {

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
    public void testGetPerfectNumbers1() {
        
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(6);
        int[] expected = new int[expectedList.size()];
        for (int i = 0; i < expectedList.size(); i++) {
            expected[i] = expectedList.get(i);
        }
        
        PerfectNumber numbers = new PerfectNumber();
        List<Integer> actualList = new ArrayList<>();
        actualList = numbers.getPerfectNumbers(8);
        int[] actual = new int[actualList.size()];
        for (int i = 0; i < actualList.size(); i++) {
            actual[i] = actualList.get(i);
        }

        Assert.assertArrayEquals(expected, actual);
    }
    
    
    @Test
    public void testGetPerfectNumbers2() {
        
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(6);
        expectedList.add(28);
        expectedList.add(496);
        int[] expected = new int[expectedList.size()];
        for (int i = 0; i < expectedList.size(); i++) {
            expected[i] = expectedList.get(i);
        }
        
        PerfectNumber numbers = new PerfectNumber();
        List<Integer> actualList = new ArrayList<>();
        actualList = numbers.getPerfectNumbers(2000);
        int[] actual = new int[actualList.size()];
        for (int i = 0; i < actualList.size(); i++) {
            actual[i] = actualList.get(i);
        }

        Assert.assertArrayEquals(expected, actual);
    }
    
    @Test
    public void testGetPerfectNumbers3() {
        
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(6);
        expectedList.add(28);
        expectedList.add(496);
        expectedList.add(8128);
        
        int[] expected = new int[expectedList.size()];
        for (int i = 0; i < expectedList.size(); i++) {
            expected[i] = expectedList.get(i);
        }
        
        PerfectNumber numbers = new PerfectNumber();
        List<Integer> actualList = new ArrayList<>();
        actualList = numbers.getPerfectNumbers(20000);
        int[] actual = new int[actualList.size()];
        for (int i = 0; i < actualList.size(); i++) {
            actual[i] = actualList.get(i);
        }

        Assert.assertArrayEquals(expected, actual);
    }

}
