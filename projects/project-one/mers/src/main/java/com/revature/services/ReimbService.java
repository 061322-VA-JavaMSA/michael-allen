package com.revature.services;

import com.revature.daos.ReimbDAO;
import com.revature.daos.ReimbHibernate;
import com.revature.exceptions.ReimbursementNotCreatedException;
import com.revature.models.Reimbursement;

public class ReimbService {

	ReimbDAO rd = new ReimbHibernate();
	
	public Reimbursement createReimbursement(Reimbursement reimb) throws ReimbursementNotCreatedException {
		
		reimb.setSubmitted(java.time.LocalDate.now().toString());
		reimb.setStatus("Pending");
		
		Reimbursement createdReimb = rd.insertReimbursement(reimb);
		if(createdReimb.getId() == -1) {
			throw new ReimbursementNotCreatedException();
		}
		
		return createdReimb;
	}
}
