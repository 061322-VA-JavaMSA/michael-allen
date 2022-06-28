package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.models.User;

public class RegisterService {
	
	private UserDAO ud = new UserPostgres();
	
	public User createUser(User u) throws Exception {
		User existingUser = ud.retrieveUserByUsername(u.getUsername());
		
		if(existingUser != null) {
			throw new Exception("\nA user with that username already exists. Please try again.");
		}
		else {
			ud.createUser(u);
			return u;
		}
		
	}
}