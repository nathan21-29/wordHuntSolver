package recursion2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordMaze1Bonus {
	//Nathan Chan Oct 6, 2025
	//word maze bonus
	//This program is a modified version of mode 1 from WordMaze1and2, but with a twist.
	//instead of reading in words from the same file as the maze, this bonus version reads
	//in a list of words from wordList.txt. Points are then assigned to lengths of words found,
	//and a score is given to each maze as well as how many valid words from the list are found.

	static boolean wordCompleted = false;
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		try {
			Scanner fileIn = new Scanner(new File("wordMazeBonus.txt"));
			int mazeCount = Integer.parseInt(fileIn.nextLine());
			for(int mazeNum = 0; mazeNum < mazeCount; mazeNum++) {
				System.out.println("Grid #" + (mazeNum + 1) + ":");
				int mazeSize = Integer.parseInt(fileIn.nextLine());
				char[][] maze = new char[mazeSize][mazeSize]; //put the maze in an array
				for(int row = 0; row < mazeSize; row++) { //for every row
					String word = fileIn.nextLine();
					System.out.println(word);
					for(int column = 0; column < mazeSize; column++) {
						maze[row][column] = word.charAt(column); 
					}
				}
				System.out.println(); //clear line
				int mazeScore = 0;
				int validWordCount = 0;
					try {
						BufferedReader wordsList = new BufferedReader(new FileReader("wordList.txt"));
						String word;
						while((word = wordsList.readLine()) != null) {
						word = word.toUpperCase();
						wordCompleted = false; //reset variable
						for(int row = 0; row < mazeSize && !wordCompleted; row++) {
							for(int column = 0; column < mazeSize && !wordCompleted; column++) {
								if(maze[row][column] == word.charAt(0)) {
									wordHunt1(maze, word.substring(1), row, column); //search for next char in the letters around the first char
								}
							}
						}
						if(wordCompleted) { //increment if the word is found
//							System.out.println("found " + word); //uncomment if you would like to see every found word
							mazeScore += getScore(word.length());
							validWordCount++;
						}
					}
					wordsList.close();
				} catch (FileNotFoundException e) {
					System.out.println("Word list not found.");
				} catch (IOException e) {
					System.out.println("Reading error.");
				}
						
//				for(int i = 0; i < mazeSize; i++) {
//					for (int j = 0; j < mazeSize; j++) {
//						System.out.print(maze[i][j]);
//					}
//					System.out.println();
//				}
				System.out.println("Results for maze # " + (mazeNum + 1));
				System.out.println("Score: " + mazeScore);
				System.out.println(validWordCount + " valid words\n");
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("Maze file not found.");
		}
		System.out.println(System.currentTimeMillis() - startTime + "ms");
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
	
	//this method gets the score for a word when it is found in the grid
	//int wordLength is the length in chars of the word that has been found
	//returns an int representing the number of points to be assigned.
	public static int getScore(int wordLength) {
		if(wordLength == 3 || wordLength == 4) {
			return 1;
		}
		else if (wordLength == 5) {
			return 2;
		}
		else if (wordLength == 6) {
			return 3;
		}
		else if (wordLength == 7) {
			return 5;
		}
		else if (wordLength >= 8) {
			return 11;
		}
		else {
			return 0;
		}
	}
}