package com.techlook.mks.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlook.mks.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public User insert(User user) {

		sessionFactory.getCurrentSession().save(user);
		return user;
	}

	public User getUserById(int id) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
		return user;
	}

	public List<User> getAllUsers() {
		@SuppressWarnings("unchecked")
		List<User> user = sessionFactory.getCurrentSession().createQuery("from User").list();
		return user;
	}

	public User login(User user) {
		Query query = sessionFactory.getCurrentSession().createQuery("from User where userName=? and password=?");
		query.setString(0, user.getUserName());
		query.setString(1, user.getPassword());

		User validUser = (User) query.uniqueResult();
		return validUser;
	}

}
