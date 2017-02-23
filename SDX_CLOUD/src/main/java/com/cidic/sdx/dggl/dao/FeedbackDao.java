package com.cidic.sdx.dggl.dao;

import java.util.List;

import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.model.Matchlist;
import com.cidic.sdx.dggl.model.User;

public interface FeedbackDao {

	public void createFeedback(Feedback feedback);
	
	public List<Feedback> getFeedbackListByUserId(int userId);

}
