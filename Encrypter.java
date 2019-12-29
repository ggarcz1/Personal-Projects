import java.util.*;

/*
 * Gilbert Garczynski 
 * Encrypter1: A simple class that takes a string input (message)
 * reverses the message, converts it to binary, then converts it 
 * back from binary to plain text. 
*/

public class Encrypter {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String message = "";
		System.out.println("Enter a messsage to encrypt.");
		message = in.nextLine();
		
		// reverse message
		StringBuilder sb = new StringBuilder(message);
		System.out.println("Message reversed: " + sb.reverse());
		
		// convert to binary
		String bCode = toBinary(message);
		System.out.println("Message toBinary: " + bCode);
		
		// convert binary to regular text
		System.out.println("Binary to regular text: " + binaryConvert(bCode));

		// add 13 to ascii value, multiply by 453641, a large prime number
		System.out.println("Crypt1: " + crypt1(message));
		
		// multiply by predetermined string's hash code "kU751^01hsnH?"
		System.out.println("Crypt2: " + crypt2(message));

		// Math.pow(value, 3)*(prime), prime is 13 in this case
		System.out.println("Crypt3: " + crypt3(message));

		bravo.close();
	}

	private static String toBinary(String message) {
		if (message == null || message == "")
			return "no message found\n";
		String s = "";
		message.replace(" ", "");
		for (int i = 0; i < message.length(); i++)
			s += "0" + Integer.toBinaryString(message.charAt(i)) + " ";
		return s;
	}

	private static String binaryConvert(String message) {
		if (message == null || message == "")
			return "no input found\n";
		String s = "";
		int total = 0;
		String[] sArr = message.split(" ");
		for (int i = 0; i < sArr.length; i++) {
			total = Integer.parseInt(sArr[i], 2);
			s += (char) total;
		}
		return s;
	}

	// add 13 to ascii value, multiply by 453641, a prime number
	private static String crypt1(String message) {
		if (message == null || message == "")
			return "no input found\n";
		String s = "";
		for (int i = 0; i < message.length(); i++) {
			s += (((int) message.charAt(i)) + 13) * 453641 + " ";
		}
		return s;
	}

	// multiply by predetermined string's hash code "kU751^01hsnH?"
	private static String crypt2(String message) {
		if (message == null || message == "")
			return "no input found\n";
		String s = "", muS = "kU751^01hsnH?";
		int mult = muS.hashCode();
		for (int i = 0; i < message.length(); i++) {
			s += (((int) message.charAt(i)) + 13) * mult + " ";
		}
		return s;
	}

	// Math.pow(value, 3)*(prime)
	private static String crypt3(String message) {
		if (message == null || message == "")
			return "no input found\n";
		String s = "";
		for (int i = 0; i < message.length(); i++) {
			s += (long)Math.pow((double) message.charAt(i), 3) * (13 * 13) + " ";
		}
		return s;
	}
}
