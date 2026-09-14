package recursion2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class WordMaze1and2 {
	//Nathan Chan Oct 6, 2025
	//Word Maze!
	//this program prompts for a mode (either 1 or 2).
	//if mode 1 is chosen, the program will search for a list of words in a char grid
	//in any direction as long as no letter is used twice, all read in from a file.
	//mode 1 is comparable to the imessage gamepigeon game word hunt.
	//mode 2 also reads in a grids and words from a text file, but instead confines its
	//search rules such that the chars must appear in a line, more similar to a traditional
	//word search.

	static boolean wordCompleted = false;
	public static void main(String[] args) {
		try {
			Scanner fileIn = new Scanner(new File("wordMaze.txt"));
			int mazeCount = Integer.parseInt(fileIn.nextLine());
			int mode = 0;
			boolean validInput;
			do {
				validInput = true;
				try {
					Scanner userInput = new Scanner(System.in);
					System.out.print("Enter 1 for WordMaze1, or 2 for WordMaze2: ");
					mode = Integer.parseInt(userInput.nextLine());
					if(mode > 2 || mode < 1) { //number falls out of range
						throw new NumberFormatException(); //dont close system.in prematurely
					}
					userInput.close();
				} catch (NumberFormatException e) {
					validInput = false;
					System.out.println("Invalid number. Input again.");
				}
			} while (!validInput);
			for(int mazeNum = 0; mazeNum < mazeCount; mazeNum++) {
				System.out.println("Grid #" + (mazeNum + 1) + ":");
				int mazeSize = Integer.parseInt(fileIn.nextLine());
				char[][] maze = new char[mazeSize][mazeSize]; //put the maze in an array
				for(int j = 0; j < mazeSize; j++) { //for every row
					String word = fileIn.nextLine();
					System.out.println(word);
					for(int k = 0; k < mazeSize; k++) {
						maze[j][k] = word.charAt(k); 
					}
				}
				System.out.println(); //clear line
				int wordCount = Integer.parseInt(fileIn.nextLine());
				for(int j = 0; j < wordCount; j++) {
					wordCompleted = false; //reset variable
					String word = fileIn.nextLine();
					System.out.print(word);
					for(int row = 0; row < mazeSize; row++) {
						for(int column = 0; column < mazeSize; column++) {
							if(maze[row][column] == word.charAt(0)) {
								if (mode == 1) {
									wordHunt1(maze, word.substring(1), row, column); //search for next char in the letters around the first char
								}
								else {
									wordHunt2(maze, word.substring(1), row, column, 0, 0, true);
								}
								if(wordCompleted) { //dont search other letters if word is found once
									System.out.println(" is found");
									break;
								}
							}
							if(wordCompleted) { //dont search other letters if word is found once
								break;
							}
						}
					}
					if(!wordCompleted) {
						System.out.println(" is NOT found");
					}
				}
//				for(int i = 0; i < mazeSize; i++) {
//					for (int j = 0; j < mazeSize; j++) {
//						System.out.print(maze[i][j]);
//					}
//					System.out.println();
//				}
				System.out.println(); //clear new line as per example
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
	
	//recursive method that searches the grid for a word in any direction, such that
	//no letter is used twice.
	//char[][] maze is the grid of chars (the maze), String word is the word to be searched for,
	//int row is the current row to be searched, int column is the current column to be searched.
	//returns nothing, the method instead changes a global boolean wordCompleted if the word has
	//been found.
	public static void wordHunt1(char[][] maze, String word, int row, int column) {
		if(word.length() == 0) { //word in total was 1 char
			wordCompleted = true;
		}
		if(wordCompleted) { //don't search other letters around word.charAt(1) if word is already found
			return;
		}
//		System.out.println("Entered method");
		maze[row][column] = Character.toLowerCase(maze[row][column]); //mark letter as used
		for(int checkRow = row - 1; checkRow <= row + 1; checkRow++) { //the 3 rows surrounding (row, col)
			for(int checkColumn = column - 1; checkColumn <= column + 1; checkColumn++) {
				try {
//					System.out.println(maze[checkRow][checkColumn]);
					if(Character.isUpperCase(maze[checkRow][checkColumn]) && maze[checkRow][checkColumn] == word.charAt(0)) {
						if(word.length() == 1) {
							wordCompleted = true;
						}
						else { 
							wordHunt1(maze, word.substring(1), checkRow, checkColumn);
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					//expected, just leave it
				}
			}
		}
		maze[row][column] = Character.toUpperCase(maze[row][column]); //mark letter as unused
	}
	
	//recursive method that searches the grid such that the word
	//char[][] maze is the grid of chars (the maze), String word is the word to be searched for,
	//int row is the current row to be searched, int column is the current column to be searched,
	//int xDirection is the defined direction to search (i.e. -1 = left, 0 = no x change, 1 = right
	//int yDirection is the defined direction to search (i.e. -1 = up, 0 = no y change, 1 = down
	//boolean firstLetter is false if the direction has not yet been set, and is true otherwise
	//returns nothing, the method instead changes a global boolean wordCompleted if the word has
	//been found.
	public static void wordHunt2(char[][] maze, String word, int row, int column, int xDirection, int yDirection, boolean firstLetter) {
		if(word.length() == 0) { //word in total was 1 char
			wordCompleted = true;
		}
		if(wordCompleted) {
			return;
		}
//		System.out.println("Entered method");
		maze[row][column] = Character.toLowerCase(maze[row][column]); //mark letter as used
		if(firstLetter) {
			for(int checkRow = row - 1; checkRow <= row + 1; checkRow++) { //the 3 rows surrounding (row, col)
				for(int checkColumn = column - 1; checkColumn <= column + 1; checkColumn++) {
					try {
//					System.out.println(maze[checkRow][checkColumn]);
						if(Character.isUpperCase(maze[checkRow][checkColumn]) && maze[checkRow][checkColumn] == word.charAt(0)) { //second char found
							firstLetter = false; //from now on searching should be in one direction only
							if(word.length() == 1) {
								wordCompleted = true;
								return;
							}
							else { 
								//find direction
								if(checkRow < row) yDirection = -1;
								else if(checkRow > row) yDirection = 1;
								else yDirection = 0; //checkRow == row
								
								if(checkColumn < column) xDirection = -1;
								else if (checkColumn > column) xDirection = 1;
								else xDirection = 0;
								
								wordHunt2(maze, word.substring(1), checkRow, checkColumn, xDirection, yDirection, firstLetter);
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						//expected, just leave it
					}
				}
			}
		}
		else { //not the first letter, search in direction
			try {
				char searchChar = maze[row + yDirection][column + xDirection];
				if(searchChar == word.charAt(0)) {
//					System.out.println(searchChar + "found");
					if(word.length() == 1) {
						wordCompleted = true;
					}
					else {
						wordHunt2(maze, word.substring(1), row + yDirection, column + xDirection, xDirection, yDirection, firstLetter);
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				//expected, do nothing
			}
		}
		maze[row][column] = Character.toUpperCase(maze[row][column]); //mark letter as unused
	}
}