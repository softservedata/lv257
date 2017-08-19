/*
 * My Class.
 * @version 1.0
 * @date 26.07.2017
 */
package com.softserve.edu.task90;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that looks perfect numbers
 * 
 * @author Rostyk
 *
 */
public class FindNumbers {

    /**
     * A constant holding the maximum value to look for.
     */
    private final int MAX_INTEGER = 2000000;
    
    /**
     * The first number to find
     */
    private int p;
    
    /**
     * The second number to find
     */
    private int q;

    
    /**
     * Prints to console numbers 'p' and 'q'
     * 
     * @param   m the number
     * @param   n the number
     */
    public void findNumbers(int m, int n) {

        List<Integer> dividersOfI; /*Dividers of the first number*/
        List<Integer> dividersOfJ; /*Dividers of the second number*/

        for (int i = 1; i <= MAX_INTEGER; i++) {
            for (int j = 1; j <= MAX_INTEGER; j++) {

                int coef1 = (m * i);
                int coef2 = (n * j);
                if (coef1 == coef2) {
                    dividersOfI = getDividers(i);
                    dividersOfJ = getDividers(j);
                    boolean flag = checkDividers(dividersOfI, dividersOfJ);
                    if (flag) {
                        p = j;
                        q = i;
                        System.out.println("p = " + p + "; q = " + q);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Check dividers of two numbers
     * 
     * @param List<Integer> dividersOfI  Dividers of the first number
     * @param List<Integer> dividersOfI  Dividers of the second number
     * @return true if two numbers do not have common dividers
     */
    public boolean checkDividers(List<Integer> dividersOfI, List<Integer> dividersOfJ) {

        int dividerI;
        int dividerJ;
        for (int k = 0; k < dividersOfI.size(); k++) {

            dividerI = dividersOfI.get(k);
            for (int l = 0; l < dividersOfJ.size(); l++) {

                dividerJ = dividersOfJ.get(l);
                if (dividerI == dividerJ){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a list of dividers
     * 
     * @param   k the number for which you need to find the dividers
     * @return  a list of dividers 
     */
    public List<Integer> getDividers(int k) {

        List<Integer> dividers = new ArrayList<>();
        for (int i =2; i < k; i++){

            if (k % i == 0){
                dividers.add(i);
            }
        }
        return dividers;
    }
    
    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }
}
