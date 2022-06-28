package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.revature.models.User;

class RegisterServiceTest {

	RegisterService rs = new RegisterService();
	
	@Test
	void registerThrowsException() {
		
		assertThrows(Exception.class, () -> {
			User user = new User("anotheruser", "mikeyboi", "Employee");
			rs.createUser(user);
		});
		
	}
	
	
	void addUserToDB() throws Exception {
		User expected = new User("anotheruser", "anotheruser", "Customer");
		
		assertEquals(expected, rs.createUser(expected));
	}

}
