package com.cidic.sdx.dggl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.dggl.dao.FeedbackDao;
import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.service.FeedbackService;
import com.cidic.sdx.util.ResponseCodeUtil;

@Repository
@Component
@Qualifier(value = "feedbackServiceImpl")
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	@Qualifier("feedbackDaoImpl")
	private FeedbackDao feedbackDaoImpl;
	
	@Override
	public int createFeedback(Feedback feedback) {
		try{
			feedbackDaoImpl.createFeedback(feedback);
			return ResponseCodeUtil.FEEDBACK_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.FEEDBACK_OPERATION_FAILURE;
		}
	}

	@Override
	public List<Feedback> getFeedbackListByUserId(int userId) {
		return feedbackDaoImpl.getFeedbackListByUserId(userId);
	}

}
