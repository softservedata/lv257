/*
 * My Class.
 * @version 1.0
 * @date 27.07.2017
 */
package com.softserve.edu.task88b;

/**
 * Class that reverses the number
 * 
 * @author Rostyk
 *
 */
public class Reverse {

    /**
     * Object type StringBuilder in which the 
     * reverse number will be stored
     */
    private StringBuilder builder = new StringBuilder();
    
    /**
     * Return number with reverse order of digits
     * 
     * @param   n the number to be reversed 
     * @return  reverse number as object String
     */
    public String revers(int n){

        int result = 0;
        while (n > 0) {
            result = n%10;
            n = n/10;
            builder.append(result);
        }

        return String.valueOf(builder);
    }
}
