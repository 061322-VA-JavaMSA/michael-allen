package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.exceptions.LoginException;
import com.revature.models.User;

public class AuthService {

	private UserDAO ud = new UserPostgres();
	
	public User login(String username, String password) {
		// if username/password passed are null, throws an exception
		if(username == null || password == null) {
			throw new LoginException();
		}
		User u = ud.retrieveUserByUserName(username);
		// if no user of that name has been retrieved/if passwords don't match, throw an exception
		if(u == null | !u.getPassword().equals(password)) {
			throw new LoginException();
		}
		return u;
	}
}
