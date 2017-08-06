/*
 * My Class.
 * @version 1.0
 * @date 28.07.2017
 */
package com.softserve.edu.task330;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that looks perfect numbers
 * 
 * @author Rostyk
 *
 */
public class PerfectNumber {

    /**
     * Returns a list of perfect numbers
     * 
     * @param   n the number to look for perfect numbers
     * @return  a list of perfect numbers 
     */
    public List<Integer> getPerfectNumbers(int n) {

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < n; i++) {

            List<Integer> dividers = getDividers(i);
            int sum = 0;
            for (int j = 0; j < dividers.size(); j++) {
                sum += dividers.get(j);
            }
            if (sum == i) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Returns a list of dividers
     * 
     * @param   k the number for which you need to find the dividers
     * @return  a list of dividers 
     */
    public List<Integer> getDividers(int k) {

        List<Integer> dividers = new ArrayList<>();
        for (int i = 1; i < k; i++) {

            if (k % i == 0) {
                dividers.add(i);
            }
        }
        return dividers;
    }

}
