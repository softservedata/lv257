package com.softserve.edu.algorithmTasks;

/**
 * Class implements method, which prints out the N rows of Pascal's Triangle.
 * Pascal's Triangle is a numeric triangle,
 *
 *          1
 *         1 1
 *        1 2 1
 *       1 3 3 1
 *      1 4 6 4 1
 *
 * which has an '1' value on the edges,
 * and every number inside equals to sum of two numbers, which are located
 * above this number in the nearest upper row.
 *
 * @version 1.0
 * @author Yura
 *
 */
public class PascalsTriangle {

    /**
     * This method is used to print out the 'n' rows of Pascal's Triangle.
     *
     * @param n the number of rows to print
     * @return two-dimensional array with 'n' rows of Pascal's Triangle.
     */
    public int[][] getNsRowsOfPascalsTriangle(int n) {

        int[][] arr = new int[n][];
        for (int i = 0; i < arr.length; i++) {
            /*
             * Initializing 'i' row of two-dimensional array
             * with the 'i+1' number of elements according to Pascal's Triangle.
             */
            arr[i] = new int[i + 1];
            System.out.println();
            for (int j = 0; j < arr[i].length; j++) {
                /*
                 * Assigning the '1' value to elements of array
                 * on the edges.
                 */
                if ((j == 0) || (j == i)) {
                    arr[i][j] = 1;

                /*
                 * Assigning the value to elements which are inside triangle
                 * using such algorithm as: every number inside
                 * equals to sum of two numbers, which are located
                 * above this number in the nearest upper row.
                 */
                } else {
                    arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
                }
                System.out.print(arr[i][j] + " ");
            }
        }
        return arr;

    }

}
