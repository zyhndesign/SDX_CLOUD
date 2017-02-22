package com.cidic.sdx.dggl.dao.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.AppUserDao;
import com.cidic.sdx.dggl.model.User;

@Repository
@Transactional
@Component
@Qualifier(value = "appUserDaoImpl")
public class AppUserDaoImpl implements AppUserDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void createUser(User user) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.save(user);
	}

	@Override
	public void updateUser(User user) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(user);
	}

	@Override
	public void deleteUser(int userId) {
		Session session = this.getSessionFactory().getCurrentSession();
		User user = new User();
		user.setId(userId);
		session.delete(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from User where username = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, username); 
        @SuppressWarnings("unchecked")
		List<User> list = query.list();
        if (list.size() > 0){
        	Optional<User> user = Optional.ofNullable(list.get(0));
     		return user;
        }
        else{
        	return Optional.empty();
        }
	}

	@Override
	public Optional<User> authorityCheck(String username, String password) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from User where username = ? and password = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, username); 
        query.setParameter(1, password);
        @SuppressWarnings("unchecked")
        List<User> list = query.list();   
        if (list.size() > 0){
        	Optional<User> user = Optional.ofNullable(list.get(0));
     		return user;
        }
        else{
        	return Optional.empty();
        }
	}

}
