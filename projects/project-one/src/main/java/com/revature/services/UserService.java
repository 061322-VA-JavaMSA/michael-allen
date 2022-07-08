package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserHibernate;
import com.revature.models.User;

public class UserService {

	private UserDAO ud = new UserHibernate();
	
	public User getUserByUsername(String username) throws Exception {
		User u = ud.retrieveUserByUsername(username);
		
		if (u == null) {
			throw new Exception();
		}
		
		return u;
	}
	
	public User getUserById(int id) throws Exception {
		User u = ud.retrieveUserById(id);
		
		if (u == null) {
			throw new Exception();
		}
		return u;
	}
}
