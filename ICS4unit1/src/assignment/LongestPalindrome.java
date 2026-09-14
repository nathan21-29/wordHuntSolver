package assignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Nathan Chan Sep 12, 2025
//Longest palindrome
//for every line in a plaintext file, program will find the longest palindrome in the string

public class LongestPalindrome {

	public static void main(String[] args) {
//		long startTime = System.currentTimeMillis(); //testing
		String dataOriginal;
		String data;
		String word;
		String longestPalindrome = null;
		int longestPalindromeIndex = 0;
		try { //read in word
			BufferedReader fileIn = new BufferedReader(new FileReader("data.txt"));
			while((dataOriginal = fileIn.readLine()) != null) { //for every line in the file
				data = dataOriginal.toLowerCase(); //a lot faster than doing this in the check method
				System.out.println("Finding the largest palindrome");
				int maxLength = 0; //track the length of the longest palindrome found so far
				int dataLength = data.length();
				for(int i = 0; i < dataLength - maxLength; i++) { //counts until data length - maxLength - 1 (don't need to consider any smaller strings)
					for(int j = dataLength; j > i + maxLength; j--) { //counts until i + maxLength + 1 (don't need to consider any smaller strings)
						word = data.substring(i, j);
//						System.out.println(word);
						if(checkPalindrome(word)) { //don't need to check length b/c only words of size > maxLength are checked
							longestPalindrome = dataOriginal.substring(i, j); //use original case data for display
							longestPalindromeIndex = i + 1; //convert from index to number (e.g. first letter is 1, not 0)
							maxLength = word.length();
						}
					}
				}
				System.out.println("Largest palindrome: " + longestPalindrome);
				System.out.println("Starting position: " + longestPalindromeIndex);
				System.out.println("Length: " + maxLength);
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		} catch (IOException e) {
			System.out.println("reading error");
		}
//		System.out.println("Program is complete"); //testing
//		System.out.println(System.currentTimeMillis() - startTime); //testing
	}
	
	//determines if a given string is a palindrome
	//parameters data is the string to be checked
	//returns true if data is a palindrome, and false if it is not.
	public static boolean checkPalindrome(String data) {
		int length = data.length();
		for(int i = 0; i < length / 2; i++) {
			if(data.charAt(i) != data.charAt(length - i - 1)) {
				return false;
			}
		}
		return true;
	}

}
