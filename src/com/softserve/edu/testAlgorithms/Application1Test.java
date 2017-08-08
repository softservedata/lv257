package com.softserve.edu.testAlgorithms;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.softserve.edu.algorithms.FirstTaskWithAlgorithm;

public class Application1Test {

	@Test
	public void testFirstTaskWithAlgorithm() {
		FirstTaskWithAlgorithm test = new FirstTaskWithAlgorithm();
		int result1 = test.findNecessaryDigit(2);
		assertEquals(0, result1);
		int result2 = test.findNecessaryDigit(100);
		assertEquals(3, result2);
		int result3 = test.findNecessaryDigit(1000000);
		assertEquals(9, result3);
	}
}
