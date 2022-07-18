package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.revature.exceptions.ReimbursementNotCreatedException;
import com.revature.models.Reimbursement;

class ReimbServiceTest {

	ReimbService rs = new ReimbService();
	
	@Test
	void createReimbursement() throws ReimbursementNotCreatedException {
		Reimbursement expected = new Reimbursement("Tom Cat", "travel", "Went to Disneyland", 200.00, "Pending", "7/17/2022", null, null );
		Reimbursement actual = rs.createReimbursement(expected);
		
		assertEquals(expected, actual);
	}

}
