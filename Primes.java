import java.util.*;

public class Primes {
	public static void main(String args[]) {
		int count = 0;
		String str1 = "Enter number to print primes to: ", str2 = "Error. Enter numerical values only: ";

		Scanner in = new Scanner(System.in);
		System.out.println(str1);
		// validate input

		while (!in.hasNextInt()) {
			System.err.println(str2);
			System.out.println(str1);
			in.next();
		}
		int num = in.nextInt();
		if (num <= 0) {
			System.err.println("Invalid numerical value passed in (<= 1)\nExiting Program");
			System.exit(1);
		}
		// loop through each number and pass to prime method
		for (int i = 1; i <= num; i++) {
			if (isPrime(i)) {
				System.out.println(i);
				count++;
			}
		}
		System.out.println("There are " + count + " primes between 1 and " + num + ".");
		in.close();
	}

	public static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		for (int i = 2; i < n; i++)
			if (n % i == 0)
				return false;
		return true;
	}
}
