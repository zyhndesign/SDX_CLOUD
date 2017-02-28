package com.cidic.sdx.dggl.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.FeedbackDao;
import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.service.FeedbackService;
import com.cidic.sdx.util.ResponseCodeUtil;

@Repository
@Component
@Qualifier(value = "feedbackServiceImpl")
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	@Qualifier("feedbackDaoImpl")
	private FeedbackDao feedbackDaoImpl;
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int createFeedback(Feedback feedback) {
		try{
			Optional<Feedback> feedbackObj = feedbackDaoImpl.getFeedbackByUserIdAndMatchlistID(feedback.getLikeId(), feedback.getMatchlist().getId());
			if(feedbackObj.isPresent()){
				return ResponseCodeUtil.FEEDBACK_OPERATION_EXIST; //已经点赞过
			}
			else{
				feedbackDaoImpl.createFeedback(feedback);
			}
			return ResponseCodeUtil.FEEDBACK_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.FEEDBACK_OPERATION_FAILURE;
		}
	}

	@Override
	public List<Feedback> getFeedbackListPageByUserId(int userId,int limit, int offset) {
		return feedbackDaoImpl.getFeedbackListPageByUserId(userId, limit, offset);
	}

	@Override
	public int updateFeedback(Feedback feedback){
		try{
			Optional<Feedback> feedbackObj = feedbackDaoImpl.getFeedbackByUserIdAndMatchlistID(feedback.getLikeId(), feedback.getMatchlist().getId());
			feedbackObj.ifPresent((feed)->{
				feedbackDaoImpl.deleteFeedback(feed.getLikeId(), feed.getMatchlist().getId() );
			});
			
			return ResponseCodeUtil.FEEDBACK_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.FEEDBACK_OPERATION_FAILURE;
		}
	}
}
