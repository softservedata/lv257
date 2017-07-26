package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task {

    public static List<Integer> task225(int number) {
        List<Integer> resultNumbers = new ArrayList<>();
        try {
            if (number <= 0) throw new IllegalArgumentException("number " + number + " isn*t Natural");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return resultNumbers;
        }

        int divider = 1;
        do {
            if ((number % Math.pow(divider, 2) == 0) && (number % Math.pow(divider, 3) != 0)) {
                resultNumbers.add(divider);
            }
            divider++;
        } while (Math.pow(divider, 2) <= number);
        return resultNumbers;
    }

    public static List<Integer> task329(int number, int requiredSum) {
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

    private static int sumOfDigitInPow(int number, int power) {
        int sum = 0;
        while (number != 0) {
            sum += Math.pow(number % 10, power);
            number = number / 10;
        }
        return sum;
    }

    public static List<Integer> task562() {
        List<Integer> resultNumbers = new ArrayList<>();
        int powerAndNumberOfDigit = 2;
        for (int i = 10; i <= 9999; i++) {
            if (i == 100) {
                powerAndNumberOfDigit = 3;
            }
            if (i == 1000) {
                powerAndNumberOfDigit = 4;
            }
            if (i == sumOfDigitInPow(i, powerAndNumberOfDigit)) {
                resultNumbers.add(i);
            }
        }
        return resultNumbers;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Natural number N for task: " +
                "Find all numbers for which N is divided by number in power 2 but not in power 3:");
        int naturalNumber = in.nextInt();
        System.out.println("Numbers:" + task225(naturalNumber));

        System.out.println("Enter Natural number N and required square sum for task: Find all numbers," +
                " which are less than N and sum of their digit in square100 is equals required sum");
        System.out.println("Enter Natural number N:");
        naturalNumber = in.nextInt();
        System.out.println("Enter required sum:");
        int requiredSum = in.nextInt();
        System.out.println("Numbers:" + task329(naturalNumber, requiredSum));

        System.out.println("Task: Find all Armstrong's numbers in range 2/3/4-digit numbers:" + task562());


    }
}
