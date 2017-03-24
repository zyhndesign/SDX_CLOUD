package com.cidic.sdx.dggl.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.MatchDao;
import com.cidic.sdx.dggl.model.Match;
import com.cidic.sdx.dggl.model.MatchListModel;
import com.cidic.sdx.dggl.model.Matchlist;
import com.cidic.sdx.dggl.service.MatchService;
import com.cidic.sdx.util.ResponseCodeUtil;

@Repository
@Transactional
@Component
@Qualifier(value = "matchServiceImpl")
public class MatchServiceImpl implements MatchService {

	@Autowired
	@Qualifier("matchDaoImpl")
	private MatchDao matchDaoImpl;
	
	@Override
	public int createMatch(Match match) {
		try{
			match.setCreatetime(new Date());
			for (Matchlist mListObj : match.getMatchlists()){
				mListObj.setMatch(match);
			}
			matchDaoImpl.createMatch(match);
			return ResponseCodeUtil.MATCH_OPERATION_SUCCESS;
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
			System.out.println("========:"+e.getMessage());
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
	public void updateShareStatus(int userId, int shareStatus) {
		matchDaoImpl.updateShareStatus(userId, shareStatus);
	}

	@Override
	public MatchListModel getAppMatchByShareStatus(int userId, int shareStatus, int offset, int limit) {
		MatchListModel matchListModel = new MatchListModel();
		matchListModel.setList(matchDaoImpl.getAppMatchByShareStatus(userId, shareStatus, offset, limit));
		matchListModel.setCount(matchDaoImpl.getAppMatchShareCountByUser(userId, shareStatus));
		return matchListModel;
	}

	@Override
	public List<Match> getMatchByDataStatus(int userId, int dataStatus, int offset, int limit) {
		return matchDaoImpl.getMatchByDataStatus(userId, dataStatus, offset, limit);
	}

	@Override
	public MatchListModel getMatchByShareStatus(int shareStatus, int offset, int limit) {
		MatchListModel matchListModel = new MatchListModel();
		matchListModel.setList(matchDaoImpl.getMatchByShareStatus(shareStatus, offset, limit));
		matchListModel.setCount(matchDaoImpl.getMatchShareCountByUser(shareStatus));
		return matchListModel;
	}

}
