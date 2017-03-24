package com.cidic.sdx.dggl.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.dggl.dao.ShopDao;
import com.cidic.sdx.dggl.model.Shop;

@Repository
@Component
@Qualifier(value = "shopDaoImpl")
public class ShopDaoImpl implements ShopDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createShop(Shop shop) {
		Session session = sessionFactory.getCurrentSession();
		session.save(shop);
	}

	@Override
	public void updateShop(Shop shop) {
		Session session = sessionFactory.getCurrentSession();
		session.update(shop);
	}

	@Override
	public void deleteShop(Shop shop) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(shop);
	}

	@Override
	public List<Shop> getShopList(int iDisplayStart, int iDisplayLength) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Shop";
		Query query = session.createQuery(hql);
		query.setFirstResult(iDisplayStart);
		query.setMaxResults(iDisplayLength);
		return query.list();
	}

	@Override
	public Long getCountShop() {
		Session session = sessionFactory.getCurrentSession();
		final String hql = " select count(u) from User u"; 
        final Query query = session.createQuery(hql); 
        return (Long)query.uniqueResult();
	}

	@Override
	public Shop loadShopById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Shop)session.get(Shop.class, id);
	}

}
