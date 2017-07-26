package com.softserve.edu.task1;

public class TheoremLagrange {
    public static void toSumOfSquares(int number) {
	for (int x = 0; x * x <= number; x++) {
	    for (int y = 0; y * y <= number; y++) {
		for (int z = 0; z * z <= number; z++) {
		    for (int t = 0; t * t <= number; t++) {
			if (x * x + y * y + z * z + t * t == number) {
			    System.out.println(x + ", " + y + ", " + z + ", " + t);
			    continue;
			}
		    }
		}
	    }
	}

    }

    public static void main(String[] args) {
	System.out.println("Enter your natural number:");
	int entered = InputValidator.validateInput();
	System.out.println("All available combinations of squares for your number:");
	toSumOfSquares(entered);
	System.exit(0);
    }
}
