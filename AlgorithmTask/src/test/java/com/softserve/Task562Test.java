package com.softserve;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.Helper.sumOfDigitInPow;

public class Task562Test {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //All 1-digit numbers are Armstrong's numbers
    // 1=1^1, ..., 9=9^1
    @Test
    public void test1() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(8);
        expected.add(9);
        Assert.assertEquals(expected, Task562.getArmstrongsNumbers(1, 9));
    }

    //Test 2-digit numbers.Expected []
    @Test
    public void test2() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        Assert.assertEquals(expected, Task562.getArmstrongsNumbers(10, 99));
    }

    //Test 3-digit numbers.Expected [153, 370, 371, 407]
    @Test
    public void test3() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(153);
        expected.add(370);
        expected.add(371);
        expected.add(407);
        List<Integer> actual = Task562.getArmstrongsNumbers(100, 999);
        for (Integer temp : actual) {
            Assert.assertEquals(new Long(temp), new Long(sumOfDigitInPow(temp, 3)));
        }
        Assert.assertEquals(expected, Task562.getArmstrongsNumbers(100, 999));
    }

    //Test 4-digit numbers.Expected [ 1634, 8208, 9474]
    @Test
    public void test4() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1634);
        expected.add(8208);
        expected.add(9474);
        List<Integer> actual = Task562.getArmstrongsNumbers(1000, 9999);
        for (Integer temp : actual) {
            Assert.assertEquals(new Long(temp), new Long(sumOfDigitInPow(temp, 3)));
        }
        Assert.assertEquals(expected, Task562.getArmstrongsNumbers(1000, 9999));
    }
}
