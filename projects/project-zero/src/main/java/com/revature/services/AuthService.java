package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.exceptions.LoginException;
import com.revature.models.User;
import com.revature.ui.MainMenu;

public class AuthService {

	private UserDAO ud = new UserPostgres();
	private static Logger log = LogManager.getLogger(AuthService.class);
	
	public User login(String username, String password) throws LoginException {
		if(username == null || password == null) {
			throw new LoginException();
		}
		
		User u = ud.retrieveUserByUsername(username);
		// if no user of that name has been retrieved/if pass don't match, throw an exception
		if(u == null || !u.getPassword().equals(password)) {
			throw new LoginException();
		}
		
		return u;
	}

}
