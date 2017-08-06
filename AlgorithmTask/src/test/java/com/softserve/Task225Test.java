package com.softserve;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Task225Test {
    @Before
    public void setUp() throws Exception {
    }

    //number 2 does not have special dividers
    @Test
    public void test1() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        Assert.assertEquals(expected, Task225.getSpecialDivider(2));
    }

    //number 4 has only one divider 2
    @Test
    public void test2() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(2);
        Assert.assertEquals(expected, Task225.getSpecialDivider(4));
    }

    //number 100 has dividers [2,5,10]
    @Test
    public void test3() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(2);
        expected.add(5);
        expected.add(10);
        Assert.assertEquals(expected, Task225.getSpecialDivider(100));
    }

    //number -4 is not Natural. Expected result will be []
    @Test
    public void testNegative1() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        Assert.assertEquals(expected, Task225.getSpecialDivider(-4));
    }


    @After
    public void tearDown() throws Exception {
    }

}