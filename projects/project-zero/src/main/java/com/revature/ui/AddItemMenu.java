package com.revature.ui;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Item;
import com.revature.services.ItemService;

public class AddItemMenu {
	
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LogManager.getLogger(AddItemMenu.class);
	private static boolean validPrice = true;
	private static double itemPrice;
	private static ItemService is = new ItemService();
	private static boolean validAnswerToRemoveQuestion = true;

	public static void addItem(String username) {
		System.out.println("At any point, type \"back\" to return to the main menu");
		System.out.println("or type \"logout\" to log out.");
		
		System.out.println("Enter the name of the item:");
		String itemName = scan.nextLine();
		System.out.println();
		if(itemName.equals("back")) { HomeMenu.employeeHome(username); }
		else if(itemName.equals("logout")) { LoginRegMenu.splashMenu(); }
		
	
		do {
			System.out.println("Enter the price of the item, with or without the dollar sign:");
			String inputPrice = scan.nextLine();
			System.out.println();

			if(inputPrice.equals("back")) { HomeMenu.employeeHome(username); }
			else if(inputPrice.equals("logout")) { LoginRegMenu.splashMenu(); }
			else {
			
				if(inputPrice.charAt(0) != '$') {
					inputPrice = '$' + inputPrice;
				}
				
				Number priceToNum = null;
				
				try {
					priceToNum = NumberFormat.getCurrencyInstance(Locale.US).parse(inputPrice);
				} catch (ParseException e) {
					log.error(e.fillInStackTrace());
				}
				
				if(priceToNum != null) {
					itemPrice = priceToNum.doubleValue();
					validPrice = true;
				}
				else {
					System.out.println("Invalid value.");
					validPrice = false;
				}
			}
		} while(validPrice == false);
			
		
		Item newItem = new Item();
		newItem.setName(itemName);
		newItem.setPrice(itemPrice);
		
		try {
			is.createItem(newItem);
			System.out.println("Item added successfully!");
		} catch (Exception e) {
			log.fatal("Could not create new item.");
			e.printStackTrace();
		}
		
		do {
			System.out.println("Would you like to add another item?");
			System.out.println("1: Yes");
			System.out.println("2: No");
			System.out.println("3: Logout");
			String removeAgain = scan.nextLine();
			System.out.println();
			
			switch(removeAgain) {
				case "1":
					validAnswerToRemoveQuestion = true;
					addItem(username);
					break;
				case "2":
					validAnswerToRemoveQuestion = true;
					HomeMenu.employeeHome(username);
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
		
	}
}
