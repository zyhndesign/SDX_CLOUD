package com.cidic.sdx.dggl.service;

import java.util.List;

import com.cidic.sdx.dggl.model.Feedback;

public interface FeedbackService {
	
	public int createFeedback(Feedback feedback);
	
	public List<Feedback> getFeedbackListPageByUserId(int userId,int limit, int offset);
	
	public int updateFeedback(Feedback feedback);
}
