package com.cidic.sdx.dggl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.dggl.dao.FeedbackDao;
import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.model.Matchlist;
import com.cidic.sdx.dggl.service.FeedbackService;

@Repository
@Component
@Qualifier(value = "feedbackServiceImpl")
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	@Qualifier("feedbackDaoImpl")
	private FeedbackDao feedbackDaoImpl;
	
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
