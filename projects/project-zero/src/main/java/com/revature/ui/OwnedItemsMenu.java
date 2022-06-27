package com.revature.ui;

import java.util.List;
import java.util.Scanner;
import com.revature.services.ItemService;

public class OwnedItemsMenu {
	
	private static Scanner scan = new Scanner(System.in);
	private static ItemService is = new ItemService();
	private static boolean validOption = true;
	private static boolean validAnswerToNA = true;
	
	public static void viewItems(String username) {
		List<String> items = is.getOwnedItems(username); 
		
		if(items.isEmpty()) {
			noOwnedItems(username);
		}
		else {
			System.out.println("**************************************************************************");
			
			System.out.println("Items you own:");
			System.out.println();
					
			for(int i = 0; i < items.size(); i++) {
				System.out.println(items.get(i));
			}
			
			System.out.println("**************************************************************************");
		}
		
		do {
			System.out.println("Choose an option:");
			System.out.println("1: Cancel offer");
			System.out.println("2: Back to home");
			System.out.println("3: Log out");
			String option = scan.nextLine();
			System.out.println();
			
			switch(option) {
				case "1":
					CancelOfferMenu.cancelOffer(username);
					validOption = true;
					break;
				case "2":
					HomeMenu.customerHome(username);
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
	
	public static void noOwnedItems(String username) {
		System.out.println("You don't own any items.");
		
		do {
			System.out.println("Type \"back\" to return home or \"logout\" to log out.");
			String option = scan.nextLine();
			System.out.println();
			
			switch(option) {
				case "back":
					HomeMenu.customerHome(username);
					validAnswerToNA = true;
					break;
				case "logout":
					LoginRegMenu.splashMenu();
					validAnswerToNA = true;
					break;
				default:
					System.out.println("Not a valid option.");
					validAnswerToNA = false;
			}
		} while(validAnswerToNA == false);
	}
}
