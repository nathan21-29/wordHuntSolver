import java.math.BigInteger;
import java.util.Scanner;

public class BigFractions {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the first numerator: ");
		BigInteger numerator1 = new BigInteger(scanner.nextLine());
		System.out.print("Enter the second numerator: ");
		BigInteger numerator2 = new BigInteger(scanner.nextLine());
		System.out.print("Enter the first denominator: ");
		BigInteger denominator1 = new BigInteger(scanner.nextLine());
		System.out.print("Enter the first denominator: ");
		BigInteger denominator2 = new BigInteger(scanner.nextLine());
		
		System.out.println(addFractions(numerator1, numerator2, denominator1, denominator2));
		
		scanner.close();
	}
	
	//adds 2 BigInteger fractions
	//parameters numerator1 and numerator2 are BigNumbers representing
	//the numerators of both respective fractions, denominator1 and 
	//denomintor2 are BigNumbers representing the denominators of both
	//respective fractions
	//returns a string representing the sum of the fractions in the form
	//numerator / denominator
	public static String addFractions(BigInteger numerator1, BigInteger numerator2, BigInteger denominator1, BigInteger denominator2) {
		String result = "";
		BigInteger orignalDenominator1 = denominator1;
		BigInteger orignalDenominator2 = denominator2;
		BigInteger orignalNumerator1 = numerator1;
		BigInteger orignalNumerator2 = numerator2;
		
		//find LCM
		while(!denominator1.equals(denominator2)) {
			if(denominator1.min(denominator2) == denominator1) { //denominator1 is smaller
				//add original numerator and denominator to fraction1
				numerator1 = numerator1.add(orignalNumerator1);
				denominator1 = denominator1.add(orignalDenominator1);
			}
			else { //denominator2 is smaller
				//add original numerator and denominator to fraction1
				numerator2 = numerator2.add(orignalNumerator2);
				denominator2 = denominator2.add(orignalDenominator2);
			}
		}
		//denominator1 and denominator2 are both LCM
		result += (numerator1.add(numerator2));
		result += " / ";
		result += denominator1;
		
		return result;
	}
}
