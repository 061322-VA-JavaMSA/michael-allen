package com.revature.ui;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.services.ItemService;

public class RemoveItemMenu {
	
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LogManager.getLogger(RemoveItemMenu.class);
	private static ItemService is = new ItemService();
	private static boolean validAnswerToNA = true;
	private static boolean validIdToRemove = true;
	private static boolean validAnswerToRemoveQuestion = true;
	private static Integer itemId = null;
	private static int indexOfId;
	private static List<Integer> ids = is.getIds();

	public static void removeItem() {
		
		if(ids.isEmpty()) {
			noItems();
		}
		
		System.out.println("At any point, type \"back\" to return to the main menu");
		System.out.println("or type \"logout\" to return to log out.");
		
		do {
			System.out.println("Enter the ID of the item you'd like to remove:");
			String itemIdinput = scan.nextLine();
			System.out.println();
					
			if(itemIdinput.equals("back")) { HomeMenu.employeeHome(); }
			else if(itemIdinput.equals("logout")) { MainMenu.mainMenu(); }
			else /*1*/ {
				
				Number inputToNum = null;
				
				try {
					inputToNum = NumberFormat.getInstance().parse(itemIdinput);
				} catch (ParseException e) {
					log.error(e.fillInStackTrace());
				}
				
				//Checks if input for ID was valid
				if(inputToNum != null) {
					itemId = inputToNum.intValue();
					
					if(ids.contains(itemId)) {
						validIdToRemove = true;
						indexOfId = ids.indexOf(itemId);
						
						if(is.deleteItemById(itemId)) {
							System.out.println("Item successfully removed from shop!");
							ids.remove(indexOfId);
						}
					} //End if(ids.contains(itemId))
					else /*3*/ {
						System.out.println("Item doesn't exist.");
						validIdToRemove = false;
					}
				} //End if(inputToNum != null)
				else /*2*/ {
					System.out.println("Invalid option.");
					validIdToRemove = false;
				}
			} //End else /*1*/
		} while (validIdToRemove == false);
				
		if(ids.isEmpty()) {
			noItems();
		}
		else {
			do {
				System.out.println("Would you like to remove another item?");
				System.out.println("1: Yes");
				System.out.println("2: No");
				System.out.println("3: Logout");
				String removeAgain = scan.nextLine();
				System.out.println();
				
				switch(removeAgain) {
					case "1":
						validAnswerToRemoveQuestion = true;
						removeItem();
						break;
					case "2":
						validAnswerToRemoveQuestion = true;
						HomeMenu.employeeHome();
						break;
					case "3":
						validAnswerToRemoveQuestion = true;
						MainMenu.mainMenu();
						break;
					default:
						System.out.println("Not a valid option.\n");
						validAnswerToRemoveQuestion = false;
				}
			} while(validAnswerToRemoveQuestion == false);
		} //End else
	} //Ends removeItem() method
	
	public static void noItems() {
		System.out.println("No items available.");
		
		do {
			System.out.println("Type \"back\" to return home");
			System.out.println("or type \"logout\" to return to log out.");
			String option = scan.nextLine();
			System.out.println();
			
			switch(option) {
				case "back":
					HomeMenu.employeeHome();
					validAnswerToNA = true;
					break;
				case "logout":
					MainMenu.mainMenu();
					validAnswerToNA = true;
					break;
				default:
					System.out.println("Not a valid option.");
					validAnswerToNA = false;
			}
		} while(validAnswerToNA == false);
	}
} //Ends RemoveItemMenu class
