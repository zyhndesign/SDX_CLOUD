package com.cidic.sdx.dggl.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.dggl.dao.AppHelpDao;
import com.cidic.sdx.dggl.model.Apphelp;

@Repository
@Component
@Qualifier(value = "appHelpDaoImpl")
public class AppHelpDaoImpl implements AppHelpDao {

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
	public void createHelpInfo(Apphelp appHelp) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.save(appHelp);
	}

}
