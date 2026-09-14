package recursion1;

import java.util.Scanner;

public class RecursionExercises1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int question;
		do {
			System.out.print("Enter the question to be tested (0 to quit): ");
			question = Integer.parseInt(scanner.nextLine());
			if(question == 1) {
				System.out.print("How many customers are there? ");
				int customers = Integer.parseInt(scanner.nextLine());
				if(customers < 0) {
					System.out.println("You cannot have negative customers!!!");
				}
				else {
					System.out.println(bubbles(customers) + " scoops of bubbles"
							+ " are given out that day.");
				}
			}
			else if(question == 2) {
				System.out.print("Please enter the value of n: ");
				int data = Integer.parseInt(scanner.nextLine());
				if(data == 0) {
					System.out.println("Please enter a non-zero integer.");
				}
				else {
					System.out.println("The result is:\n" + sumDiff(data));
				}
			}
			else if(question == 3) {
				System.out.print("Enter the first number: ");
				int num1 = Integer.parseInt(scanner.nextLine());
				System.out.print("Enter the second number: ");
				int num2 = Integer.parseInt(scanner.nextLine());
				System.out.println("The product of the 2 numbers is:\n" + multiply(num1, num2));
			}
			else if(question == 4) {
				System.out.print("Enter the phrase: ");
				System.out.println("There are " + find(scanner.nextLine()) + " uppercase or non-alphabetic characters.");
			}
			else if(question == 5) {
				System.out.print("Please enter a phrase: ");
				String phrase = scanner.nextLine();
				System.out.print("Please enter a character to insert: ");
				char symbol = scanner.nextLine().charAt(0);
//				System.out.println(symbol);
				System.out.println("The result is: \n" + insert(phrase, symbol));
			}
			else if(question == 6) {
				System.out.print("Please enter a number: ");
				System.out.println(commas(Integer.parseInt(scanner.nextLine())));
			}
			else if(question == 7) {
				String[] data = {"1.22222"};
				if(data.length == 0) {
					System.out.println("Error: empty array.");
				}
				else {
					StringBuilder result = new StringBuilder(data.length);
					for(int i = 0; i < data.length; i++) {
						result.append(data[i] + ", ");
					}
					result.delete(result.length() - 2, result.length());
					System.out.println("Listed below are the contents of the array:\n" + result + "\nPress ENTER to continue.");
					scanner.nextLine();
					System.out.println("The average of all numbers is: " + numAvg(data, data.length - 1, 0, 0));
				}
			}
			else {
				System.out.println("Invalid question, please input a number from 1 - 7 inclusive.");
			}
		} while (question != 0);
		
		scanner.close();
	}

	//recursive method that calculates the amount of bubbles given out on a day
	//int customers is the number of customers served on that day
	//returns an int # of scoops of bubbles given out that day
	public static int bubbles(int customers) {
		if(customers == 0) {
			return 0;
		}
		if(customers % 10 == 0) { //10th customer
			return 3 + bubbles(customers - 1);
		}
		else if(customers % 2 == 1) { //odd # customers
			return 2 + bubbles(customers - 1);
		}
		else { //even # customers
			return 1 + bubbles(customers - 1);
		}
	}
	
	//recursive method that calculates the sum of every reciprocal from denominator to 1
	//int denominator represents the number n
	//returns the sum of all reciprocals as a double
	public static double sumDiff(int denominator) {
		//base cases
		if(denominator == 1) { //last term with odd denominators
			return 1; // 1/1
		}
		else if(denominator == -1) {
			return -1;
		}
		else if(denominator < 1 && denominator > -1) { //invalid number, absolute value of denominator can be from 1 inclusive to infinity
			return 0;
		}
//		System.out.println("1/" + denominator);
		int nextNumber;
		if(denominator > 0) {
			nextNumber = denominator - 2;
		}
		else { //denominator < 0
			nextNumber = denominator + 2;
		}
		if(denominator % 2 == 1 || denominator % 2 == -1) { //odd denominator 
			return Math.round((1 / (double) denominator + sumDiff(nextNumber)) * 100000) / 100000.0;
		}
		else { //denominator % 2 == 0
			return Math.round((-1 / (double) denominator + sumDiff(nextNumber)) * 100000) / 100000.0;
		}
	}
	
	//multiplies 2 numbers through repeated addition (no multiplication)
	//int constant is the first factor, int counter is the second factor
	//returns an int which is the product of constant and counter
	public static int multiply(int constant, int counter) {
		if(counter == 0 || constant == 0) { //zero product
			return 0;
		}
		else if(counter == 1) {
			return constant;
		}
		else if(counter == -1) {
			return -constant;
		}
		if(counter > 0) {
			return constant + multiply(constant, counter - 1);
		}
		else { //counter < 0
			return -constant + multiply(constant, counter + 1);
		}
	}
	
	//finds the number of capital letters & non alphabetical chars in a string
	//String phrase is the String to be counted
	//returns int number of the sum of # capital letters & # non alphabetical chars 
	public static int find(String phrase) {
		if(phrase.equals("")) {
			return 0;
		}
		else if(Character.isUpperCase(phrase.charAt(0)) || !Character.isAlphabetic(phrase.charAt(0))) {
			return 1 + find(phrase.substring(1));
		}
		else {
			return 0 + find(phrase.substring(1));
		}
	}

	//inserts a chosen char between all consecutive identical characters
	//String sentence is the String to be checked, char symbol is the char to be inserted
	//returns a String with all non alphabetical chars removed with symbol between identical letters
	public static String insert(String sentence, char symbol) {
		if(sentence.length() == 0) { //check empty string first
			return sentence;
		}
		if(!Character.isAlphabetic(sentence.charAt(0))) { //remove it
			return insert(sentence.substring(1), symbol);
		}
		if(sentence.length() >= 2 && !Character.isAlphabetic(sentence.charAt(1))) { //remove non alphabetic chars continuously until chars 0 and 1 are both alphabetic
			sentence = sentence.charAt(0) + sentence.substring(2);
			return insert(sentence, symbol);
		}
		if(sentence.length() == 1) { //if this statement is at the top, the last char is skipped for remove checking
			return sentence;
		}
		if(Character.toLowerCase(sentence.charAt(0)) == Character.toLowerCase(sentence.charAt(1))) {
			return "" + sentence.charAt(0) + symbol + insert(sentence.substring(1), symbol); 
			//empty quote at the start or else the ascii's of char 1 and symbol are added
		}
		else { //keep the char if it is alphabetic
			return sentence.charAt(0) + insert(sentence.substring(1), symbol);
		}
	}
	
	//formats an integer with commas and sign
	//int number is the number to be formatted
	//returns a String with the formatted number
	public static String commas(int number) {
		if(number / 1000 == 0) { //less than or equal to 3 digits in the number, decide sign
			if(number == 0) {
				return "" + Math.abs(number);
			}
			else if(number > 0) {
				return "+" + Math.abs(number);
			}
			else {
				return "-" + Math.abs(number);
			}
		}
		return commas(number / 1000) + "," + String.format("%03d", Math.abs(number) % 1000);
	}
	
	//calculates the average of all valid doubles in an array
	//String[] data is the array from which values are read in, int index is the index of the element
	//of data being read (starts at last element), int validNumbers is the count of valid numbers in the array (used for final calculation)
	//, double currentSum is the sum of all valid numbers
	//returns the average as a string to ensure 2 decimals of precision for integer-equivalent averages
	//(e.g. 2 -> 2.00 with String.format whereas with the math rounding method 2 -> 2.0)
	public static String numAvg(String[] data, int index, int validNumbers, double currentSum) {
		if(index < 0) {
			return "ERROR: Empty array.";
		}
		if(index == 0) { //last iteration
				try {
					currentSum += Double.parseDouble(data[index]);
					//if valid double has been read in, increment validNumbers
					validNumbers++; 
				} catch (NumberFormatException e) {
					//no need to do anything, invalid numbers are expected
				}
				if(validNumbers == 0) {
					return "ERROR: There were no valid numbers in the array.";
				}
				return String.format("%.2f", currentSum / validNumbers); //this way rounds integers to .00 instead of .0
		}
		else {
			try {
				currentSum += Double.parseDouble(data[index]);
				//if valid double has been read in, increment validNumbers
				validNumbers++; 
			} catch (NumberFormatException e) {
				//no need to do anything, invalid numbers are expected
			}
			return numAvg(data, index - 1, validNumbers, currentSum);
		}
	}
}

