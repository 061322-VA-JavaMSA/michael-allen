package com.revature.ui;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Offer;
import com.revature.services.ItemService;
import com.revature.services.OfferService;

public class MakeOfferMenu {

	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LogManager.getLogger(RemoveItemMenu.class);
	private static boolean validIdForOffer = true;
	private static String itemName = null;
	private static Integer itemId = null;
	private static ItemService is = new ItemService();
	private static OfferService os = new OfferService();
	private static List<Integer> ids = is.getIds();
	
	public static void makeOffer(String username) {
		System.out.println("At any point, type \"back\" to return to the main menu");
		System.out.println("or type \"logout\" to return to log out.");
		
		do {
			System.out.println("Enter the ID of the item you'd like to make an offer for:");
			String itemIdInput = scan.nextLine();
			System.out.println();
					
			if(itemIdInput.equals("back")) { HomeMenu.employeeHome(); }
			else if(itemIdInput.equals("logout")) { MainMenu.mainMenu(); }
			else /*1*/ {
				
				Number inputToNum = null;
				
				try {
					inputToNum = NumberFormat.getInstance().parse(itemIdInput);
				} catch (ParseException e) {
					log.error(e.fillInStackTrace());
				}
				
				//Checks if input for ID was valid
				if(inputToNum != null) {
					itemId = inputToNum.intValue();
					
					if(ids.contains(itemId)) {
						itemName = is.getItemName(itemId);
					} //End if(ids.contains(itemId))
					else /*3*/ {
						System.out.println("Item doesn't exist.");
						validIdForOffer = false;
					}
				} //End if(inputToNum != null)
				else /*2*/ {
					System.out.println("Invalid option.");
					validIdForOffer = false;
				}
			} //End else /*1*/
		} while (validIdForOffer == false);
	
		
		Offer offer = new Offer();
		offer.setCustomer(username);
		offer.setItem(itemName);
		offer.setStatus("Pending");
		
		try {
			os.makeOffer(offer);
			System.out.println("Your offer has been made!");
		} catch (Exception e) {
			log.fatal("Could not create offer.");
			e.printStackTrace();
		}
	}
}
