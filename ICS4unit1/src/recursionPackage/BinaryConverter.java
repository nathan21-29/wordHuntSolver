package recursionPackage;

import java.util.Scanner;

public class BinaryConverter {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a binary number: ");
		String binaryNumber = scanner.nextLine();
		System.out.println(binaryToBase10(binaryNumber, 0));
	}

	public static int binaryToBase10(String binaryNumber, int index) {
		if(index == binaryNumber.length()) {
			return 0;
		}
		if(Integer.parseInt("" + binaryNumber.charAt(binaryNumber.length() - 1 - index)) == 0) {
			return 0 + binaryToBase10(binaryNumber, ++index);
		}
		else if(Integer.parseInt("" + binaryNumber.charAt(binaryNumber.length() - 1 - index)) == 1) {
			return (int)Math.pow(2, index) + binaryToBase10(binaryNumber, ++index);
		}
		return 0;
	}
}
