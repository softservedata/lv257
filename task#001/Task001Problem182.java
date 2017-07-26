/*
 * 182. Даны натуральное число  n , целые числа  a 1 , … ,  a n . Найти
* количество и сумму тех членов данной последовательности, которые
* делятся на 5 и не делятся на 7
* 
* NOT finished yet
 */
package task001Problems;

/**
 * The Class Task001_182.
 */
public class Task001Problem182 {

	/**
	 * The main method.
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		/*
		 * @param N is given natural number
		 */
		final int N = 100;
		int sumN = 0;
		int countN = 0;
		for (int i = 1; i < N; i++) {
			if (i % 5 == 0) {
				if (i % 7 != 0) {
//					System.out.println(i + "\t" + i % 5 + "\t" + i % 7);
					sumN += i;
					countN++;
				}
			}
		}
		System.out.println("Result of Problem #182:");
		System.out.println("Given natural number is " + N);
		System.out.println("sumN = " + sumN);
		System.out.println("countN = " + countN);
	}
}
