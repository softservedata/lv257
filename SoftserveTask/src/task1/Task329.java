package task1;

import java.util.ArrayList;
import java.util.List;

import static task1.Helper.sumOfDigitInPow;

public class Task329 {
    private Task329(){}

    /**
     * Find all numbers which are less than N and sum of their digit in square is equals required sum.
     * @param number Natural N
     * @param requiredSum required sum of number's digit
     * @return list of all found numbers
     */
    public static List<Integer> getSpecialNumbers(int number, int requiredSum) {
        List<Integer> resultNumbers = new ArrayList<>();
        try {
            if (number <= 0) {
                throw new IllegalArgumentException("number " + number + " isn*t Natural");
            }
            if (requiredSum <= 0) {
                throw new IllegalArgumentException("number " + requiredSum + " isn*t Natural");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return resultNumbers;
        }
        int checkedNumber = 1;
        while (checkedNumber < number) {
            if (Math.pow(sumOfDigitInPow(checkedNumber, 1), 2) == requiredSum) {
                resultNumbers.add(checkedNumber);
            }
            checkedNumber++;
        }
        return resultNumbers;
    }
}
