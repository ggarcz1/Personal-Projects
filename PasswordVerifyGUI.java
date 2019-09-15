import java.util.*;
import javax.swing.*;

/*
 * Gilbert Garczynski 
 * Password Verify GUI: A simple class that takes a string input 
 * and validates that said input string has the correct characters
 *  and number of characters in it to be an effective, secure password
 *  This is the GUI Version 
*/
public class PasswordVerifyGUI {
	public static void main(String[] args) {
		String output = "Enter new password with \n-At least one uppercase letter"
				+ "\n-At least one lowercase letter\n-At least one number\n-At least "
				+ "one special character (!,@,#,$,%,&)\n-At least 8 characters long, no more than 32\nPassword:";
		String s = JOptionPane.showInputDialog(output);

		while (!(spacesInvalidChars(s) && conseqChars(s) && upper(s) && lower(s) && number(s) && length(s)
				&& special(s))) {
			s = JOptionPane.showInputDialog(null, "\nPassword not valid.\n" + output, "", JOptionPane.WARNING_MESSAGE);

		}
		String confirm = JOptionPane.showInputDialog("Re-enter password: ");
		while (!s.equals(confirm)) {
			confirm = JOptionPane.showInputDialog(null, "ERROR. PASSWORDS DO NOT MATCH\nRe-enter password:", "",
					JOptionPane.WARNING_MESSAGE);

		}
		JOptionPane.showMessageDialog(null, "Sucess", "", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean spacesInvalidChars(String s) {
		// no space at beginning or end of s
		if (s.charAt(0) == (char) 32 || s.charAt(s.length() - 1) == (char) 32)
			return false;

		// Make sure no carraige return, linefeed, /, \, or a trailing * symbol
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
		for (int i = 0; i < password.length(); i++)
			if ('A' <= password.charAt(i) && password.charAt(i) <= 'Z')
				return true;
		return false;
	}

	public static boolean lower(String password) {
		for (int i = 0; i < password.length(); i++)
			if ('a' <= password.charAt(i) && password.charAt(i) <= 'z')
				return true;
		return false;
	}

	public static boolean number(String s) {
		for (int i = 0; i < s.length(); i++)
			if (('0' <= s.charAt(i) && s.charAt(i) <= '9'))
				return true;
		return false;
	}

	public static boolean special(String s) {
		return s.contains("!") || s.contains("@") || s.contains("#") || s.contains("$") || s.contains("%")
				|| s.contains("&");
	}

	public static boolean length(String s) {
		int leng = s.length();
		return leng >= Math.pow(2, 3) && leng <= Math.pow(2, 5);
	}
}
