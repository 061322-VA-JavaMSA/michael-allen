package com.revature.ui;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.services.OfferService;

public class AcceptOfferMenu {
	
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LogManager.getLogger(AcceptOfferMenu.class);
	private static Integer offerId = null;
	private static OfferService os = new OfferService();
	private static List<Integer> ids = os.getIds();
	private static boolean validIdForOffer = true;
	
	public static void acceptOffer(String username) {
		
		System.out.println("At any point, type \"back\" to return to the main menu");
		System.out.println("or type \"logout\" to log out.");
		
		do {
			System.out.println("Enter the ID of the offer you'd like to accept.");
			String itemIdInput = scan.nextLine();
			System.out.println();
					
			if(itemIdInput.equals("back")) { HomeMenu.employeeHome(username); }
			else if(itemIdInput.equals("logout")) { LoginRegMenu.splashMenu(); }
			else /*1*/ {
				
				Number inputToNum = null;
				
				try {
					inputToNum = NumberFormat.getInstance().parse(itemIdInput);
				} catch (ParseException e) {
					log.error(e.fillInStackTrace());
				}
				
				//Checks if input for ID was valid
				if(inputToNum != null) {
					offerId = inputToNum.intValue();
					
					if(ids.contains(offerId)) {
						try {
							os.acceptOffer(offerId);
							System.out.println("Offer accepted!");
						} catch(Exception e) {
							log.fatal("Could not accept offer.");
							e.printStackTrace();
						}
						
					} //End if(ids.contains(OfferId))
					else /*3*/ {
						System.out.println("Offer doesn't exist.");
						validIdForOffer = false;
					}
				} //End if(inputToNum != null)
				else /*2*/ {
					System.out.println("Invalid option.");
					validIdForOffer = false;
				}
			} //End else /*1*/
		} while (validIdForOffer == false);
		
		HomeMenu.employeeHome(username);
	}
}
