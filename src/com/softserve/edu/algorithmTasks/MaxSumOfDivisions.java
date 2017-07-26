package com.softserve.edu.algorithmTasks;

import java.util.Set;
import java.util.TreeSet;

public class MaxSumOfDivisions {

	// method for finding number with maximum sum of divisors from special length of int numbers
	static int findNumberWithMaxSumOfDivisorsFromLength(int startPoint, int endPoint) {

		int[] ar1 = new int[((endPoint - startPoint) + 1) / 2];
		int startNumber = startPoint;
		if (startPoint % 2 == 0) {
			for (int i = 0; i < ar1.length; i++) {
				ar1[i] = startNumber += 2;
			}
		} else {
			startNumber = startPoint + 1;
			for (int i = 0; i < ar1.length; i++) {
				ar1[i] = startNumber += 2;
			}
		}

		// ASK about divisors length of numbers  
		// now using endPoint number from 1 to endPoint)

		System.out.println();
		int maxSumOfDivisions = 0;
		int currentSumOfDivisions = 0;
		int numberWithMaxSumOfDivisions = 0;
		Set<Integer> tempSet = new TreeSet<>();
		boolean newDivisionsExists = true;
		for (int i = 0; i < ar1.length; i++) {
			/* how many divisors to check, what length from 1 to N>? instead of endPoint, because, for me it`s not effective */
			 for (int j = 1; j < endPoint; j++) {
				if (ar1[i] % j == 0) {
					for (int integer : tempSet) {
						if (ar1[i] / j == integer || j == integer) {
							newDivisionsExists = false;
						}
					}
					if (!newDivisionsExists) {
						break;
					}
					tempSet.add(ar1[i] / j);
					tempSet.add(j);
				}

			}
			for (Integer integer : tempSet) {
				currentSumOfDivisions += integer;
			}
			if (currentSumOfDivisions > maxSumOfDivisions) {
				maxSumOfDivisions = currentSumOfDivisions;
				numberWithMaxSumOfDivisions = ar1[i];
			}
			currentSumOfDivisions = 0;
			tempSet.clear();
			newDivisionsExists = true;
		}

		System.out.println(maxSumOfDivisions);
		return numberWithMaxSumOfDivisions;

	}

	public static void main(String[] args) {

		// using static method above
		System.out.println("Number with a maximum sum of divisors: " + findNumberWithMaxSumOfDivisorsFromLength(1, 10000));

		// some example to test
		int[] ar1 = new int[5000];
		int number1 = 0;
		for (int i = 0; i < ar1.length; i++) {
			ar1[i] = number1 += 2;
		}
		System.out.println(ar1[4999]);
		// TODO: what quantity of N divisors in length from 1 to N-i,
		// (in this example I`m using 200 divisors, from 1 to 200)
		// should we check for Integers of special length, in this case from 1 - 10000)
		
		int[] ar2 = new int[200];
		int number2 = 1;

		for (int i = 0; i < ar2.length; i++) {
			ar2[i] = number2++;
		}

		System.out.println();
		int maxSumOfDivisions = 0;
		int currentSumOfDivisions = 0;
		int numberWithMaxSumOfDivisions = 0;
		Set<Integer> tempSet = new TreeSet<>();

		for (int i = 0; i < ar1.length; i++) {
			for (int j = 0; j < ar2.length; j++) {
				if (ar1[i] % ar2[j] == 0) {
					tempSet.add(ar1[i] / ar2[j]);
					tempSet.add(ar2[j]);
				}

			}
			for (Integer integer : tempSet) {
				currentSumOfDivisions += integer;
			}
			if (currentSumOfDivisions > maxSumOfDivisions) {
				maxSumOfDivisions = currentSumOfDivisions;
				numberWithMaxSumOfDivisions = ar1[i];
			}
			currentSumOfDivisions = 0;
			tempSet.clear();
		}

		System.out.println("Number with a maximum sum of divisors: " + numberWithMaxSumOfDivisions);
		System.out.println("Sum of divisors of an Integer" + numberWithMaxSumOfDivisions + ": " + maxSumOfDivisions);
		Set<Integer> setOfDivisions = new TreeSet<>();

		for (int i = 0; i < ar2.length; i++) {
			if (numberWithMaxSumOfDivisions % ar2[i] == 0) {
				setOfDivisions.add(numberWithMaxSumOfDivisions / ar2[i]);
				setOfDivisions.add(ar2[i]);
				// System.out.print(numberWithMaxSumOfDivisions/ar2[i] + ", " +
				// ar2[i] + ", ");
			}
		}
		System.out.println("All divisors of integer " + numberWithMaxSumOfDivisions + ":" + setOfDivisions);
		System.out.println("Quantity of divisors " + setOfDivisions.size());

		int sum = 0;
		for (Integer integer : setOfDivisions) {
			sum += integer;
		}
		System.out.println(sum);

	}

}
