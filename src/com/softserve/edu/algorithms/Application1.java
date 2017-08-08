/*
 * Application1 Class.
 * @version 1.0
 * @date 27.07.2017
 */
package com.softserve.edu.algorithms;

/**
 * Main class for testing the methods in classes in this package.
 * @author Lv-257
 */
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * @author Lv-257
 */
public final class Application1 {
/**
 * default constructor.
 */
private Application1() {
}
/**
* super main.
* @param args
* <b>from command line arguments.</b>
*/
   public static void main(final String[] args) {
    Scanner readNumber = new Scanner(System.in);
    System.out.print("Input the integer number: ");
    int m = 0;
    boolean wenttocatch;
  do {
     try {
     wenttocatch = false;
     m = readNumber.nextInt();
     } catch (InputMismatchException e) {
     readNumber.next();
     wenttocatch = true;
     System.out.println("You entered the integer number incorrectly, "
     + "please try again.");
     }
     } while (wenttocatch);
     readNumber.close();
     FirstTaskWithAlgorithm run = new FirstTaskWithAlgorithm();
     System.out.println("The largest integer number k, "
    + "for which the condition 4 is in power k less than m, is: "
    + run.findNecessaryDigit(m));
   }
}
