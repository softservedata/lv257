package task1;

import java.util.Scanner;

public class Main {

    private Main(){}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Natural number N for task: " +
                "Find all numbers for which N is divided by number in power 2 but not in power 3:");
        int naturalNumber = in.nextInt();
        System.out.println("Numbers:" + Task225.getSpecialDivider(naturalNumber));

        System.out.println("Enter Natural number N and required square sum for task: Find all numbers," +
                " which are less than N and sum of their digit in square is equals required sum");
        System.out.println("Enter Natural number N:");
        naturalNumber = in.nextInt();
        System.out.println("Enter required sum:");
        int requiredSum = in.nextInt();
        System.out.println("Numbers:" + Task329.getSpecialNumbers(naturalNumber, requiredSum));

        System.out.println("task: Find all Armstrong's numbers in range 2/3/4-digit numbers:"
                + Task562.getArmstrongsNumbers(10,9999));
    }
}
