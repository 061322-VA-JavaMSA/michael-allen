package com.revature.ui;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.revature.models.Item;
import com.revature.services.ItemService;

public class HomeMenu {
	
	private static Scanner scan = new Scanner(System.in);
	private static ItemService is = new ItemService();
	private static boolean validOption = true;
	private static boolean validEditOption = true;
	
	public HomeMenu() {
		
	}
	
	public static void employeeHome(String username) {
		
		System.out.println("Welcome, " + username + "!\n");
		
		System.out.println("**************************************************************************");
		
		List<Item> items = is.getItems();
		
		if(items.isEmpty()) {
			System.out.println("No items available.");
		}
		else {
			System.out.println("ID   |   Item Name  |  Price");
			System.out.println();
					
			for(int i = 0; i < items.size(); i++) {
				Double price = items.get(i).getPrice();
				String priceDollarAmnt = NumberFormat.getCurrencyInstance(Locale.US).format(price);
				
				System.out.println(items.get(i).getId() + "      " + items.get(i).getName() + "       " + priceDollarAmnt);
			}
		}
		
		System.out.println("**************************************************************************");
		
		do {
			System.out.println("Choose an option:");
			System.out.println("1: Edit item list");
			System.out.println("2: View open offers");
			System.out.println("3: Log out");
			String option = scan.nextLine();
			System.out.println();
			
			switch(option) {
				case "1":
					editList(username);
					validOption = true;
					break;
				case "2":
					ViewOffersMenu.empPendingOffers(username);
					validOption = true;
					break;
				case "3":
					LoginRegMenu.splashMenu();
					validOption = true;
					break;
				default:
					System.out.println("Not a valid option.\n");
					validOption = false;
			}
		} while(validOption == false);
	}
	
	public static void editList(String username) {
		
		do {
			System.out.println("Choose an option:");
			System.out.println("1: Add item to shop.");
			System.out.println("2: Remove item from shop.");
			System.out.println("3: Go back to home page.");
			System.out.println("4: Log out.");
			String option = scan.nextLine();
			System.out.println();
			
			switch(option) {
				case "1":
					AddItemMenu.addItem(username);
					validEditOption = true;
					break;
				case "2":
					RemoveItemMenu.removeItem(username);
					validEditOption = true;
					break;
				case "3":
					employeeHome(username);
					validEditOption = true;
					break;
				case "4":
					LoginRegMenu.splashMenu();
					validEditOption = true;
					break;
				default:
					System.out.println("Not a valid option.\n");
					validEditOption = false;
			}
		} while(validEditOption == false);
	}
	
	public void viewOffers() {
		// Implementation to view pending offers
	}
	
	public static void customerHome(String username) {
		
		System.out.println("Welcome, " + username + "!\n");
		
		System.out.println("**************************************************************************");
		
		List<Item> items = is.getAvailableItems();
		
		if(items.isEmpty()) {
			System.out.println("No items available.");
		}
		else {
			System.out.println("ID   |   Item Name  |  Price");
			System.out.println();
					
			for(int i = 0; i < items.size(); i++) {
				Double price = items.get(i).getPrice();
				String priceDollarAmnt = NumberFormat.getCurrencyInstance(Locale.US).format(price);
				
				System.out.println(items.get(i).getId() + "      " + items.get(i).getName() + "       " + priceDollarAmnt);
			}
		}
		
		System.out.println("**************************************************************************");
		
		do {
			System.out.println("Choose an option:");
			System.out.println("1: Make offer");
			System.out.println("2: View status of offers");
			System.out.println("3: View your owned items");
			System.out.println("4: Log out");
			String option = scan.nextLine();
			System.out.println();
			
			switch(option) {
				case "1":
					MakeOfferMenu.makeOffer(username);
					validOption = true;
					break;
				case "2":
					ViewOffersMenu.custListOffers(username);
					validOption = true;
					break;
				case "3":
					OwnedItemsMenu.viewItems(username);
					validOption = true;
					break;
				case "4":
					LoginRegMenu.splashMenu();
					validOption = true;
					break;
				default:
					System.out.println("Not a valid option.\n");
					validOption = false;
			}
		} while(validOption == false);
	}
}
