/*
*  Copyright (c) 1993-2017 Softserve, Inc.
 *
 * This software is the confidential and proprietary information of SoftServe.
*/

package com.softserve.edu.algorithms;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Client implementation class.
 */

public final class NaturalNumbers {
  /**
   * Decimal base.
   */
  public static final int BASE_10 = 10;

  /**
   * Don't let anyone instantiate this class.
   */
  private NaturalNumbers() {
  }

  /**
   * Used to separate output of different metods.
   */
  private static final String OUTPUT_SEPARATOR = "---------";

  /**
   * Main method executing task implementations.
   *
   * @param args command line parameters for executed tasks
   */
  public static void main(final String[] args) {
    int inputBound;
    if (args.length > 0) {
      try {
        inputBound = Integer.parseInt(args[0]);
        List<Integer> factors = factorize(inputBound);
        System.out.printf("dividers of %d are:%n%s%n", inputBound, factors);
        System.out.println(OUTPUT_SEPARATOR);
        List<Integer> primeFactors = getPrimeFactors(inputBound);
        System.out.printf("prime dividers of %d are:%n%s%n", inputBound, primeFactors);
        System.out.println(OUTPUT_SEPARATOR);
        List<Integer> numbers = getNumbersWhereBaseIsSuffixOfItsSquare(inputBound);
        System.out.println("Numbers whose square contains the number " + "as a suffix:");
        numbers.forEach(number -> System.out.printf("%d -> %d%n", number, number * number));
      } catch (Exception e) {
        System.out.printf("%s - %s", e, e.getMessage());
      }
    } else {
      System.out.println("run java com.softserve.edu.Algorithms.NaturalNumbers <number>");
    }
  }

  /**
   * Returns natural numbers which are suffixes of it's square,
   * i.e. 6 -&gt; 36 (6^2)
   *
   * @param bound upper bound of range of search
   * @return List containing number and it's square (key and value respectively)
   * @throws IllegalArgumentException if {@code bound} is not natural
   */

  public static List<Integer>
      getNumbersWhereBaseIsSuffixOfItsSquare(final int bound) {
    if (bound < 1) {
      throw new IllegalArgumentException("natural number expected, but found " + bound);
    }
    int digitCount = countSignificantDigits(bound);
    return Stream.iterate(1, number -> ++number)
               .limit(bound)
               .filter(number -> isSuffixOf(number, number * number))
               .collect(Collectors.toList());
  }

  /**
   * Checks whether {@code first} number is a suffix of {@code second} number.
   *
   * @param first   {@code int} number to check against {@code second}
   * @param second  {@code int} number being matched with suffix number
   *                {@code first}
   * @return {@code true} if {@code first} is a suffix of {@code second}
   */
  public static boolean isSuffixOf(final int first, final int second) {
    return lessDigitsThan(first, second)
               && first == getLeastSignificantDigits(second, countSignificantDigits(first));
  }

  /**
   * Checks whether {@code first} number has less digits than {@code second}.
   *
   * @param first {@code int} value digit quantity of which being compared
   * @param second {@code int} value digit quantity of which being compared
   * @return true if {@code first} number has less digits than {@code second}
   */
  public static boolean lessDigitsThan(final int first, final int second) {
    return countSignificantDigits(first) < countSignificantDigits(second);
  }

  /**
   * Returns number of least significant digits (numerical suffix)
   * of provided number.
   *
   * @param number   number to examine
   * @param lsdCount number of least significant digits to return
   * @return number of least significant digits of {@code number}
   * @throws IllegalArgumentException if {@code lsdCount} is negative
   */
  public static int getLeastSignificantDigits(
      final int number, final int lsdCount) {
    if (lsdCount < 0) {
      throw new IllegalArgumentException("lsdCount should be positive");
    }
    return (int) Math.abs((number % Math.pow(BASE_10, lsdCount)));
  }

  /**
   * Returns number of significant digits of number.
   *
   * @param number to examine
   * @return number of significant digits present in {@code number}
   */
  public static int countSignificantDigits(final int number) {
    int n = 1;
    int suffix = number;
    while ((suffix /= BASE_10) != 0) {
      n++;
    }
    return n;
  }

  /**
   * Returns all natural dividers (factors) of given natural number.
   *
   * @param n number to be examined
   * @return List containing factors of given number
   * @throws IllegalArgumentException if {@code n} is not natural
   */
  public static List<Integer> factorize(final int n) {
    if (n < 1) {
      throw new IllegalArgumentException("natural number expected, but found " + n);
    }
    return IntStream.rangeClosed(1, n)
               .filter(i -> n % i == 0)
               .boxed()
               .collect(Collectors.toList());
  }

  /**
   * Returns all prime dividers (factors) of given natural number.
   *
   * @param n number to be examined
   * @return List containing prime dividers (factors) of given number
   * @throws IllegalArgumentException if {@code n} is not natural
   */
  public static List<Integer> getPrimeFactors(final int n)
      throws IllegalArgumentException {
    if (n < 1) {
      throw new IllegalArgumentException("natural number expected, but found " + n);
    }
    return factorize(n).stream()
               .filter(integer -> BigInteger.valueOf(integer).isProbablePrime(BASE_10))
               .collect(Collectors.toList());
  }

}
