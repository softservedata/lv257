package com.softserve.edu.algorithms;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class NaturalNumbersTest {
  
  private final Integer[] T = new Integer[0];
  
  @Test
  public void getNumbersWhereBaseIsSuffixOfItsSquare() throws Exception {
    Assert.assertArrayEquals(new Integer[] {}, NaturalNumbers.getNumbersWhereBaseIsSuffixOfItsSquare(1).toArray(T));
    Assert.assertArrayEquals(new Integer[] {5}, NaturalNumbers.getNumbersWhereBaseIsSuffixOfItsSquare(5).toArray(T));
    Assert.assertArrayEquals(new Integer[] {5, 6}, NaturalNumbers.getNumbersWhereBaseIsSuffixOfItsSquare(7).toArray(T));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getNumbersWhereBaseIsSuffixOfItsSquareFromNonNatural() throws Exception {
    Assert.assertArrayEquals(new Integer[] {-6}, NaturalNumbers.getNumbersWhereBaseIsSuffixOfItsSquare(-100).toArray(T));
  }
  
  @Test
  public void lessDigits() throws Exception {
    Assert.assertFalse(NaturalNumbers.lessDigitsThan(0, 0));
    Assert.assertFalse(NaturalNumbers.lessDigitsThan(1, 1));
    Assert.assertFalse(NaturalNumbers.lessDigitsThan(123, 26));
    Assert.assertTrue(NaturalNumbers.lessDigitsThan(1, 11));
  }
  
  @Test
  public void isSuffixOf() throws Exception {
    Assert.assertFalse(NaturalNumbers.isSuffixOf(-0, 0));
    Assert.assertFalse(NaturalNumbers.isSuffixOf(1, -1));
    Assert.assertFalse(NaturalNumbers.isSuffixOf(-1, -11));
    Assert.assertFalse(NaturalNumbers.isSuffixOf(5, 26));
    Assert.assertTrue(NaturalNumbers.isSuffixOf(23, 123));
    Assert.assertTrue(NaturalNumbers.isSuffixOf(23, -123));
    Assert.assertTrue(NaturalNumbers.isSuffixOf(0, 10));
    Assert.assertTrue(NaturalNumbers.isSuffixOf(1, 11));
    
  }
  
  @Test
  public void getLeastSignificantDigitsOfPositiveNumber() throws Exception {
    Assert.assertEquals(0, NaturalNumbers.getLeastSignificantDigits(0, 0));
    Assert.assertEquals(0, NaturalNumbers.getLeastSignificantDigits(1, 0));
    Assert.assertEquals(0, NaturalNumbers.getLeastSignificantDigits(101, 0));
    Assert.assertEquals(1, NaturalNumbers.getLeastSignificantDigits(101, 1));
    Assert.assertEquals(1, NaturalNumbers.getLeastSignificantDigits(101, 2));
    Assert.assertEquals(101, NaturalNumbers.getLeastSignificantDigits(101, 3));
    Assert.assertEquals(101, NaturalNumbers.getLeastSignificantDigits(101, 4));
  }
  
  @Test
  public void getLeastSignificantDigitsOfNegative() throws Exception {
    Assert.assertEquals(0, NaturalNumbers.getLeastSignificantDigits(-1, 0));
    Assert.assertEquals(0, NaturalNumbers.getLeastSignificantDigits(-101, 0));
    Assert.assertEquals(1, NaturalNumbers.getLeastSignificantDigits(-101, 1));
    Assert.assertEquals(101, NaturalNumbers.getLeastSignificantDigits(-101, 3));
    Assert.assertEquals(101, NaturalNumbers.getLeastSignificantDigits(-101, 4));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getNegativeCountOfLeastSignificantDigits() throws Exception {
    Assert.assertEquals(0, NaturalNumbers.getLeastSignificantDigits(100, -1));
  }
  
  @Test
  public void factorizeNaturalNumbers() throws Exception {
    List<Integer> factors = NaturalNumbers.factorize(1);
    Assert.assertArrayEquals(new Integer[]{ 1}, factors.toArray(T));
    factors = NaturalNumbers.factorize(2);
    Assert.assertArrayEquals(new Integer[]{1, 2}, factors.toArray(T));
    factors = NaturalNumbers.factorize(13);
    Assert.assertArrayEquals(new Integer[]{1, 13}, factors.toArray(T));
    factors = NaturalNumbers.factorize(10);
    Assert.assertArrayEquals(new Integer[]{1, 2, 5, 10}, factors.toArray(T));
    factors = NaturalNumbers.factorize(169);
    Assert.assertArrayEquals(new Integer[]{1, 13, 169}, factors.toArray(T));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void factorizeNonNaturalNumber() throws Exception {
    Assert.assertEquals(1, NaturalNumbers.factorize(-1).size());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void factorizeZero() throws Exception {
    Assert.assertEquals(1, NaturalNumbers.factorize(0).size());
  }
  
  @Test
  public void getPrimeFactorsOfNaturalNumbers() throws Exception {
    List<Integer> factors = NaturalNumbers.getPrimeFactors(1);
    final Integer[] T = new Integer[0];
    Assert.assertArrayEquals(new Integer[]{}, factors.toArray(T));
    factors = NaturalNumbers.getPrimeFactors(2);
    Assert.assertArrayEquals(new Integer[]{2}, factors.toArray(T));
    factors = NaturalNumbers.getPrimeFactors(13);
    Assert.assertArrayEquals(new Integer[]{13}, factors.toArray(T));
    factors = NaturalNumbers.getPrimeFactors(10);
    Assert.assertArrayEquals(new Integer[]{2, 5}, factors.toArray(T));
    factors = NaturalNumbers.getPrimeFactors(15);
    Assert.assertArrayEquals(new Integer[]{3, 5}, factors.toArray(T));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getPrimeFactorsOfNonNaturalNumbers() throws Exception {
    Assert.assertEquals(0, NaturalNumbers.getPrimeFactors(0).size());
  }
  
  @Test
  public void countPositiveNumbersDigits() {
    Assert.assertEquals(1, NaturalNumbers.countSignificantDigits(0));
    Assert.assertEquals(1, NaturalNumbers.countSignificantDigits(1));
    Assert.assertEquals(2, NaturalNumbers.countSignificantDigits(10));
    Assert.assertEquals(2, NaturalNumbers.countSignificantDigits(11));
    Assert.assertEquals(10, NaturalNumbers.countSignificantDigits(Integer.MAX_VALUE));
  }
  
  @Test
  public void countNegativeNumbersDigits() {
    Assert.assertEquals(1, NaturalNumbers.countSignificantDigits(-1));
    Assert.assertEquals(2, NaturalNumbers.countSignificantDigits(-10));
    Assert.assertEquals(2, NaturalNumbers.countSignificantDigits(-11));
    Assert.assertEquals(10, NaturalNumbers.countSignificantDigits(Integer.MIN_VALUE));
  }

}

