package com.revature.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.revature.models.Reimbursement;
import com.revature.util.HibernateUtil;

public class ReimbHibernate implements ReimbDAO {

	@Override
	public Reimbursement insertReimbursement(Reimbursement reimb) {
		reimb.setId(-1);
		
		try(Session s = HibernateUtil.getSessionFactory().openSession())  {
			Transaction tx = s.beginTransaction();
			int id = (int) s.save(reimb);
			reimb.setId(id);
			tx.commit();
		} catch(ConstraintViolationException e) {
			e.printStackTrace();
		}
		
		return reimb;
	}

}
