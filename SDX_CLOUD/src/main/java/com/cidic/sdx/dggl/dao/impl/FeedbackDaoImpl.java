package com.cidic.sdx.dggl.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.FeedbackDao;
import com.cidic.sdx.dggl.model.Feedback;

@Repository
@Transactional
@Component
@Qualifier(value = "feedbackDaoImpl")
public class FeedbackDaoImpl implements FeedbackDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createFeedback(Feedback feedback) {
		Session session = sessionFactory.getCurrentSession();
		session.save(feedback);
	}

	@Override
	public List<Feedback> getFeedbackListByUserId(int userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Feedback where likeId = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId); 
        @SuppressWarnings("unchecked")
		List<Feedback> list = query.list();
        return list;
	}

}
