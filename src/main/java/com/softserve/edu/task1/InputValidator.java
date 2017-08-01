/*
 * @(#)Example.java 1.00 2017/07/28
 *
 * Copyright (c) 1993-2014 Softserve, Inc.
 *
 * This software is the confidential and proprietary information of Softserve.
 */
package com.softserve.edu.task1;

import java.util.Scanner;

/**
 * Checks that the value which entered in console from keyboard is a natural
 * number.
 *
 * @author IT Academy
 *
 */
public final class InputValidator {
    /**
     * Don't let anyone instantiate this class.
     */
    private InputValidator() {
    }

    /* Checking whether entered number is natural */
    /**
     * Checks that the data which was typed from keyboard is a natural number.
     * Repeats request for entering data until it doesn't meet the conditions.
     * If entered number is natural, returns it as {@code integer} value.
     *
     * @param in
     *            A simple text scanner which allows a user to read a number
     *            from <tt>System.in</tt>
     *
     * @return A natural number as a value of {@code integer}
     */
    public static int validateInput(final Scanner in) {
        int input;
        do {
            while (!in.hasNextInt()) {
                System.out.println("Your input data isn't number. Try again:");
                in.next();
            }
            input = in.nextInt();
            if (input <= 0) {
                System.out.println("Your number isn't natural. Try again:");
            }
        } while (input <= 0);
        return input;
    }
}
