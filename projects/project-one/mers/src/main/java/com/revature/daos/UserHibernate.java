package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

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
	public boolean updateUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> retrieveEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

}
