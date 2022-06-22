package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.LoginException;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

public class Driver {

	static Scanner scan;
	static AuthService as;
	static UserService us;
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		as = new AuthService();
		us = new UserService();
		
		String username = null;
		String password = null;
		
		System.out.println("Please enter username:");
		username = scan.nextLine();
		System.out.println("Please enter password:");
		password = scan.nextLine();
		
		try {
			System.out.println(as.login(username, password));
		} catch (LoginException e) {
			System.out.println("Invalid credentials.");
		}
		scan.close();
		
		List<User> users = us.getUsers();
		for(User u : users) {
			System.out.println(u);
		}
	}
}
