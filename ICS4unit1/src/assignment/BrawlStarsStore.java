package assignment;

import java.io.*;
import java.util.Scanner;

//Nathan Chan Sep 5 2025
//Brawl stars shop!!
//This program will run a shop in the console with the following flow:
//Money input -> gem purchase -> buy items -> state remaining gems and finalize
//A record of the purchases will be printed into the text file "summary.txt"
//in the project directory.

public class BrawlStarsStore {
	
	public static void main(String[] args) {
		boolean validInput;
		//general variables
		double cashBalance = 0;
		//use arrays to store items and their prices
		String[] gemCountStrings = {"30", "80", "170", "360", "950", "2000"}; //strings used to streamline usage of findInArray
		int[] gemCounts = {30, 80, 170, 360, 950, 2000}; 
		double[] gemPrices = {1.99, 4.99, 9.99, 19.99, 49.99, 99.99};
		String[] items = {"spray", "XP doubler", "skin", "brawler"}; 
		int[][] prices = { //item prices
				{19}, //spray price 
				{25}, //XP doubler price
				{29, 49, 79, 149, 199, 299, 499}, //Skin prices 
				{29, 79, 169, 349, 699}}; //Brawler prices
		String[] brawlerRarities = {"Rare", "Super rare", "Epic", "Mythic", "Legendary"};
		
		//Prompt for payment amount
		Scanner in = new Scanner(System.in);
		do {
			validInput = true;
			System.out.print("How much money are you spending on gems today? $");
			try {
				cashBalance = Double.parseDouble(in.nextLine().trim());
				//catch invalid doubles
				if(cashBalance < 1.99) {
					System.out.println("INVALID. Please provide a positive amount of at least $1.99.");
					validInput = false;
				}
			} catch (NumberFormatException e) {
				System.out.print("INVALID. ");
				validInput = false;
			}
		} while (!validInput);
		
		//Ask how many gems user wants to buy
		String gemSelect;
		int gemIndex;
		do {
			validInput = true;
			System.out.print("Enter the gem pack to purchase (30, 80, 170, 360, 950, 2000): ");
			gemSelect = in.nextLine().trim();
			gemIndex = findInArray(gemCountStrings, gemSelect);
			if(gemIndex == -1) { //requested amount does not exist
				System.out.print("INVALID. ");
				validInput = false;
			}
			else if(gemPrices[gemIndex] > cashBalance) { //not enough cash balance to buy...
				System.out.println("You do not have enough to buy this.");
				validInput = false;
			}
		} while (!validInput);
		int gemBalance = gemCounts[gemIndex];
		System.out.printf("    You paid $%.2f. Your change is $%.2f.\n", cashBalance, cashBalance - gemPrices[gemIndex]);
		
		//start the header of the receipt
		PrintWriter outFile;
		try {
			outFile = new PrintWriter(new FileWriter("summary.txt"));
			outFile.println("Summary of your purchases:\n");
			outFile.printf("%-24s$%.2f\n", "AMOUNT SPENT", gemPrices[gemIndex]);
			outFile.printf("%-24s%d\n\n", "# GEMS PURCHASED", gemCounts[gemIndex]);
			outFile.println("--------------------------------------------------------");
			outFile.printf("%-24s%-18s%-18s\n", "ITEM PURCHASED", "TYPE", "# GEMS");
			outFile.printf("%-24s%-18s%-18s\n", "--------------", "----", "-------");
			outFile.close();
		} catch (IOException e) {
			System.out.println("Writing error");
		}
		
		//buy items
		boolean keepBuying = true;
		do { //outer loop
			//buy item
			
			String type = "/"; //default type for type-less items
			int itemIndex;
			String itemRaw;
			System.out.print("\nEnter the item to purchase (spray, XP doubler, skin, brawler): ");
			do { //get input for item request
				validInput = true;
				itemRaw = in.nextLine().trim();
				itemIndex = findInArray(items, itemRaw);
				if(itemIndex == -1 ) { //item was not found
					System.out.print("    INVALID. Enter the item to purchase (spray, XP doubler, skin, brawler): ");
					validInput = false;
				}
			} while (!validInput);
			
			int priceIndex = 0; //used for items with multiple rarities/prices
			
			if(itemIndex == 0 || itemIndex == 1) { //item is spray or xp doubler, which have only 1 possible price
				gemBalance = attemptPurchase(items[itemIndex], prices[itemIndex][priceIndex], type, gemBalance, itemRaw);
			}
			else if(itemIndex == 2) { //item is skin
				priceIndex = (int) (Math.random() * 7); //produces range from 0-6 for 7 possible skins
				gemBalance = attemptPurchase(items[itemIndex], prices[itemIndex][priceIndex], type, gemBalance, itemRaw);
			}
			else { //item is brawler
				//make sure user can buy cheapest brawler
				if(prices[itemIndex][0] > gemBalance) {
					System.out.println("    It will cost " + prices[itemIndex][0] + " gems to purchase the cheapest brawler."
							+ "\n    You do not have enough gems to buy this.");
				}
				else { //user can afford at least 1 skin option; prompt rarity
					int rarityIndex;
					boolean canAfford;
					do { //buying a brawler will keep prompting until satisfiable rarity is selected
						canAfford = true;
						//cannot use attemptPurchase yet or else an unsatisfactory rarity will prompt "buy again?" prompt
						System.out.print("    Enter the brawler type (rare, super rare, epic, mythic, legendary): ");
						do { //get valid rarity name
							validInput = true;
							String selectedRarity = in.nextLine().trim();
							type = selectedRarity; //update type for receipt printing
							rarityIndex = findInArray(brawlerRarities, selectedRarity);
							if(rarityIndex == -1) { //invalid rarity
								System.out.print("    INVALID. Enter the brawler type (rare, super rare, epic, mythic, legendary): ");
								validInput = false;
							}
						} while (!validInput);
						//check if user can afford a skin of this rarity
						if(prices[itemIndex][rarityIndex] > gemBalance) { //the user cannot afford
							System.out.println("    You do not have enough to buy this.");
							canAfford = false; //re-prompt brawler type
						}
					} while (!canAfford);
					//at this point a buyable rarity has been selected
					gemBalance = attemptPurchase(items[itemIndex], prices[itemIndex][rarityIndex], type, gemBalance, itemRaw);
				}
			}
			
			if(gemBalance == 0) { //the user has *no* more gems, so end program as per instructions
				keepBuying = false;
			}
			else {
				//ask user if they are done
				System.out.print("    Are you buying anymore items? (y/n): ");
				do {
					validInput = true;
					String response = in.nextLine().trim().toLowerCase();
					if(response.equals("n") || response.equals("no")) {
						keepBuying = false;
					}
					else if(!response.equals("y") && !response.equals("yes")) { //invalid response
						System.out.print("    INVALID. Are you buying anymore items? (y/n): ");
						validInput = false;
					}
					//else == valid response, keep keepBuying = true
				} while (!validInput);
			}
		} while (keepBuying);
		
		//finish receipt
		try {
			outFile = new PrintWriter(new FileWriter("summary.txt", true));
			outFile.println("--------------------------------------------------------\n");
			outFile.printf("%-44s%d\n", "TOTAL # GEMS SPENT", gemCounts[gemIndex] - gemBalance);
			outFile.printf("%-44s%d", "# GEMS LEFT", gemBalance);
			outFile.close();
		} catch (IOException e) {
			System.out.println("Writing error");
		}
		
		//final output
		System.out.println("\nYou have " + gemBalance + " gems left.");
		System.out.println("Thanks for your purchases. Summary of your purchases is recorded in summary.txt.");
		
		in.close();
	}
	
