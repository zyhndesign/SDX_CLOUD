package com.cidic.sdx.dggl.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.MatchDao;
import com.cidic.sdx.dggl.dao.ShareDao;
import com.cidic.sdx.dggl.model.CostumeModel;
import com.cidic.sdx.dggl.model.Match;
import com.cidic.sdx.dggl.model.MatchListModel;
import com.cidic.sdx.dggl.model.Matchlist;
import com.cidic.sdx.dggl.service.MatchService;
import com.cidic.sdx.hpgl.dao.HpIndexDao;
import com.cidic.sdx.util.ResponseCodeUtil;

@Repository
@Transactional
@Component
@Qualifier(value = "matchServiceImpl")
public class MatchServiceImpl implements MatchService {

	@Autowired
	@Qualifier("matchDaoImpl")
	private MatchDao matchDaoImpl;
	
	@Autowired
	@Qualifier(value = "hpIndexDaoImpl")
	private HpIndexDao hpIndexDaoImpl;
	
	@Autowired
	@Qualifier("shareDaoImpl")
	private ShareDao shareDaoImpl;
	
	@Override
	public int createMatch(Match match) {
		try{
			match.setCreatetime(new Date());
			for (Matchlist mListObj : match.getMatchlists()){
				mListObj.setMatch(match);
			}
			return matchDaoImpl.createMatch(match);
		}
		catch(Exception e){
			return ResponseCodeUtil.MATCH_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateMatch(Match match) {
		try{
			matchDaoImpl.updateMatch(match);
			return ResponseCodeUtil.MATCH_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.MATCH_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteMatch(int matchId) {
		try{
			matchDaoImpl.deleteMatch(matchId);
			return ResponseCodeUtil.MATCH_OPERATION_SUCCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.MATCH_OPERATION_FAILURE;
		}
	}

	@Override
	@Transactional (propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=true)
	public List<Match> findMatchByUser(int userId, int offset, int limit) {
		
		return matchDaoImpl.findMatchByUser(userId, offset, limit);
	}

	@Override
	public int updateShareStatus(int matchId, int shareStatus) {
		
		try{
			matchDaoImpl.updateShareStatus(matchId, shareStatus);
			return ResponseCodeUtil.MATCH_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.MATCH_OPERATION_FAILURE;
		}
	}

	@Override
	public List<Match> getAppMatchByShareStatus(int userId, int shareStatus, int offset, int limit) {
		return matchDaoImpl.getAppMatchByShareStatus(userId, shareStatus, offset, limit);
	}

	@Override
	public List<Match> getMatchByDataStatus(int userId, int dataStatus, int offset, int limit) {
		return matchDaoImpl.getMatchByDataStatus(userId, dataStatus, offset, limit);
	}

	@Override
	public List<Match> getAppMatchByDraftStatus(int userId, int draftStatus, int offset, int limit){
		return matchDaoImpl.getAppMatchByDraftStatus(userId, draftStatus, offset, limit);
	}
	
	@Override
	public MatchListModel getMatchByShareStatus(int userId, int shareStatus, int offset, int limit) {
		try{
			MatchListModel matchListModel = new MatchListModel();
			matchListModel.setList(matchDaoImpl.getMatchByShareStatus(userId,shareStatus, offset, limit));
			matchListModel.setCount(matchDaoImpl.getMatchShareCountByUser(userId,shareStatus));
			return matchListModel;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Match> getAppMatchByBackStatus(int userId, int backStatus, int offset, int limit) {
		
		return matchDaoImpl.getAppMatchByBackStatus(userId, backStatus, offset, limit);
	}

	@Override
	public Match findMatchByMatchId(int id) {
		Match match = matchDaoImpl.findMatchByMatchId(id);
		for (Matchlist mList : match.getMatchlists()){
			
			CostumeModel inner = hpIndexDaoImpl.getData(mList.getInnerClothId());
			CostumeModel outter = hpIndexDaoImpl.getData(mList.getOutClothId());
			CostumeModel trouser = hpIndexDaoImpl.getData(mList.getTrousersId());
			
			mList.setInnerClothUrl(inner.getProductImageUrl());
			mList.setOutClothUrl(outter.getProductImageUrl());
			mList.setTrousersUrl(trouser.getProductImageUrl());	
			
			mList.setInnerClothName(inner.getHpName());
			mList.setInnerClothShopUrl(inner.getShopURL());
			mList.setOutClothName(outter.getHpName());
			mList.setOutClothShopUrl(outter.getShopURL());
			mList.setTrouserName(trouser.getHpName());
			mList.setTrouserShopUrl(trouser.getShopURL());
			
			mList.setInnerClothNum(inner.getHpNum());
			mList.setOutClothNum(outter.getHpNum());
			mList.setTrouserClothNum(trouser.getHpNum());
		}
		
		return match;
	}

	@Override
	public List<Match> getMatchByPushHistory(String vipName, int userId,int limit, int offset) {
		List<Integer> matchIds = shareDaoImpl.getVipuserShareList(userId, vipName, limit, offset);
		if (matchIds.size() > 0){
			List<Match> list = matchDaoImpl.getMatchByIds(matchIds);
			return list;
		}
		else{
			return null;
		}
	}

	@Override
	public int updateDraftStatus(int matchId, int draftStatus) {
		try{
			matchDaoImpl.updateDraftStatus(matchId, draftStatus);
			return ResponseCodeUtil.MATCH_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.MATCH_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateBackStatus(int matchId, int backStatus) {
		
		try{
			matchDaoImpl.updateBackStatus(matchId, backStatus);
			return ResponseCodeUtil.MATCH_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.MATCH_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateShareAndDraftStatus(int matchId, int shareStatus, int draftStatus) {
		
		try{
			matchDaoImpl.updateShareAndDraftStatus(matchId, shareStatus, draftStatus);
			return ResponseCodeUtil.MATCH_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.MATCH_OPERATION_FAILURE;
		}
	}

	@Override
	public Map<String, Integer> getStatisticsDataByWeek(int userId) {
		return matchDaoImpl.getStatisticsDataByWeek(userId);
	}

	@Override
	public Map<String, Integer> getStatisticsDataByMonth(int userId) {
		return matchDaoImpl.getStatisticsDataByMonth(userId);
	}

	@Override
	public Map<String, Integer> getStatisticsDataByYear(int userId) {
		return matchDaoImpl.getStatisticsDataByYear(userId);
	}

}
