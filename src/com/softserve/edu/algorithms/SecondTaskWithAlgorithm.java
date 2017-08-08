/*
 * Condition of task:
 * Given a natural number n. Can we imagine it in
 * The sum of two squares of this natural numbers?
 * If it is possible, then indicate a pair x, y
 * of positive integers such that
 * n = x*x + y*y
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
 * we need to import Arraylist class
 * @author Lv-257
 */
public class SecondTaskWithAlgorithm {
/**
* @param number first parameter to analyze
* @return ArrayList of Strings
*/
    public ArrayList<String> sumOfSquaresOfTwoNumbers(final int number) {
       ArrayList<String> list = new ArrayList<String>();
        int to = (int) Math.sqrt(number);
         for (int a = 1; a <= to; a++) {
            int b = (int) Math.sqrt(number - Math.pow(a, 2));
            if (Math.pow(b, 2) + Math.pow(a, 2) == number && b != 0) {
            list.add(a + " and " + b);
            return list;
            }
        }
        return list;
    }
}
