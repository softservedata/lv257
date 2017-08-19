/*
 * My Class.
 * @version 1.0
 * @date 26.07.2017
 */
package com.softserve.edu.task90;

/**
 * Test class for class FindNumbers.
 * @author Lv-257
 */
public class Solution {

    /**
     * Method main
     * @param args from command line arguments.
     */
    public static void main(String[] args) {

        FindNumbers numbers = new FindNumbers();
        numbers.findNumbers(6, 4);
        numbers.findNumbers(10, 4);
        numbers.findNumbers(100, 30);
        numbers.findNumbers(250, 136);
        numbers.findNumbers(20154654, 136);
    }
}
