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

import com.cidic.sdx.dggl.dao.VipUserDao;
import com.cidic.sdx.dggl.model.Shop;
import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.model.Vipuser;

@Repository
@Component
@Qualifier(value = "vipUserDaoImpl")
public class VipUserDaoImpl implements VipUserDao {

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
	public void createVipUser(Vipuser user) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.save(user);
	}

	@Override
	public void updateVipUser(Vipuser user) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(user);
	}

	@Override
	public void deleteVipUser(int userId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " update Vipuser u set u.valid = 1 where u.id = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId); 
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vipuser> getVipuserByShoppingGuideId(int shoppingGuideId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Vipuser u where u.user.id = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, shoppingGuideId); 
		return query.list();
	}

	@Override
	public List<Vipuser> getVipuserByPage(int limit, int offset) {
		
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " from Vipuser "; 
        Query query = session.createQuery(hql);
        query.setFirstResult(offset);  
        query.setMaxResults(limit); 
        @SuppressWarnings("unchecked")
		final List<Vipuser> list = query.list(); 
        System.out.println("***********************:"+list.size());
		return list;
	}

	@Override
	public Optional<Vipuser> getVipuserByCardNumber(String cardNumber) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Vipuser u where u.cardnumber = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, cardNumber); 
        @SuppressWarnings("unchecked")
        List<Vipuser> list = query.list();
        if (list.size() > 0){
        	Optional<Vipuser> user = Optional.ofNullable(list.get(0));
     		return user;
        }
        else{
        	return Optional.empty();
        }
	}

	@Override
	public int getCountVipuserByShoppingGuideId(int shoppingGuideId) {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " select count(u) from Vipuser u where u.user.id = ?"; 
        final Query query = session.createQuery(hql); 
        query.setParameter(0, shoppingGuideId);
        Number num = (Number)query.uniqueResult();
        return num.intValue();
	}

	@Override
	public int getVipuserCount() {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " select count(u) from Vipuser u"; 
        final Query query = session.createQuery(hql); 
        Number num = (Number)query.uniqueResult();
        return num.intValue();
	}

	@Override
	public Vipuser loadVipCustomerById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Vipuser)session.get(Vipuser.class, id);
	}

	
}
