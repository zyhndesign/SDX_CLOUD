package com.cidic.sdx.dggl.service;

import java.util.List;

import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.model.Matchlist;

public interface FeedbackService {
	
	public int createFeedback(Feedback feedback);
	
	public List<Feedback> getFeedbackListPageByUserId(int userId,int limit, int offse);
	
	public int updateFeedback(Feedback feedback);
}
