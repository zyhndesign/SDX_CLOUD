package com.cidic.sdx.dggl.dao;

import java.util.List;
import java.util.Optional;

import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.model.HotMatchModel;

public interface FeedbackDao {

	public void createFeedback(Feedback feedback);
	
	public List<HotMatchModel> getFeedbackListPageByUserId(int userId,int limit, int offset);

	public List<HotMatchModel> getTopThreeDataByUserId(int userId);
	
	public Optional<Feedback> getFeedbackByUserIdAndMatchlistID(int userId, int matchlistId, int vipId);
	
	public int deleteFeedback(int userId, int matchlistId);
	
	public List<String> getFeedbackVipName(int userId,int matchlistId);
	
	public List<Feedback> getFeedbackDataByVipIdAndMatchlistIds(List<Integer> ids, int vipId, int userId);
}
