package com.softserve.edu.testAlgorithms;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.softserve.edu.algorithms.SecondTaskWithAlgorithm;

public class Application2Test {

	@Test
	public void testSumOfSquaresOfTwoNumbers() {
		SecondTaskWithAlgorithm test = new SecondTaskWithAlgorithm();
		ArrayList<String> result1 = test.sumOfSquaresOfTwoNumbers(2);
		assertEquals("[1 and 1]", result1.toString());
		ArrayList<String> result2 = test.sumOfSquaresOfTwoNumbers(1000000);
		assertEquals("[280 and 960]",
				result2.toString());
		ArrayList<String> result3 = test.sumOfSquaresOfTwoNumbers(3);
		assertEquals("[]",
				result3.toString());
	}
}
