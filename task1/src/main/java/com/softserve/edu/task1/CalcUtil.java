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
     * @throws IllegalArgumentException
     *             if argument isn't positive integer number
     */
    public static int calculateDigitsInNumber(int number)
            throws IllegalArgumentException {
        if (number <= 0) {
            throw new IllegalArgumentException(
                    "Argument must be a non-negative number");
        }
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
     * @throws IllegalArgumentException
     *             if arguments aren't positive integer numbers or number
     *             sequence isn't in ascending order
     */
    public static int calculateMultiplicity(int multiple, int nonMultiple,
            int lowlim, int uplim) throws IllegalArgumentException {
        if (multiple <= 0 || nonMultiple <= 0 || lowlim <= 0 || uplim <= 0) {
            throw new IllegalArgumentException(
                    "Arguments must be a non-negative numbers");
        }
        if (lowlim > uplim) {
            throw new IllegalArgumentException(
                    "Sequence of numbers must be in ascending order");
        }
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
     * @throws IllegalArgumentException
     *             if argument isn't positive integer number
     */
    public static List<String> calculateSumOfSquares(int number)
            throws IllegalArgumentException {
        if (number <= 0) {
            throw new IllegalArgumentException(
                    "Argument must be a non-negative number");
        }
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
