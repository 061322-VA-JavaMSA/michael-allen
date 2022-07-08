package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	//Auth
	User retrieveUserByUsername(String username);
	
	//Employee
	User retrieveUserById(int id);
	boolean updateUser(User u);
	
	//Manager
	List<User> retrieveEmployees();
}
