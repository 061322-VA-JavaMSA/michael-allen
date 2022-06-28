package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.revature.models.User;

class AuthServiceTest {

	AuthService authorization = new AuthService();
	
	@Test
	void loginThrowsException() {

		assertThrows(Exception.class, () -> {
			User user = new User();
			user.setUsername(null);
			user.setPassword(null);
			authorization.login(user.getUsername(), user.getPassword());
		});
		
		assertThrows(Exception.class, () -> {
			User user = new User();
			user.setUsername("Michael");
			user.setPassword("green");
			authorization.login(user.getUsername(), user.getPassword());
		});
	}
	
	@Test
	void loginSuccess() throws Exception {
		User user = new User();
		user.setUsername("mikeyboi");
		user.setPassword("mikeyboi");
		authorization.login(user.getUsername(), user.getPassword());
	}

}
