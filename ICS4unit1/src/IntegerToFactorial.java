import java.math.BigInteger;
import java.util.Scanner;

public class IntegerToFactorial {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		long number;
		do {
			System.out.print("Please enter the number of which you would like to find the factorial: ");
			number = Long.parseLong(scanner.nextLine());
			System.out.println(toFactorial(number));
		} while (number != 0);
		
		scanner.close();
	}

	public static BigInteger toFactorial(long number) {
		BigInteger result = new BigInteger("" + number);
		BigInteger multiplier;
		for(long i = number - 1; i > 1; i--) {
			multiplier = new BigInteger("" + i);
			result = result.multiply(multiplier);
		}
		
		return result;
	}
}
