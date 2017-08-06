/*
 * My Class.
 * @version 1.0
 * @date 28.07.2017
 */
package com.softserve.edu.task330;

import java.util.List;

/**
 * Test class for class PerfectNumber.
 * @author Lv-257
 */
public final class Solution {
    
    /**
     * Method main
     * @param args from command line arguments.
     */
    public static void main(final String[] args) {

        PerfectNumber perfectNumber = new PerfectNumber();
        List<Integer> numbers = perfectNumber.getPerfectNumbers(20000);
        for (Integer integer : numbers) {
            System.out.print(integer + " - ");
        }
    }
}
