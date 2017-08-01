package com.softserve.edu.task1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test Case for CalcUtil class.
 *
 * @author IT Academy
 *
 */
public class CalcUtilTest {

    /**
     * Testing work of {@code calculateDigitsInNumber} method with correct
     * argument.
     */
    @Test
    public void testCalculateDigitsInNumber0() {
        int expected = 3;
        int actual = CalcUtil.calculateDigitsInNumber(765);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Testing work of {@code calculateDigitsInNumber} method with incorrect
     * argument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDigitsInNumber1() {
        CalcUtil.calculateDigitsInNumber(-765);
    }

    /**
     * Testing work of {@code calculateMultiplicity} method with correct
     * argument.
     */
    @Test
    public void testCalculateMultiplicity0() {
        int expected = 5;
        int actual = CalcUtil.calculateMultiplicity(3, 5, 1, 20);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Testing work of {@code calculateMultiplicity} method with incorrect
     * argument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMultiplicity1() {
        CalcUtil.calculateMultiplicity(3, -5, 1, 20);
    }

    /**
     * Testing work of {@code calculateMultiplicity} method with incorrect
     * argument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMultiplicity2() {
        CalcUtil.calculateMultiplicity(3, 5, 20, 1);
    }

    /**
     * Testing work of {@code calculateSumOfSquares} method with correct
     * argument.
     */
    @Test
    public void testCalculateSumOfSquares0() {
        List<String> expected = new ArrayList<>();
        expected.add("0, 1, 1, 1");
        expected.add("1, 0, 1, 1");
        expected.add("1, 1, 0, 1");
        expected.add("1, 1, 1, 0");
        List<String> actual = CalcUtil.calculateSumOfSquares(3);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Testing work of {@code calculateSumOfSquares} method with incorrect
     * argument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateSumOfSquares1() {
        CalcUtil.calculateSumOfSquares(-5);
    }

}
