package com.softserve.edu.numbers;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NaturalNumbers {

	public static void main(String[] args) {
		int N = 0;
		if (args.length > 0) {
			try {
				N = Integer.parseInt(args[0]);
				List<Integer> factors = factorize(N);
				System.out.printf("dividers of %d are:%n%s%n", N, factors);
				List<Integer> primeFactors = getPrimeFactors(N);
				System.out.printf("prime dividers of %d are:%n%s%n", N, primeFactors);
				Map<Integer, Integer> squaresWithSuffixes = getNumbersWhereBaseIsSuffixOfItsSquare(N);
				System.out.println("Numbers whose square contains the number as a suffix:");
				squaresWithSuffixes.entrySet()
						.forEach(entry -> System.out.printf("%d -> %d%n", entry.getKey(), entry.getValue()));
			} catch (Exception e) {
				System.out.printf("%s - %s", e, e.getMessage());
			}
		} else
			System.out.println("run java NaturalNumbers <number>");
	}

	// returns natural numbers which are suffixes of it's square, i.e. 6 -> 36 (6^2)
	private static Map<Integer, Integer> getNumbersWhereBaseIsSuffixOfItsSquare(int bound) {
		int digitCount = countSignificantDigits(bound);
		return Stream.iterate(new int[]{1, 1}, ints -> new int[] {++ints[0], ints[0]*ints[0]})
				       .limit(bound)
				       .filter(ints -> ints[0] == getLeastSignificantDigits(ints[1], countSignificantDigits(ints[0])))
				       .collect(Collectors.toMap(ints -> ints[0], ints -> ints[1]));
	}

	private static int getLeastSignificantDigits(int number, int lsdCount) {
		return (int) (number % Math.pow(10, lsdCount));
	}

	private static int countSignificantDigits(int bound) {
		int n = 1;
		int limit = bound;
		while ((limit /= 10) != 0) n++;
		return n;
	}

	// returns all natural dividers (factors) of given natural number
	private static List<Integer> factorize(int n) {
		if (n < 1) throw new IllegalArgumentException("Must be a positive natural number");
		return IntStream.rangeClosed(1, n)
				       .filter(i -> n % i == 0)
				       .boxed()
				       .collect(Collectors.toList());
	}

	// returns all prime dividers of given natural number
	private static List<Integer> getPrimeFactors(int n) throws IllegalArgumentException {
		if (n < 1) throw new IllegalArgumentException("Must be a positive natural number");
		return factorize(n).stream()
				       .filter(integer -> BigInteger.valueOf(integer).isProbablePrime(10))
				       .collect(Collectors.toList());
	}


}
