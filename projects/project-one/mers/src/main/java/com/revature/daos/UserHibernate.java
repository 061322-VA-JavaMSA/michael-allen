package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class UserHibernate implements UserDAO {

	@Override
	public User retrieveUserByUsername(String username) {
		User u = null;
		
		try (Session s = HibernateUtil.getSessionFactory().openSession();) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);

			//Define entity to be search
			Root<User> root = cq.from(User.class);
			
			//Define conditions
			Predicate predicateForUsername = cb.equal(root.get("username"), username);
			
			cq.select(root).where(predicateForUsername);
			
			// retrieves the result
			u = (User) s.createQuery(cq).getSingleResult();
		}
		
		return u;
	}
	
	@Override
	public User retrieveUserById(int id) {
		User u = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession();) {
			u = s.get(User.class, id);
		}

		return u;
	}
	
	@Override
	public int updateUser(int id, String fname, String lname, String username, String email) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			Query q = s.createQuery("update User set firstName=:fname, lastName=:lname, username=:username, email=:email where id=:id");
			q.setParameter("fname", fname);
			q.setParameter("lname", lname);
			q.setParameter("username", username);
			q.setParameter("email", email);
			q.setParameter("id", id);
			
			return q.executeUpdate();		
		}
	}

	@Override
	public List<User> retrieveEmployees() {
		List<User> users = new ArrayList<>();
		
		try (Session s = HibernateUtil.getSessionFactory().openSession();) {
			Query q = s.createQuery("from User where role=:role");
			q.setParameter("role", "Employee");
			
			users = q.getResultList();
		}
		return users;
	}

}
