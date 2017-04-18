package com.cidic.sdx.dggl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.FeedbackDao;
import com.cidic.sdx.dggl.dao.MatchDao;
import com.cidic.sdx.dggl.model.CostumeModel;
import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.model.HotMatchModel;
import com.cidic.sdx.dggl.model.Match;
import com.cidic.sdx.dggl.model.Matchlist;
import com.cidic.sdx.dggl.service.FeedbackService;
import com.cidic.sdx.hpgl.dao.HpIndexDao;
import com.cidic.sdx.util.ResponseCodeUtil;

@Repository
@Component
@Qualifier(value = "feedbackServiceImpl")
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	@Qualifier("feedbackDaoImpl")
	private FeedbackDao feedbackDaoImpl;
	
	@Autowired
	@Qualifier(value = "hpIndexDaoImpl")
	private HpIndexDao hpIndexDaoImpl;
	
	@Autowired
	@Qualifier("matchDaoImpl")
	private MatchDao matchDaoImpl;
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int createFeedback(Feedback feedback) {
		try{
			Optional<Feedback> feedbackObj = feedbackDaoImpl.getFeedbackByUserIdAndMatchlistID(feedback.getUserId(), feedback.getMatch().getId());
			if(feedbackObj.isPresent()){
				return ResponseCodeUtil.FEEDBACK_OPERATION_EXIST; //已经点赞过
			}
			else{
				feedbackDaoImpl.createFeedback(feedback);
			}
			return ResponseCodeUtil.FEEDBACK_OPERATION_SUCCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.FEEDBACK_OPERATION_FAILURE;
		}
	}

	@Override
	public List<HotMatchModel> getFeedbackListPageByUserId(int userId,int limit, int offset) {
		
		List<HotMatchModel> list = feedbackDaoImpl.getFeedbackListPageByUserId(userId, limit, offset);
		if (list.size() > 0){
			List<Integer> ids = new ArrayList<>(10);
			for (HotMatchModel hmModel : list){
				ids.add(hmModel.getMatchId());
			}
			List<Match> matchList = matchDaoImpl.getMatchByIds(ids);
			
			for (Match match : matchList){
				
				Set<Matchlist> mls = match.getMatchlists();
				for (Matchlist mlModel : mls){
					CostumeModel custumeModel = hpIndexDaoImpl.getClothUrl(mlModel.getInnerClothId());
					mlModel.setCustumeModel(custumeModel);
				}
				
				for (HotMatchModel hmModel : list){
					if (match.getId() == hmModel.getMatchId()){
						hmModel.setMatch(match);
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public List<HotMatchModel> getTopThreeDataByUserId(int userId){
		List<HotMatchModel> list = feedbackDaoImpl.getTopThreeDataByUserId(userId);
		
		if (list.size() > 0){
			List<Integer> ids = new ArrayList<>(10);
			for (HotMatchModel hmModel : list){
				ids.add(hmModel.getMatchId());
			}
			List<Match> matchList = matchDaoImpl.getMatchByIds(ids);
			
			for (Match match : matchList){
				
				Set<Matchlist> mls = match.getMatchlists();
				for (Matchlist mlModel : mls){
					CostumeModel custumeModel = hpIndexDaoImpl.getClothUrl(mlModel.getInnerClothId());
					mlModel.setCustumeModel(custumeModel);
				}
				
				for (HotMatchModel hmModel : list){
					if (match.getId() == hmModel.getMatchId()){
						hmModel.setMatch(match);
					}
				}
			}
		}
		return list;
	}
	
	@Override
	public int updateFeedback(Feedback feedback){
		try{
			Optional<Feedback> feedbackObj = feedbackDaoImpl.getFeedbackByUserIdAndMatchlistID(feedback.getUserId(), feedback.getMatch().getId());
			feedbackObj.ifPresent((feed)->{
				feedbackDaoImpl.deleteFeedback(feed.getUserId(), feed.getMatch().getId() );
			});
			
			return ResponseCodeUtil.FEEDBACK_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.FEEDBACK_OPERATION_FAILURE;
		}
	}
}
