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

import com.cidic.sdx.dggl.dao.AppUserDao;
import com.cidic.sdx.dggl.model.User;

@Repository
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
		String hql = "";
		Query query = null;
		if (user.getHeadicon() == null || user.getHeadicon().equals("")){
			hql = " update User u set u.username = ? , u.shop.id = ? where u.id = ?";
			query = session.createQuery(hql);
			query.setParameter(0, user.getUsername());
			query.setParameter(1, user.getShop().getId());
			query.setParameter(2, user.getId());
		}else{
			hql = " update User u set u.username = ? , u.shop.id = ? , u.headicon = ? where u.id = ?";
			query = session.createQuery(hql);
			query.setParameter(0, user.getUsername());
			query.setParameter(1, user.getShop().getId());
			query.setParameter(2, user.getHeadicon());
			query.setParameter(3, user.getId());
		}
		
		query.executeUpdate();
	}

	@Override
	public void deleteUser(int userId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " update User u set u.valid = 1 where u.id = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId); 
		query.executeUpdate();
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
		String hql = " from User where username = ? and password = ? and valid = 0";
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

	@Override
	public List<User> getUserListByPage(int limit, int offset) {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " from User order by createtime desc"; 
        final Query query = session.createQuery(hql); 
        query.setFirstResult(offset);    
        query.setMaxResults(limit); 
        @SuppressWarnings("unchecked")
		final List<User> list = query.list(); 
		return list;
	}

	@Override
	public Long getUserCount() {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " select count(u) from User u"; 
        final Query query = session.createQuery(hql); 
        return (Long)query.uniqueResult();
	}

	@Override
	public Optional<User> findUserById(int userId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from User where Id = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId); 
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
	public List<User> findUserByShopIdAndUsername(int shopId, String username, int iDisplayStart, int iDisplayLength) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "";
		Query query = null;
		if (shopId == 0 && (username == null || username.equals(""))){
			hql = " from User order by createtime desc";
			query = session.createQuery(hql);
		}
		else if (shopId == 0 && username != null && !username.equals("")){
			hql = " from User where username = ? order by createtime desc";
			query = session.createQuery(hql);
			query.setParameter(0, username);
		}
		else if (shopId != 0 && (username == null || username.equals(""))){
			hql = " from User where shopId = ? order by createtime desc";
			query = session.createQuery(hql);
			query.setParameter(0, shopId);
		}
		else{
			hql = " from User where username = ? and shopId = ? order by createtime desc";
			query = session.createQuery(hql);
			query.setParameter(0, username);
			query.setParameter(1, shopId);
		}
		query.setFirstResult(iDisplayStart);
		query.setMaxResults(iDisplayLength);
		return query.list();
	}

	@Override
	public Long getUserCountByCondition(int shopId, String username) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "";
		Query query = null;
		if (shopId == 0 && (username == null || username.equals(""))){
			hql = " select count(u) from User u";
			query = session.createQuery(hql);
		}
		else if (shopId == 0 && username != null && !username.equals("")){
			hql = " select count(u) from User u where u.username = ?";
			query = session.createQuery(hql);
			query.setParameter(0, username);
		}
		else if (shopId != 0 && (username == null || username.equals(""))){
			hql = " select count(u) from User u where u.shop.id = ?";
			query = session.createQuery(hql);
			query.setParameter(0, shopId);
		}
		else{
			hql = " select count(u) from User u where u.username = ? and u.shop.id = ?";
			query = session.createQuery(hql);
			query.setParameter(0, username);
			query.setParameter(1, shopId);
		}
        return (Long)query.uniqueResult();
	}

	@Override
	public void updatePwd(int userId, String password, String slot) {
		System.out.println(userId+"|"+password+"|"+slot);
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " update User u set u.password = ?, u.slot = ? where u.id = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, password); 
		query.setParameter(1, slot); 
        query.setParameter(2, userId); 
		query.executeUpdate();
	}

	@Override
	public void disableAccount(int userId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " update User u set u.valid = 1 where u.id = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId); 
		query.executeUpdate();
	}

	@Override
	public void enableAccount(int userId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " update User u set u.valid = 0 where u.id = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId); 
		query.executeUpdate();
	}

	@Override
	public void appUpdateData(String phone, String headicon, int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "";
		Query query = null;
		if (!phone.equals("") && !headicon.equals("")){
			hql = " update User u set u.phone = ? , u.headicon = ? where u.id = ?";
			query = session.createQuery(hql);
			query.setParameter(0, phone);
			query.setParameter(1, headicon);
			query.setParameter(2, id);
		}
		else if(phone.equals("") && !headicon.equals("")){
			hql = " update User u set u.headicon = ? where u.id = ?";
			query = session.createQuery(hql);
			query.setParameter(0, headicon);
			query.setParameter(1, id);
		}
		else if(!phone.equals("") &&  headicon.equals("")){
			hql = " update User u set u.phone = ? where u.id = ?";
			query = session.createQuery(hql);
			query.setParameter(0, headicon);
			query.setParameter(1, id);
		}
		query.executeUpdate();
	}
	
}
