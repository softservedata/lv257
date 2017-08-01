package com.softserve.edu.algorithmTasks;

/**
 * Main class for testing the methods in classes in this package.
 *
 * @author Yura
 *
 */
public final class MainClassAlgorithms {

    /**
     * First variable to use in testing Euclidean Algorithm.
     */
    static final int FIRST_PARAM = 15;

    /**
     * Second variable to use in testing Euclidean Algorithm.
     */
    static final int SECOND_PARAM = 6;

    /**
     * First variable to use in testing and finding
     * the number with max sum of divisors from special interval.
     * The first value of interval.
     */
    static final int START_POINT = 1;

    /**
     * Second variable to use in testing and finding
     * the number with max sum of divisors from special interval.
     * The last value of interval.
     */
    static final int END_POINT = 10000;

    /**
     * Variable to use in testing Pascal's Triangle class .
     */
    static final int ROW_TO_SHOW = 5;

    /**
     * Constructor for class.
     */
    private MainClassAlgorithms() {

    }
    /**
     * Method for testing the methods of other classes.
     * @param args Parameters from command line
     */
    public static void main(String[] args) {

        EuclideanAlgorithm ea = new EuclideanAlgorithm();
        try {
            System.out.println(ea.gcd(FIRST_PARAM, SECOND_PARAM));
            System.out.println(ea.lcm(FIRST_PARAM, SECOND_PARAM));
        } catch (RuntimeException e) {
            System.out.println("Both parameteres couldn't be equal to '0'");
            e.printStackTrace();
        }


        MaxSumOfDivisions msod = new MaxSumOfDivisions();
        System.out.println(msod.findNumberWithMaxSumOfDivisors(
                            START_POINT, END_POINT));

        PascalsTriangle pt = new PascalsTriangle();
        pt.getNsRowsOfPascalsTriangle(ROW_TO_SHOW);

    }
}
