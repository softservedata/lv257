package com.softserve.edu.algorithmTasks;

import org.junit.Assert;
import org.junit.Test;

public class MaxSumOfDivisionsTest {

    MaxSumOfDivisions msod = new MaxSumOfDivisions();

    @Test
    public void testFindNumberWithMaxSumOfDivisors1() {

        int expected = 96;
        int actual = msod.findNumberWithMaxSumOfDivisors(1, 100);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testFindNumberWithMaxSumOfDivisors2() {

        int expected = 180;
        int actual = msod.findNumberWithMaxSumOfDivisors(101, 200);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testFindNumberWithMaxSumOfDivisors3() {

        int expected = 300;
        int actual = msod.findNumberWithMaxSumOfDivisors(201, 300);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testFindNumberWithMaxSumOfDivisors4() {

        int expected = 300;
        int actual = msod.findNumberWithMaxSumOfDivisors(1, 300);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testFindNumberWithMaxSumOfDivisors5() {

        int expected = 960;
        int actual = msod.findNumberWithMaxSumOfDivisors(1, 1000);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testFindNumberWithMaxSumOfDivisors6() {

        int expected = 960;
        int actual = msod.findNumberWithMaxSumOfDivisors(900, 1000);
        Assert.assertEquals(expected, actual);

    }
}
