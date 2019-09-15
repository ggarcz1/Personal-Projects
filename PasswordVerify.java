import java.util.Scanner;

public class PasswordVerify {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String output = "Enter new password with \n-At least one uppercase letter"
				+ "\n-At least one lowercase letter\n-At least one number\n-At least "
				+ "one special character (!,@,#,$,%,&)\n-At least 8 characters long, no more than 32\nPassword:";
		System.out.print(output);
		String s = in.next();

		while (!(spacesInvalidChars(s) && conseqChars(s) && upper(s) && lower(s) && number(s) && length(s)
				&& special(s))) {
			System.err.print("\nPassword not valid.\n");
			System.out.println(output);
			s = in.next();
		}
		System.out.println("Re-enter password: ");
		while (!s.equals(in.next())) {
			System.err.print("\nERROR. PASSWORDS DO NOT MATCH\n");
			System.out.println("Re-enter password: ");
		}
		System.out.println("Sucess!");
		in.close();
	}

	public static boolean spacesInvalidChars(String s) {
		// no space at beginning or end of s
		if (s.charAt(0) == (char) 32 || s.charAt(s.length() - 1) == (char) 32)
			return false;

		// Ensure no carriage return, linefeed, /, \, or a trailing * symbol
		for (int i = 0; i < s.length(); i++)
			if ((char) 42 == s.charAt(s.length() - 1) || (char) 13 == s.charAt(i) || (char) 10 == s.charAt(i)
					|| '/' == s.charAt(i) || '\\' == s.charAt(i))
				return false;
		return true;
	}

	public static boolean conseqChars(String s) {
		// no more than two identical consecutive chars
		for (int i = 0; i < s.length() - 2; i++)
			if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i + 1) == s.charAt(i + 2))
				return false;
		return true;
	}

	public static boolean upper(String password) {
		// at least one uppercase letter
		for (int i = 0; i < password.length(); i++)
			if ('A' <= password.charAt(i) && password.charAt(i) <= 'Z')
				return true;
		return false;
	}

	public static boolean lower(String password) {
		// at least one lowercase letter
		for (int i = 0; i < password.length(); i++)
			if ('a' <= password.charAt(i) && password.charAt(i) <= 'z')
				return true;
		return false;
	}

	public static boolean number(String s) {
		// at least one character is a number
		for (int i = 0; i < s.length(); i++)
			if (('0' <= s.charAt(i) && s.charAt(i) <= '9'))
				return true;
		return false;
	}

	public static boolean special(String s) {
		// at least one special character
		return s.contains("!") || s.contains("@") || s.contains("#") || s.contains("$") || s.contains("%")
				|| s.contains("&");
	}

	public static boolean length(String s) {
		// length of string is >=8 and <=32
		int leng = s.length();
		return leng >= Math.pow(2, 3) && leng <= Math.pow(2, 5);
	}
}
