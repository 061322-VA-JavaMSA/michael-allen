package com.revature.ui;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Item;
import com.revature.services.ItemService;

public class Home {
	
	private static Scanner scan;
	private String username;
	private static ItemService is;
	private static Logger log = LogManager.getLogger(Home.class);
	private static double itemPrice;
	
	public Home(String username) {
		this.username = username;
	}
	
	public void employeeHome() {
		scan = new Scanner(System.in);
		is = new ItemService();
		boolean validOption = true;
		
		System.out.println("*******************************");
		
		List<Item> items = is.getItems();
		
		for(int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).getId() + "      " + items.get(i).getName() + "      " + items.get(i).getPrice());
		}
		
		System.out.println("*******************************");
		
		do {
			System.out.println("\nWelcome, " + username + "!");
			System.out.println("Choose an option:");
			System.out.println("1: Edit item list.");
			System.out.println("2: View open offers.");
			System.out.println("3: Log out.");
			String option = scan.nextLine();
			
			switch(option) {
				case "1":
					editList();
					validOption = true;
					break;
				case "2":
					//View open offers
					validOption = true;
					break;
				case "3":
					System.out.println();
					MainMenu.mainMenu();
					validOption = true;
					break;
				default:
					System.out.println("Not a valid option.\n");
					validOption = false;
			}
		} while(validOption == false);
	}
	
	public void editList() {
		boolean validEditOption = true;
		is = new ItemService();
		boolean validInput = true;
		
		do {
			System.out.println("Choose an option:");
			System.out.println("1: Add item to shop.");
			System.out.println("2: Remove item from shop.");
			System.out.println("3: Go back to home page.");
			System.out.println("4: Log out.");
			String option = scan.nextLine();
			
			switch(option) {
				case "1":
					System.out.println("\nAt any point, type \"back\" to return to the main menu");
					System.out.println("or type \"logout\" to return to log out.");
					
					System.out.println("Enter the name of the item:");
					String itemName = scan.nextLine();
					if(itemName.equals("back")) { employeeHome(); }
					else if(itemName.equals("logout")) { MainMenu.mainMenu(); }
					
				
				do {
						System.out.println("Enter the price of the item, with or without the dollar sign:");
						String inputPrice = scan.nextLine();
						System.out.println();
	
						if(inputPrice.equals("back")) { employeeHome(); }
						else if(inputPrice.equals("logout")) { MainMenu.mainMenu(); }
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
								validInput = true;
							}
							else {
								System.out.println("Invalid value.");
								validInput = false;
							}
						}
					} while(validInput == false);
						
					
					Item newItem = new Item();
					newItem.setName(itemName);
					newItem.setPrice(itemPrice);
					
					try {
						is.createItem(newItem);
						System.out.println("Item added successfully!");
						employeeHome();
					} catch (Exception e) {
						log.error("Could not create new item.");
						e.printStackTrace();
					}
					
					validEditOption = true;
					break;
				case "2":
					//Call delete item function
					validEditOption = true;
					break;
				case "3":
					employeeHome();
					validEditOption = true;
					break;
				case "4":
					System.out.println();
					MainMenu.mainMenu();
					validEditOption = true;
					break;
				default:
					System.out.println("Not a valid option.\n");
					validEditOption = false;
			}
		} while(validEditOption == false);
	}
	
	public static void CustomerHome(String username) {
		is = new ItemService();
		
		System.out.println("*******************************");
		
		List<Item> items = is.getItems();
		
		for(int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).getName() + "      " + items.get(i).getPrice());
		}
		
		System.out.println("*******************************");
	}
}
