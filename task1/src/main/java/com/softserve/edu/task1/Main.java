package com.softserve.edu.task1;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    private static void doEx1() {
	System.out.println("Enter your natural number:");
	int entered = InputValidator.validateInput(in);
	System.out.println("The quantity of digits in your number is " + CalcUtil.calculateDigitsInNumber(entered) + "\n");
    }

    private static void doEx2() {
	int start;
	int end;
	do {
	    System.out.println("Enter lower limit of number sequence:");
	    start = InputValidator.validateInput(in);
	    System.out.println("Enter upper limit of number sequence:");
	    end = InputValidator.validateInput(in);
	    if (start > end)
		System.out.println("Your sequence must be in ascending order. Try again.");
	} while (start > end);
	System.out.println("The quantity of numbers in your sequnce which multiple 3 and not multiple 5 is "
		+ CalcUtil.calculateMultiplicity(start, end) + "\n");
    }

    private static void doEx3() {
	System.out.println("Enter your natural number:");
	int entered = InputValidator.validateInput(in);
	System.out.println("All available combinations of squares for your number:");
	List<String> list = CalcUtil.calculateSumOfSquares(entered);
	for (String line : list) {
	    System.out.println(line);
	}
	System.out.println("");
    }

    public static void main(String[] args) {
	while (true) {
	    System.out.println("Choose what do you want to do:\n" + "1. Calculate quantity of digits in the number;\n"
		    + "2. Find sequence of numbers which are multiple 3 and not multiple 5;\n"
		    + "3. Decompose number on sum of 4 squares of numbers;\n" + "4. Exit.");
	    int choice = 0;
	    try {
		choice = in.nextInt();
	    } catch (InputMismatchException e) {
		in.next();
		System.out.println("There isn't such item in menu. Try again.");
		continue;
	    }
	    if (choice == 1) {
		doEx1();
	    } else if (choice == 2) {
		doEx2();
	    } else if (choice == 3) {
		doEx3();
	    } else if (choice == 4) {
		System.out.println("Bye!");
		System.exit(0);
	    } else {
		System.out.println("There isn't such item in menu. Try again.");
		continue;
	    }
	}
    }
}
