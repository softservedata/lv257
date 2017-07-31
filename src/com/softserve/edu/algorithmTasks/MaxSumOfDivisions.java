package com.softserve.edu.algorithmTasks;

import java.util.HashSet;
import java.util.Set;


/**
 * Class MaxSumOfDivisions implements method, which is used to find the number
 * with maximum sum of divisors from special length of natural numbers; It is a
 * solution for Exercise ¹322 from book "Exercises for programming" of Abramov
 * S.A., Gnezdilova G.G., Kapustina E.N., Selyun M.I..
 * @version 1.0
 * @author Yura
 *
 */
public class MaxSumOfDivisions {
    /**
     * This method is used to find number with maximum sum of divisors from
     * special interval of integer non-negative numbers (natural numbers).
     *
     * @param startPoint the start of the interval
     * @param endPoint the end of the interval
     * @return int The number with maximum sum of divisors from interval
     *         startPoint - endPoint.
     */

    public int findNumberWithMaxSumOfDivisors(int startPoint, int endPoint) {

        /*
         * Creating an array of even numbers from the interval,
         * because only even numbers have the most quantity of divisors.
         */
        int[] ar1 = new int[((endPoint - startPoint) + 1) / 2];
        int startNumber = startPoint;
        if (startPoint % 2 == 0) {
            for (int i = 0; i < ar1.length; i++) {
                startNumber += 2;
                ar1[i] = startNumber;
            }
        } else {
            startNumber = startPoint + 1;
            for (int i = 0; i < ar1.length; i++) {
                startNumber += 2;
                ar1[i] = startNumber;
            }
        }

        /*
         *   initializing a number for counting current maximum sum of divisors
         *   for current iteration
         */
        int maxSumOfDivisors = 0;

        /*
         *  initializing a number for counting a current sum of divisors of
         *  current iteration number
         */
        int currentSumOfDivisions = 0;

        /*
         * initializing a number for tracking a current number
         * with maximum sum of its divisors from special interval
         */
        int numberWithMaxSumOfDivisions = 0;

        /*
         * Set is created for tracking a divisors which are not repeated.
         */
        Set<Integer> tempSet = new HashSet<>();

        /*
         * newDivisionsExists boolean variable is for stopping the iteration
         * of looking for new divisors. If we find the first
         * same divisors, which we have already collected to Set,
         * there won't be more new divisors, and we can stop iteration.
         */
        boolean newDivisionsExists = true;

        /*
         * First 'for' cycle: iteration through even numbers
         * to find the number with maximum sum of divisors;
         * Second 'for' cycle: iteration through possible divisors
         * of above numbers
         */
        for (int i = 0; i < ar1.length; i++) {
            for (int j = 1; j < endPoint; j++) {
                if (ar1[i] % j == 0) {
                    /* used primitive int in 'forEach' cycle
                     *  for avoiding of two unboxing when
                     *  comparing values from array and set;
                     */
                    for (int integer : tempSet) {
                        if (ar1[i] / j == integer || j == integer) {
                            newDivisionsExists = false;
                        }
                    }
                    if (!newDivisionsExists) {
                        break;
                    }
                    tempSet.add(ar1[i] / j);
                    tempSet.add(j);
                }

            }
            for (Integer integer : tempSet) {
                currentSumOfDivisions += integer;
            }
            if (currentSumOfDivisions > maxSumOfDivisors) {
                maxSumOfDivisors = currentSumOfDivisions;
                numberWithMaxSumOfDivisions = ar1[i];
            }

            /*
             * Defining zero value for sum of divisors,
             * clearing set of divisors for current number for using
             * these variables and collection to the next number in array
             */
            currentSumOfDivisions = 0;
            tempSet.clear();
            newDivisionsExists = true;
        }

        return numberWithMaxSumOfDivisions;

    }

}
