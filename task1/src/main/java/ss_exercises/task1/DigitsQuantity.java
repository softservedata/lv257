package ss_exercises.task1;

public class DigitsQuantity
	{
		//Calculation using loop
		public static int calculateDigits(int number)
			{
				int count = 0;
				while (number >= 1)
					{
						number /= 10;
						count++;
					}
				return count;
			}

		//Calculation using String class
		/*	    public static int calculateDigits(int number)
			{
				return String.valueOf(number).length();
			}*/

		public static void main(String[] args)
			{
				System.out.println("Enter your natural number:");
				int entered = InputValidator.validateInput();
				System.out.println("The quantity of digits in your number is " + calculateDigits(entered));
				System.exit(0);
			}
	}
