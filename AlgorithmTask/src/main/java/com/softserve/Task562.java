package com.softserve;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.Helper.numberOfDigit;
import static com.softserve.Helper.sumOfDigitInPow;

public class Task562 {
    private Task562() {
    }

    /**
     * Find all Armstrong's numbers.
     *
     * @param startNumber number from which will be starts search
     * @param endNumber   number to which will be continue searh
     * @return list of found numbers
     */
    public static List<Integer> getArmstrongsNumbers(int startNumber, int endNumber) {
        List<Integer> resultNumbers = new ArrayList<Integer>();
        for (int i = startNumber; i <= endNumber; i++) {
            if (i == sumOfDigitInPow(i, numberOfDigit(i))) {
                resultNumbers.add(i);
            }
        }
        return resultNumbers;
    }
}
