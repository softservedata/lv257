package com.softserve;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;


public class Task329Test {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //1 is Natural, 1 is  square of 1. Expected result [1]
    @Test
    public void test1() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        Assert.assertEquals(expected, Task329.getSpecialNumbers(1, 1));
    }

    //1 is Natural, 25 is  square of 5. Expected result []
    @Test
    public void test2() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        Assert.assertEquals(expected, Task329.getSpecialNumbers(1, 25));
    }

    //100 is Natural, 10 is  not square of any number. Expected result []
    @Test
    public void test3() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        Assert.assertEquals(expected, Task329.getSpecialNumbers(100, 10));
    }

    //100 is Natural, 25 is square of 5. Expected result [5,14,23,32,41,50]
    @Test
    public void test4() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(5);
        expected.add(14);
        expected.add(23);
        expected.add(32);
        expected.add(41);
        expected.add(50);
        Assert.assertEquals(expected, Task329.getSpecialNumbers(100, 25));
    }

    //-1 is not Natural, 25 is square of 5. Expected result []
    @Test
    public void testNegative1() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        Assert.assertEquals(expected, Task329.getSpecialNumbers(-1, 25));
    }

    //100 is Natural, -7 is not Natural. Expected result []
    @Test
    public void testNegative2() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        Assert.assertEquals(expected, Task329.getSpecialNumbers(100, -7));
    }


}
