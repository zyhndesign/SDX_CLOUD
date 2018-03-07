package com.cidic.sdx.dggl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.FeedbackDao;
import com.cidic.sdx.dggl.dao.MatchDao;
import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.model.HotMatchListModel;
import com.cidic.sdx.dggl.model.HotMatchModel;
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
	public int createFeedback(Feedback feedback,int matchId) {
		try{
			Optional<Feedback> feedbackObj = feedbackDaoImpl.getFeedbackByUserIdAndMatchlistID(feedback.getUserId(), feedback.getMatchlist().getId(),feedback.getVipId());
			if(feedbackObj.isPresent()){
				return ResponseCodeUtil.FEEDBACK_OPERATION_EXIST; //已经点赞过
			}
			else{
				feedbackDaoImpl.createFeedback(feedback);
				matchDaoImpl.updateBackStatus(matchId, 1);
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
	
		for (HotMatchModel hotMatchModel : list){
			hotMatchModel.setInnerClothUrl(hpIndexDaoImpl.getData(hotMatchModel.getInnerClothId()));
			hotMatchModel.setOutClothUrl(hpIndexDaoImpl.getData(hotMatchModel.getOutClothId()));
			hotMatchModel.setTrousersClothUrl(hpIndexDaoImpl.getData(hotMatchModel.getTrousersId()));
		}
		return list;
	}

	@Override
	public List<HotMatchModel> getTopThreeDataByUserId(int userId){
		List<HotMatchModel> list = feedbackDaoImpl.getTopThreeDataByUserId(userId);
		
		for (HotMatchModel hotMatchModel : list){
			hotMatchModel.setInnerClothUrl(hpIndexDaoImpl.getData(hotMatchModel.getInnerClothId()));
			hotMatchModel.setOutClothUrl(hpIndexDaoImpl.getData(hotMatchModel.getOutClothId()));
			hotMatchModel.setTrousersClothUrl(hpIndexDaoImpl.getData(hotMatchModel.getTrousersId()));
		}
		
		return list;
	}
	
	@Override
	public int updateFeedback(Feedback feedback){
		try{
			Optional<Feedback> feedbackObj = feedbackDaoImpl.getFeedbackByUserIdAndMatchlistID(feedback.getUserId(), feedback.getMatchlist().getId(), feedback.getVipId());
			feedbackObj.ifPresent((feed)->{
				feedbackDaoImpl.deleteFeedback(feed.getUserId(), feed.getMatchlist().getId() );
			});
			
			return ResponseCodeUtil.FEEDBACK_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.FEEDBACK_OPERATION_FAILURE;
		}
	}

	@Override
	public Map<Integer, List<String>> getFeedbackVipName(int userId, String matchlistIds) {
		Map<Integer, List<String>> map = new HashMap<>(4);
		String[] ids = matchlistIds.split(",");
		for (String matchlistId : ids){
			System.out.println(matchlistId);
			map.put(Integer.parseInt(matchlistId.toString()), feedbackDaoImpl.getFeedbackVipName(userId, Integer.parseInt(matchlistId.toString())));
		}
		return map;
	}

	@Override
	public List<Integer> getFeedbackDataByVipIdAndMatchlistIds(String[] matchlistIdsArray, int vipId, int userId) {
		List<Integer> conditionIdList = new ArrayList<>(4);
		for (String s : matchlistIdsArray){
			conditionIdList.add(Integer.parseInt(s));
		}
		
		List<Feedback> feedbackList = feedbackDaoImpl.getFeedbackDataByVipIdAndMatchlistIds(conditionIdList, vipId,userId);
		List<Integer> idList = new ArrayList<>(4);
		for (Feedback feedBack : feedbackList){
			idList.add(feedBack.getMatchlist().getId());
		}
		return idList;
	}

	@Override
	public HotMatchListModel getFeedbackListPage(int limit, int offset) {
		HotMatchListModel hotMatchListModel = new HotMatchListModel();
		List<HotMatchModel> list = feedbackDaoImpl.getFeedbackListPage(limit, offset);
		
		for (HotMatchModel hotMatchModel : list){
			hotMatchModel.setInnerClothUrl(hpIndexDaoImpl.getData(hotMatchModel.getInnerClothId()));
			hotMatchModel.setOutClothUrl(hpIndexDaoImpl.getData(hotMatchModel.getOutClothId()));
			hotMatchModel.setTrousersClothUrl(hpIndexDaoImpl.getData(hotMatchModel.getTrousersId()));
		}
		
		int count = feedbackDaoImpl.getFeedbackCount();
		hotMatchListModel.setList(list);
		hotMatchListModel.setCount(count);
		
		return hotMatchListModel;
	}
}
