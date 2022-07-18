package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.revature.models.User;

class AuthServiceTest {

	AuthService auth = new AuthService();
	

	void loginThrowsException() {
		assertThrows(Exception.class, () -> {
			User user = new User();
			user.setUsername(null);
			user.setPassword(null);
			auth.login(user.getUsername(), user.getPassword());
		});
		
		assertThrows(Exception.class, () -> {
			User user = new User();
			user.setUsername("roman_reigns");
			user.setPassword("tribal");
			auth.login(user.getUsername(), user.getPassword());
		});
	}
	
	@Test
	void loginSuccess() throws Exception {
		User user = new User();
		user.setUsername("roman_reigns");
		user.setPassword("tribalchief");
		auth.login(user.getUsername(), user.getPassword());
	}

}
