package com.cidic.sdx.dggl.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.ShareDao;
import com.cidic.sdx.dggl.model.Share;

@Repository
@Transactional
@Component
@Qualifier(value = "shareDaoImpl")
public class ShareDaoImpl implements ShareDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createShare(Share share) {
		Session session = sessionFactory.getCurrentSession();
		session.save(session);
	}

}
