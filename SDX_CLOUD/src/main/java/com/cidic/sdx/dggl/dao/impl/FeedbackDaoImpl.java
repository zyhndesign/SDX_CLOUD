package com.cidic.sdx.dggl.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.FeedbackDao;
import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.model.Matchlist;

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
		
	}

	@Override
	public void getFeedbackListByUserId(int userId) {
		
	}

	@Override
	public List<Matchlist> getHotMatchListByUserId(int userId) {
		
		return null;
	}

}
