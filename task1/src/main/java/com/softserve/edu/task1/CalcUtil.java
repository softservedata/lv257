package com.softserve.edu.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * The class {@code CalcUtil} contains methods for performing simply tasks, such
 * as calculation the quantity of digits in the number, calculation the quantity
 * of multiple numbers in some number sequence, decomposition the number on sum
 * of 4 squares of numbers.
 *
 * @author IT Academy
 *
 */

public final class CalcUtil {
    /**
     * Don't let anyone instantiate this class.
     */
    private CalcUtil() {
    }

    /**
     * Returns the quantity of digits in the {@code number}.
     *
     * @param number
     *            A natural number
     * @return Quantity of digits in the {@code number}
     */
    public static int calculateDigitsInNumber(int number) {
        int count = 0;
        while (number >= 1) {
            number /= 10;
            count++;
        }
        return count;
    }

    /**
     * Returns the quantity of numbers in the sequence which are multiple
     * {@code multiple} and not multiple {@code nonMultiple}.
     *
     * @param multiple
     *            From sequence will be selected numbers which are multiple to
     *            this number
     * @param nonMultiple
     *            From sequence will be selected numbers which aren't multiple
     *            to this number
     * @param lowlim
     *            A natural number which is the start of number sequence
     * @param uplim
     *            A natural number which is the end of number sequence
     * @return Quantity of numbers which meet the conditions
     */
    public static int calculateMultiplicity(int multiple, int nonMultiple,
            int lowlim, int uplim) {
        int count = 0;
        for (int n = lowlim; n <= uplim; n++) {
            if ((n % multiple == 0) && (n % nonMultiple != 0)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the list of strings in which every line is the result of
     * decomposition {@code number} on sum of squares of four non-negative
     * numbers (Lagrange theorem).
     *
     * @param number
     *            A natural number
     * @return List of strings with all available combinations of squares
     */
    public static List<String> calculateSumOfSquares(int number) {
        List<String> listOfSquares = new ArrayList<>();
        for (int x = 0; x * x <= number; x++) {
            for (int y = 0; y * y <= number; y++) {
                for (int z = 0; z * z <= number; z++) {
                    for (int t = 0; t * t <= number; t++) {
                        if (x * x + y * y + z * z + t * t == number) {
                            listOfSquares
                                    .add(x + ", " + y + ", " + z + ", " + t);
                            continue;
                        }
                    }
                }
            }
        }
        return listOfSquares;
    }
}
