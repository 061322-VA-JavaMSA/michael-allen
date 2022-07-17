package com.revature.services;

import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.UserHibernate;
import com.revature.exceptions.CannotRetrieveEmployeesException;
import com.revature.exceptions.ReimbStatusNotUpdatedException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.UserNotUpdatedException;
import com.revature.models.User;

public class UserService {

	private UserDAO ud = new UserHibernate();
	
	public User getUserByUsername(String username) throws UserNotFoundException {
		User u = ud.retrieveUserByUsername(username);
		
		if (u == null) {
			throw new UserNotFoundException();
		}
		
		return u;
	}
	
	public User getUserById(int id) throws UserNotFoundException {
		User u = ud.retrieveUserById(id);
		
		if (u == null) {
			throw new UserNotFoundException();
		}
		return u;
	}
	
	public List<User> getEmployees() throws CannotRetrieveEmployeesException {
		List<User> users = ud.retrieveEmployees();
		
		if(users == null) {
			throw new CannotRetrieveEmployeesException();
		}
		
		return users;
	}
	
	public void updateUser(int id, String fname, String lname, String username, String email) throws UserNotUpdatedException {
		int userUpdated = ud.updateUser(id, fname, lname, username, email);
		
		if(userUpdated == 0) {
			throw new UserNotUpdatedException();
		}
	}
}
