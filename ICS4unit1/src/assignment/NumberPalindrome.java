package assignment;

import java.util.Scanner;

//Nathan Chan Sep 12, 2025
//number palindrome!
//this program continually takes in integers and returns the number palindrome
//created by adding numbers with their reverses, and then adding that sum with 
//its reverse if the sum is not a palindrome. If the sum exceeds the long max
//value before becoming a palindrome, the program will say that the number does
//not become a palindrome.

public class NumberPalindrome {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);		long numOriginal;
		do {
			System.out.print("Please input a starting number (0 to quit): ");
			numOriginal = Long.parseLong(in.nextLine()); //save this value for print statement
			long num = numOriginal;
			long sum = num; //useful for the first iteration
			int steps = 0;
			boolean possiblePalindrome = true;
			
			if(!isPalindrome(numOriginal)) { //if first number is already a palidrome then do not add reverse
				do { //add until a palindrome or max value is reached
					steps++;
					num = sum; //set num equal to the sum of the last iteration
					if(num == 0) {
						break;
					}
					//check if new sum can be stored in long
					if(Long.MAX_VALUE - num < reverseNumber(num)) { //if max - num is less than the reversed number, then the sum will be >maxValue
						System.out.println(numOriginal + " does not become a palindrome");
						possiblePalindrome = false;
						break;
					}
					sum = num + reverseNumber(num); 
				} while (!isPalindrome(sum));
			}
			else if(numOriginal != 0) { //input is already a palindrome
				System.out.println(numOriginal + " is already a palindrome.");
				possiblePalindrome = false;
			}
			
			if(numOriginal != 0 && possiblePalindrome) {
				System.out.println(numOriginal + " becomes the palindrome " + sum + " after " + steps + " steps");
			}
		} while (numOriginal != 0);
		System.out.println("Program is complete");
		
		in.close();
	}
	
	//finds the length, in characters, of a long
	//parameters long num is the number to find the length of
	//returns the length in characters of a long as an int
	public static int findLength(long num) {
		//old approach, causes overflow with numbers 19 chars long (same length as long max)
//		int length = 1;
//		//find the length of num using powers of 10, I guess it also trims leading 0s
//		while(num % (long) Math.pow(10, length) != num) {
//			length++;
//		}
//		return length;
		String number = "" + num;
		return number.length();
	}
	
	//reverses a given long number
	//parameters long num is the number to be reversed
	//returns the reversed number as a long
	public static long reverseNumber(long num) {
//		int length = findLength(num);
//		long result = 0;
//		int orderOfMagnitude = length - 1; //tracks the number of zeroes to add to a given number
//		for(long i = 1; i <= Math.pow(10, length - 1); i *= 10) { //count from ones digit up
//			long temp = num % (10 * i) / i;
//			temp *= Math.pow(10, orderOfMagnitude--); //correct the order of magnitude
//			result += temp;
//		}
//		return result;
		String number = "" + num;
		String result = "";
		for(int i = number.length() - 1; i >= 0; i--) {
			result += number.charAt(i);
		}
		return Long.parseLong(result);
	}

	//checks if a number is a palindrome (i.e. the number
	//and its reverse are the same number)
	//parameters long sum is the number to be checked
	//returns true if the number is palindrome, false if not. 
	public static boolean isPalindrome(long sum) {
//		int length = findLength(sum);
//		for(int i = 0; i < length / 2; i++) {
//			//grab the ith number from the front
//			int frontNum = (int) ((sum % Math.pow(10, length - i)) / Math.pow(10,  length - i - 1));
//			//grab the ith number from the back
//			int backNum = (int) ((sum % Math.pow(10, i + 1)) / Math.pow(10,  i));
//			if(frontNum != backNum) {
//				return false;
//			}
//		}
		
		String number = "" + sum;
		for(int i = 0; i < number.length() / 2; i++) {
			if(number.charAt(i) != number.charAt(number.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
}
