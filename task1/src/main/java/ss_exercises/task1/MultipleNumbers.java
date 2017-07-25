package ss_exercises.task1;

public class MultipleNumbers
	{
		public static int calculateMultiplicity(int lowlim, int uplim)
			{
				int count = 0;
				for (int n = lowlim; n <= uplim; n++)
					{
						if (n % 3 == 0 && n % 5 != 0)
							{
								count++;
							}
					}
				return count;
			}

		public static void main(String[] args)
			{
				int start;
				int end;
				do
					{
						System.out.println("Enter lower limit of number sequence:");
						start = InputValidator.validateInput();
						System.out.println("Enter upper limit of number sequence:");
						end = InputValidator.validateInput();
						if (start > end)
							System.out.println("Your sequence must be in ascending order. Try again.");
					} while (start > end);
				System.out.println("The quantity of numbers in your sequnce which multiple 3 and not multiple 5 is "
						+ calculateMultiplicity(start, end));
				System.exit(0);
			}
	}
