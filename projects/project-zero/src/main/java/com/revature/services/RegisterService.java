package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.models.User;

public class RegisterService {
	
	private UserDAO ud = new UserPostgres();
	
	public void createUser(User u) throws Exception {
		User existingUser = ud.retrieveUserByUsername(u.getUsername());
		
		if(existingUser != null) {
			throw new Exception();
		}
		else {
			ud.createUser(u);
		}
		
	}
}