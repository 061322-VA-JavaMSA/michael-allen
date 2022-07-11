package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbDAO {

	Reimbursement insertReimbursement(Reimbursement reimb);
	List<Reimbursement> getPendingReimbursements(String user, String status);
	List<Reimbursement> getPendingReimbursements(String status);
}
