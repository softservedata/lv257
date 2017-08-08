/*
 * My Class.
 * @version 1.0
 * @date 27.07.2017
 */
package com.softserve.edu.algorithms;
/**
 * Application2 Class.
 * @author Lv-257
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Main class for testing the methods in classes in this package.
 * @author Lv-257
 */
public final class Application2 {
/**
* default constructor.
*/
private Application2() {
}

/**
* super main.
* @param args
* <b>from command line arguments.</b>
*/
public static void main(final String[] args) {
Scanner readNumber = new Scanner(System.in);
System.out.print("Input the integer number: ");

int number = 0;
boolean wenttocatch;
do {
   try {
        wenttocatch = false;
        number = readNumber.nextInt();
        } catch (InputMismatchException e) {
        readNumber.next();
        wenttocatch = true;
  System.out.println("You entered the integer number incorrectly, "
     + "please try again.");
     }
     } while (wenttocatch);
     readNumber.close();

   SecondTaskWithAlgorithm run = new  SecondTaskWithAlgorithm();
   ArrayList<String> numberList = run.sumOfSquaresOfTwoNumbers(number);
   if (numberList.size() == 0) {
   System.out.print("The number " + number
   + " can not be represented as a sum of squares of 2 numbers");
   } else {
   System.out.println("The number " + number
   + " can be represented as a sum of squares of 2 numbers:");
   for (int i = 0; i < numberList.size(); i++) {
   System.out.println("   " + numberList.get(i) + ";"); }
    }
  }
}
