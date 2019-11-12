import java.util.*;

/*
 * Gilbert Garczynski 
 * Encrypter1: A simple class that takes a string input (message)
 * reverses the message, converts it to binary, then converts it 
 * back from binary to plain text. 
*/

public class Encrypter1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String message = "";
		System.out.println("Enter a messsage to encrypt.");
		message = in.nextLine();
		// reverse message
		System.out.println("Message reversed: " + reverse(message));
		// convert to binary
		String bCode = toBinary(message);
		System.out.println("Message toBinary: " + bCode);
		// convert binary to regular text
		System.out.println("Binary to regular text: " + binaryConvert(bCode));

		in.close();
	}

	private static StringBuilder reverse(String message) {
		StringBuilder sb = new StringBuilder(message);
		return sb.reverse();
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
}
