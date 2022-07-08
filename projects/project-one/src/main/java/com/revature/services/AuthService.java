package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserHibernate;
import com.revature.exceptions.LoginException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

public class AuthService {

	private UserDAO ud = new UserHibernate();
	
	public User login(String username, String password) throws UserNotFoundException, LoginException {
		
		User principal = ud.retrieveUserByUsername(username);
		
		if(principal == null) {
			throw new UserNotFoundException();
		}
		
		// if no user of that name has been retrieved/if pass don't match, throw an exception
		if(!principal.getPassword().equals(password)) {
			throw new LoginException();
		}
		
		return principal;
	}

}
