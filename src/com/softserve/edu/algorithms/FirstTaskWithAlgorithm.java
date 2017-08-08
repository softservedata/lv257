/* Condition:
 * Given an integer m> 1. 
 * Get the largest integer k,
 * with which is 4 in power k < m
 * @version 1.0
 * @date 27.07.2017
 */
package com.softserve.edu.algorithms;
/**
 * First Class.
 * @author Lv-257
 */
public final class FirstTaskWithAlgorithm {
/**
* variable k - parameter to analyze 
*/
private double k = 0;
/**
 * variable pow - we got from the condition
 */
private final int POW = 4;
/**
 * @param m - this number we got from the condition
 * @return integer number
 */
public int findNecessaryDigit(final int m) {
while (Math.pow(POW, k) < m) {
k = k + 1;
}
return (int) k - 1;
}
/**
 * @return k - the result of task.
 */
public double getK() {
return k;
}
/**
 * @param k  in this peace of code takes
 * access to variable k
 */
public void setK(final double k) {
this.k = k;
}
}
