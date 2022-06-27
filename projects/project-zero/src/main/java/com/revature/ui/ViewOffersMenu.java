package com.revature.ui;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Offer;
import com.revature.services.OfferService;

public class ViewOffersMenu {
	
	private static Scanner scan = new Scanner(System.in);
	private static OfferService os = new OfferService();
	private static boolean validOption = true;
	private static boolean validAnswerToNA = true;
	
	public static void custListOffers(String username) {
		
		List<Offer> offers = os.getUserOffers(username);
		
		if(offers.isEmpty()) {
			System.out.println("No offers to view.");
		}
		else {
			System.out.println("**************************************************************************");
			
			System.out.println("ID   |   Item Name  |  Status");
			System.out.println();
					
			for(int i = 0; i < offers.size(); i++) {
				System.out.println(offers.get(i).getId() + "      " + offers.get(i).getItem() + "       " + offers.get(i).getStatus());
			}
		}
		
		System.out.println("**************************************************************************");
		
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
	
	public static void empPendingOffers(String username) {

		List<Offer> offers = os.getPendingOffers();
		
		if(offers.isEmpty()) {
			noOffers(username);
		}
		else {
			System.out.println("**************************************************************************");
			
			System.out.println("ID   |   Customer   |   Item Name  |  Status");
			System.out.println();
					
			for(int i = 0; i < offers.size(); i++) {
				System.out.println(offers.get(i).getId() + "      " + offers.get(i).getCustomer() +
						"      " + offers.get(i).getItem() + "       " + offers.get(i).getStatus());
			}
		}
		
		System.out.println("**************************************************************************");
		
		do {
			System.out.println("Choose an option:");
			System.out.println("1: Accept an offer");
			System.out.println("2: Reject an offer");
			System.out.println("3: Back to home");
			System.out.println("4: Log out");
			String option = scan.nextLine();
			System.out.println();
			
			switch(option) {
				case "1":
					AcceptOfferMenu.acceptOffer(username);
					validOption = true;
					break;
				case "2":
					RejectOfferMenu.rejectOffer(username);
					validOption = true;
					break;
				case "3":
					HomeMenu.customerHome(username);
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
	
	public static void noOffers(String username) {
		System.out.println("No open offers.");
		
		do {
			System.out.println("Type \"back\" to return home or \"logout\" to log out.");
			String option = scan.nextLine();
			System.out.println();
			
			switch(option) {
				case "back":
					HomeMenu.employeeHome(username);
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
