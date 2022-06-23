package com.revature.daos;

import com.revature.models.User;

public interface UserDAO {
	User createUser(User u);
	User retrieveUserByUsername(String username);
}
