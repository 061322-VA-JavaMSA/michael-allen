package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.revature.exceptions.ReimbStatusNotUpdatedException;
import com.revature.models.Reimbursement;
import com.revature.util.HibernateUtil;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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

	@Override
	public List<Reimbursement> getPendingReimbursements(String user, String status) {
		
		List<Reimbursement> reimbs = new ArrayList<>();
		
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> root = cq.from(Reimbursement.class);
			
			Predicate predicateForAuthor = cb.equal(root.get("author"), user);
			Predicate predicateForStatus = cb.equal(root.get("status"), status);
			Predicate predicateForPendingReimb = cb.and(predicateForAuthor, predicateForStatus);
			
			cq.select(root).where(predicateForPendingReimb);
			
			reimbs = s.createQuery(cq).getResultList();
		}
		
		return reimbs;
	}

	@Override
	public List<Reimbursement> getPendingReimbursements(String status) {
		List<Reimbursement> reimbs = new ArrayList<>();
		
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> root = cq.from(Reimbursement.class);
			
			Predicate predicateForStatus = cb.equal(root.get("status"), status);
			
			cq.select(root).where(predicateForStatus);
			
			reimbs = s.createQuery(cq).getResultList();
		}
		
		return reimbs;
	}

	@Override
	public int updateReimbStatus(int id, String status, String resolver, String resolved) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			Query q = s.createQuery("update Reimbursement set status=:status, resolver=:resolver, resolved=:resolved where id=:id");
			q.setParameter("status", status);
			q.setParameter("resolver", resolver);
			q.setParameter("resolved", resolved);
			q.setParameter("id", id);
			
			return q.executeUpdate();		
		
		}

	}

}
