package com.softserve.edu.algorithmTasks;

/**
 * The EuclideanAlgorithm class consists from methods for finding:
 * - the greatest common divisor;
 * - the least common multiple;
 * for two non-negative integers with next options:
 * a) two integers don't equal '0' at one time;
 * b) one integer could be equal or greater than another integer.
 * @version 1.0
 * @author Yura
 *
 */
public class EuclideanAlgorithm {

    /**
     * This method, which uses the Euclidean algorithm, is used to find
     * the greatest common divisor (GCD) for two non-negative integers,
     * which are the first and the second parameters in method.
     * @param m first parameter to analyze
     * @param n second parameter to analyze
     * @return int The greatest common divisor.
     * @throws ArithmeticException when both parameters are equal '0'.
     */
    public int gcd(int m, int n) throws ArithmeticException {
        int r = 0;

        if (m == 0 && n == 0) {
            throw new ArithmeticException();
        }
        if (m >= n) {

            if (n == 0) {
                return m;
            }

            while (n != 0) {

                r = m % n;
                m = n;
                n = r;

            }
            return m;

        } else {
            if (m == 0) {
                return n;
            }
            while (m != 0) {
                r = n % m;
                n = m;
                m = r;
            }
            return n;

        }

    }

    /**
     * This method returns the same as 'gcd(int m, int n)' method,
     * but only using an Euclidean algorithm with recursion
     * to find out greatest common divisor(GCD) for two non-negative integers,
     * which are the first and the second parameters in method.
     * @param m first parameter to analyze
     * @param n second parameter to analyze
     * @return int The greatest common divisor for the parameters of the method.
     * @throws ArithmeticException when both parameters are equal to '0'.
     */
    public int recursionGCD(int m, int n) throws ArithmeticException {

        if (m == 0 && n == 0) {
            throw new ArithmeticException();
        }

        if (m >= n) {
            if (n == 0) {
                return m;
            } else {
                return recursionGCD(n, m % n);
            }
        } else {
            if (m == 0) {
                return n;
            } else {
                return recursionGCD(n % m, m);
            }
        }

    }

    /**
     * This method is used to find the least common multiple(LCM)
     * for two non-negative integers, using the Euclidean algorithm
     * with method 'gcd(int m, int n)'.
     * @param m first parameter to analyze (non-negative integer)
     * @param n second parameter to analyze (non-negative integer)
     * @return int The least common multiple
     * @throws ArithmeticException when both parameters are equal to '0'.
     */
    public int lcm(int m, int n) throws ArithmeticException {
        return m * n / gcd(m, n);
    }

    /**
     * This method returns the same as 'lcm(int m, int n)' method,
     * but only using an Euclidean algorithm with recursion
     * to find out the least common multiple(LCM) for two non-negative integers,
     * which are the first and the second parameters in method.
     * @param m first parameter to analyze (non-negative integer)
     * @param n second parameter to analyze (non-negative integer)
     * @return int The least common multiple
     * @throws ArithmeticException when both parameters are equal to '0'.
     */
    public int recursionLCM(int m, int n) throws ArithmeticException {
        return m * n / recursionGCD(m, n);
    }

}
