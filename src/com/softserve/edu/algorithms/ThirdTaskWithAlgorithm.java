/*
 * Given a natural number n. Can we imagine it in
 * The sum of three squares of natural numbers?
 * If it is possible, then
 * Specify all triples x, y, z of natural numbers such that
 * n = x*x + y*y + z*z
 * @version 1.0
 * @date 27.07.2017
 */
package com.softserve.edu.algorithms;
/**
 * First Class.
 * @author Lv-257
 */
import java.util.ArrayList;
/**
 * importing ArrayList class.
 */
public class ThirdTaskWithAlgorithm {
/**
 * @return
 * This method returns array of strings to class application.
 * @param number -parameter to analyze
*/
public ArrayList<String> sumOfSquaresOfThreeNumbers(final int number) {
  ArrayList<String> list = new ArrayList<String>();
  int to = (int) Math.sqrt(number);
  for (int a = 1; a <= to; a++) {
  int b = (int) Math.sqrt(number - Math.pow(a, 2));
  for (b = 1; b <= to; b++) {
  int c = (int) Math.sqrt(number - Math.pow(a, 2) - Math.pow(b, 2));
  if (Math.pow(b, 2) + Math.pow(a, 2)
  + Math.pow(c, 2) == number && b != 0 && c != 0) {
  list.add(a + " and " + b + " and " + c);
       }
      }
     }
    return list;
  }
}
