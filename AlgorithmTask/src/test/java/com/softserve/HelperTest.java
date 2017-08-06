package com.softserve;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.softserve.Helper.numberOfDigit;
import static com.softserve.Helper.sumOfDigitInPow;

public class HelperTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void numberOfDigitTest1() {
        Assert.assertEquals(1, numberOfDigit(0));
    }

    @Test
    public void numberOfDigitTest2() {
        Assert.assertEquals(3, numberOfDigit(100));
    }

    @Test
    public void sumOfDigitInPowerTest1() {
        Assert.assertEquals(1, sumOfDigitInPow(1, 0));
    }

    @Test
    public void sumOfDigitInPowerTest2() {
        Assert.assertEquals(5, sumOfDigitInPow(12, 2));
    }


}
