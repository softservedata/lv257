package com.softserve.edu.algorithmTasks;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;


public class EuclideanAlgorithmTest {

    EuclideanAlgorithm ea = new EuclideanAlgorithm();
    
    @Test
    public void testGcd1() {
       int expected = 3;
       int actual = ea.gcd(15, 6);
       Assert.assertEquals(expected, actual);
  
    }
    
    @Test
    public void testGcd2() {
       int expected = 15;
       int actual = ea.gcd(15, 0);
       Assert.assertEquals(expected, actual);
   
    }
    
    @Test
    public void testGcd3() {
       int expected = 3;
       int actual = ea.gcd(6, 15);
       Assert.assertEquals(expected, actual);
   
    }
    
    @Test
    public void testGcd4() {
        int expected = 15;
        int actual = ea.gcd(0, 15);
        Assert.assertEquals(expected, actual);
    
     }
    
    @Test (expected = ArithmeticException.class)
    public void testGcd5() {
        int expected = 0;
        int actual = ea.gcd(0, 0);
        Assert.assertEquals(expected, actual);
        
        
     }

    @Test
    public void testRecursionGCD1() {
        int expected = 3;
        int actual = ea.recursionGCD(15, 6);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testRecursionGCD2() {
        int expected = 15;
        int actual = ea.recursionGCD(15, 0);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testRecursionGCD3() {
        int expected = 15;
        int actual = ea.recursionGCD(0, 15);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testRecursionGCD4() {
        int expected = 3;
        int actual = ea.recursionGCD(6, 15);
        Assert.assertEquals(expected, actual);
    }

    @Test (expected = ArithmeticException.class)
    public void testRecursionGCD5() {
        int expected = 0;
        int actual = ea.recursionGCD(0, 0);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLcm1() {
        int expected = 30;
        int actual = ea.lcm(15, 6);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLcm2() {
        int expected = 30;
        int actual = ea.lcm(6, 15);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testLcm3() {
        int expected = 0;
        int actual = ea.lcm(15, 0);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testLcm4() {
        int expected = 0;
        int actual = ea.lcm(0, 15);
        Assert.assertEquals(expected, actual);
    }
    
    @Test (expected = ArithmeticException.class)
    public void testLcm5() {
        int expected = 0;
        int actual = ea.lcm(0, 0);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testRecursionLCM1() {
        int expected = 30;
        int actual = ea.recursionLCM(15, 6);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testRecursionLCM2() {
        int expected = 30;
        int actual = ea.recursionLCM(6, 15);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testRecursionLCM3() {
        int expected = 0;
        int actual = ea.recursionLCM(15, 0);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testRecursionLCM4() {
        int expected = 0;
        int actual = ea.recursionLCM(0, 15);
        Assert.assertEquals(expected, actual);
    }
    
    @Test (expected = ArithmeticException.class)
    public void testRecursionLCM5() {
        int expected = 0;
        int actual = ea.recursionLCM(0, 0);
        Assert.assertEquals(expected, actual);
    }

}
