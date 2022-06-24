package com.revature.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.RegisterService;

public class MainMenu {
	
	private static Scanner scan;
	private static AuthService as;
	private static RegisterService rs;
	private static boolean validOption;
	private static Logger log = LogManager.getLogger(MainMenu.class);
	
	public static void mainMenu() {
		
		scan = new Scanner(System.in);
		validOption = true;
	
		do {
			System.out.println("Welcome to Mike's shop! Enter an option: ");
			System.out.println("1: Log in to your account ");
			System.out.println("2: Register for new account ");
			String option = scan.nextLine();
			
			switch(option) {
				case "1":
					login();
					validOption = true;
					break;
				case "2":
					register();
					validOption = true;
					break;
				default:
					System.out.println("Not a valid option.\n");
					mainMenu();
					validOption = false;
			}
		} while(validOption == false);
	}
	
	public static void login() {
		as = new AuthService();
		String username;
		String password;
		
		System.out.println("\nType \"back\" to return to the main menu.");
		
		System.out.println("Please enter your username:");
		username = scan.nextLine();
		if(username.equals("back")) { mainMenu(); }
		
		System.out.println("Please enter your password:");
		password = scan.nextLine();
		System.out.println();
		if(password.equals("back")) { mainMenu(); }
		
		try {
			User u = as.login(username, password);
			if(u.getRole().equals("Employee")) {
				HomeMenu.employeeHome();
			}
			else {
				HomeMenu.CustomerHome(u.getUsername());
			}
		} catch (Exception e) {
			System.out.println("Invalid credentials. Please try again.");
			log.error("Exception was thrown: " + e.fillInStackTrace());
			login();
		}
	}
	
	public static void register() {
		String username;
		String password;
		String roleOption;
		validOption = true;
		String optionToRole = null;
		rs = new RegisterService();
		
		
		System.out.println("Type \"back\" to return to the main menu.");
		
		System.out.println("\nPlease enter a username:");
		username = scan.nextLine();
		if(username.equals("back")) { mainMenu(); }
		
		System.out.println("Please enter a password:");
		password = scan.nextLine();
		if(password.equals("back")) { mainMenu(); }
		
		do {
		System.out.println("How will you be using the app?");
		System.out.println("Enter 1 for Employee.");
		System.out.println("Enter 2 for Customer.");
		roleOption = scan.nextLine();

		switch(roleOption) {
			case "1":
				optionToRole = "Employee";
				validOption = true;
				break;
			case "2":
				optionToRole = "Customer";
				validOption = true;
				break;
			case "back":
				mainMenu();
				validOption = true;
				break;
			default:
				System.out.println("Not a valid option.\n");
				validOption = false;
			}
		} while(validOption == false);
		
		User userToReg = new User();
		userToReg.setUsername(username);
		userToReg.setPassword(password);
		userToReg.setRole(optionToRole);
		
		try {
			rs.createUser(userToReg);
			System.out.println("\nYou have successfully registered! You can now log in.");
			mainMenu();
		} catch (Exception e) {
			System.out.println("\nA user with that username already exists. Please try again.");
			log.error("Registration exception was thrown: " + e.fillInStackTrace());
			register();
		}

	}
}
