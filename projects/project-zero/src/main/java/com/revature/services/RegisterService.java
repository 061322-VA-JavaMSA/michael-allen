package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.models.User;

public class RegisterService {
	
	private UserDAO ud = new UserPostgres();
	private static Logger log = LogManager.getLogger(RegisterService.class);
	
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