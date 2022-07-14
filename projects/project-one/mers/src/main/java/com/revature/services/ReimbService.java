package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbDAO;
import com.revature.daos.ReimbHibernate;
import com.revature.exceptions.ReimbStatusNotUpdatedException;
import com.revature.exceptions.ReimbsNotFoundException;
import com.revature.exceptions.ReimbursementNotCreatedException;
import com.revature.models.Reimbursement;

public class ReimbService {

	ReimbDAO rd = new ReimbHibernate();
	
	public Reimbursement createReimbursement(Reimbursement reimb) throws ReimbursementNotCreatedException {
		
		Reimbursement createdReimb = rd.insertReimbursement(reimb);
		if(createdReimb.getId() == -1) {
			throw new ReimbursementNotCreatedException();
		}
		
		return createdReimb;
	}
	
	public List<Reimbursement> getPendingReimbs(String user, String status) throws ReimbsNotFoundException {
		List<Reimbursement> reimbs = rd.getPendingReimbursements(user, status);
		
		if(reimbs == null) {
			throw new ReimbsNotFoundException();
		}
		
		return reimbs;
	}
	
	public List<Reimbursement> getPendingReimbs(String status) throws ReimbsNotFoundException {
		List<Reimbursement> reimbs = rd.getPendingReimbursements(status);
		
		if(reimbs == null) {
			throw new ReimbsNotFoundException();
		}
		
		return reimbs;
	}
	
	public void updateReimbStatus(int id, String status, String resolver, String resolved) throws ReimbStatusNotUpdatedException {
		int statusUpdated = rd.updateReimbStatus(id, status, resolver, resolved);
		
		if(statusUpdated == 0) {
			throw new ReimbStatusNotUpdatedException();
		}
	}
	
	public List<Reimbursement> getEmployeeReimbs(String user) throws ReimbsNotFoundException {
		List<Reimbursement> reimbs = rd.getReimbsByUser(user);
		
		if(reimbs == null) {
			throw new ReimbsNotFoundException();
		}
		
		return reimbs;
		
	}

}
