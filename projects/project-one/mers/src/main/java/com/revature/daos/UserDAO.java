package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	//Auth
	User retrieveUserByUsername(String username);
	
	//Employee
	User retrieveUserById(int id);
	int updateUser(int id, String fname, String lname, String username, String email);
	
	//Manager
	List<User> retrieveEmployees();
}