	//finds the index of a string within a string array
	//essentially indexOf but for an array
	//params String[] array is the array to be searched
	//String keyword is the string that will be searched for
	//returns the index of keyword in array OR -1 if keyword does not exist
	//in array.
	public static int findInArray(String[] array, String keyword) {
		for(int i = 0; i < array.length; i++) {
			if(array[i].equalsIgnoreCase(keyword)) {
				return i;
			}
		}
		//not found case
		return -1;
	}
	
	//checks if user has enough funds to purchase selected item,
	//then completes transaction + updates receipt if they do.
	//parameters item is the String name of the item to be purchased
	//price is the price in gems of the item to be purchased
	//returns nothing, all console output is already handled by method
	public static int attemptPurchase(String item, int price, String type, int gemBalance, String itemRaw) {
		if(price > gemBalance) { 
			if(!item.equalsIgnoreCase("Brawler")) {
				System.out.println("    It will cost " + price + " gems to purchase " + itemRaw + ".  You do not have enough to buy this.");
			}
			else {
				System.out.println("    You do not have enough to buy this.");
			}
		} //if user cannot afford, will skip to "buying anymore?" prompt
		else {
			gemBalance -= price;
			System.out.printf("    %d gems have been spent on %s. You have %d gems left.\n", price, item, gemBalance);
			
			//update receipt
			try {
				PrintWriter outFile = new PrintWriter(new FileWriter("summary.txt", true));
				outFile.printf("%-24s%-20s%-16d\n", itemRaw, type, price);
				outFile.close();
			} catch (IOException e) {
				System.out.println("Writing error");
			}
		}
		return gemBalance;
	}

}
