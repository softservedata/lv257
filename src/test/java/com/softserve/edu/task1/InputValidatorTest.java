package com.softserve.edu.task1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test Case for InputValidator class.
 *
 * @author IT Academy
 *
 */
public class InputValidatorTest {

    /**
     * Testing work of {@code validateInput} method with positive integer number
     * as argument.
     */
    @Test
    public void testValidateInput() {
        String data = "123";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner scanner = new Scanner(System.in);
            int expected = 123;
            int actual = InputValidator.validateInput(scanner);
            Assert.assertEquals(expected, actual);
        } finally {
            System.setIn(stdin);
        }
    }

}
