package ss_exercises.task1;

import java.util.Scanner;

public class InputValidator
	{
		// Checking whether entered number is natural
		static int validateInput()
			{
				Scanner in = new Scanner(System.in);
				int input;
				do
					{
						while (!in.hasNextInt())
							{
								System.out.println("Your input data isn't number. Try again:");
								in.next();
							}
						input = in.nextInt();
						if (input <= 0)
							System.out.println("Your number isn't natural. Try again:");
					} while (input <= 0);
				return input;
			}
	}
