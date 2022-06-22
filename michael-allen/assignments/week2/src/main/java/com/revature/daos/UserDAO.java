package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {

	User createUser(User u);
	User retrieveUserById(int id);
	List<User> retrieveUsers();
	User retrieveUserByUserName(String username);
	boolean updateUser(User u);
	boolean deleteUserById(int id);
}
