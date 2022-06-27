package com.revature.ui;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.services.OfferService;

public class CancelOfferMenu {
	
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LogManager.getLogger(CancelOfferMenu.class);
	private static OfferService os = new OfferService();
	private static boolean validAnswerToNA = true;
	private static boolean validIdToRemove = true;
	private static boolean validAnswerToRemoveQuestion = true;
	private static Integer itemId = null;
	private static int indexOfId;
	private static List<Integer> ids = os.getIds();
	
	public static void cancelOffer(String username) {
		
		if(ids.isEmpty()) {
			noOffers(username);
		}
		
		System.out.println("At any point, type \"back\" to return to the main menu");
		System.out.println("or type \"logout\" to log out.");
		
		do {
			System.out.println("Enter the ID of the offer you'd like to cancel:");
			String itemIdinput = scan.nextLine();
			System.out.println();
					
			if(itemIdinput.equals("back")) { HomeMenu.customerHome(username); }
			else if(itemIdinput.equals("logout")) { LoginRegMenu.splashMenu(); }
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
						
						if(os.cancelOffer(itemId)) {
							System.out.println("Offer successfully canceled.");
							ids.remove(indexOfId);
						}
					} //End if(ids.contains(itemId))
					else /*3*/ {
						System.out.println("Offer doesn't exist.");
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
			noOffers(username);
		}
		else {
			do {
				System.out.println("Would you like to cancel another offer?");
				System.out.println("1: Yes");
				System.out.println("2: No");
				System.out.println("3: Logout");
				String removeAgain = scan.nextLine();
				System.out.println();
				
				switch(removeAgain) {
					case "1":
						validAnswerToRemoveQuestion = true;
						cancelOffer(username);
						break;
					case "2":
						validAnswerToRemoveQuestion = true;
						HomeMenu.customerHome(username);
						break;
					case "3":
						validAnswerToRemoveQuestion = true;
						LoginRegMenu.splashMenu();
						break;
					default:
						System.out.println("Not a valid option.\n");
						validAnswerToRemoveQuestion = false;
				}
			} while(validAnswerToRemoveQuestion == false);
		} //End else
	} //Ends removeItem() method
	
	public static void noOffers(String username) {
		System.out.println("No offers to view.");
		
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
