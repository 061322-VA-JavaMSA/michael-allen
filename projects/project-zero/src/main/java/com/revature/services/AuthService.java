package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.models.User;

public class AuthService {

	private UserDAO ud = new UserPostgres();
	
	public User login(String username, String password) throws Exception {
		if(username == null || password == null) {
			throw new Exception("Invalid credentials. Please try again.");
		}
		
		User u = ud.retrieveUserByUsername(username);
		// if no user of that name has been retrieved/if pass don't match, throw an exception
		if(u == null || !u.getPassword().equals(password)) {
			throw new Exception("Invalid credentials. Please try again.");
		}
		
		return u;
	}

}
