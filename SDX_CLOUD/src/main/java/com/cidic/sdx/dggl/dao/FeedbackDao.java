package com.cidic.sdx.dggl.dao;

import java.util.List;

import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.model.Matchlist;

public interface FeedbackDao {

	public void createFeedback(Feedback feedback);
	
	public void getFeedbackListByUserId(int userId);
	
	public List<Matchlist> getHotMatchListByUserId(int userId);
}
