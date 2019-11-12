import java.util.*;

public class Encrypter1 {
	public static void main(String args[]) {
		Scanner bravo = new Scanner(System.in);
		String message = "";
		System.out.println("Enter a messsage to encrypt.");
		message = bravo.nextLine();
		// reverse message
		StringBuilder sb = new StringBuilder(message);
		System.out.println("Message reversed: " + sb.reverse());
		// convert to binary
		String bCode = toBinary(message);
		System.out.println("Message toBinary: " + bCode);
		// convert binary to regular text
		System.out.println("Binary to regular text: " + binaryConvert(bCode));

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
}