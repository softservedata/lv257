package com.softserve.edu.task1;

import java.util.ArrayList;
import java.util.List;

public class CalcUtil {

    public static int calculateDigitsInNumber(int number) {
	int count = 0;
	while (number >= 1) {
	    number /= 10;
	    count++;
	}
	return count;
    }

    public static int calculateMultiplicity(int lowlim, int uplim) {
	int count = 0;
	for (int n = lowlim; n <= uplim; n++) {
	    if ((n % 3 == 0) && (n % 5 != 0)) {
		count++;
	    }
	}
	return count;
    }

    public static List<String> calculateSumOfSquares(int number) {
	List<String> listOfSquares = new ArrayList<>();
	for (int x = 0; x * x <= number; x++) {
	    for (int y = 0; y * y <= number; y++) {
		for (int z = 0; z * z <= number; z++) {
		    for (int t = 0; t * t <= number; t++) {
			if (x * x + y * y + z * z + t * t == number) {
			    listOfSquares.add(x + ", " + y + ", " + z + ", " + t);
			    continue;
			}
		    }
		}
	    }
	}
	return listOfSquares;
    }
}
