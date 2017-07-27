package com.softserve.edu.task90;

/**
 * Даны натуральные числа m и n. Найти такие натуральные p и
 q, не имеющие общих делителей, что p/q = m/n.
 */
public class Solution {

    public static void main(String[] args) {

        FindNumbers numbers = new FindNumbers();
        numbers.findNumbers(6, 4);
        numbers.findNumbers(10, 4);
        numbers.findNumbers(100, 30);
    }
}
