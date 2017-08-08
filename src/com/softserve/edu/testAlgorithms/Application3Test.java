package com.softserve.edu.testAlgorithms;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.softserve.edu.algorithms.ThirdTaskWithAlgorithm;

public class Application3Test {

	@Test
	public void testSumOfSquaresOfThreeNumbers() {
		ThirdTaskWithAlgorithm test = new ThirdTaskWithAlgorithm();
		ArrayList<String> result1 = test.sumOfSquaresOfThreeNumbers(3);
		assertEquals("[1 and 1 and 1]", result1.toString());
		ArrayList<String> result2 = test.sumOfSquaresOfThreeNumbers(280);
		assertEquals("[6 and 10 and 12, 6 and 12 and 10, 10 and 6 and 12, 10 and 12 and 6, 12 and 6 and 10,"
				+ " 12 and 10 and 6]",result2.toString());
		ArrayList<String> result3 = test.sumOfSquaresOfThreeNumbers(10);
		assertEquals("[]",
				result3.toString());
	}

}
