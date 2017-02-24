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

	public Optional<Feedback> getFeedbackByUserIdAndMatchlistID(int userId, int matchlistId){
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Feedback where likeId = ? and matchlistId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId); 
        query.setParameter(1, matchlistId); 
        query.setMaxResults(1);
        Optional<Feedback> optFeedback = Optional.ofNullable((Feedback)query.uniqueResult());
        return optFeedback;
	}
	
	public int deleteFeedback(int userId, int matchlistId){
		Session session = sessionFactory.getCurrentSession();
		String sql = " delete from feedback where likeId= " + userId + " and matchlistId=" + matchlistId;
		Query query = session.createSQLQuery(sql);
		return query.executeUpdate();
	}
}
