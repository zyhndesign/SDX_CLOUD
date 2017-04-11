package com.cidic.sdx.dggl.service;

import java.util.List;

import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.model.HotMatchModel;

public interface FeedbackService {
	
	public int createFeedback(Feedback feedback);
	
	public List<HotMatchModel> getFeedbackListPageByUserId(int userId,int limit, int offset);
	
	public int updateFeedback(Feedback feedback);
	
	public List<HotMatchModel> getTopThreeDataByUserId(int userId);
}
