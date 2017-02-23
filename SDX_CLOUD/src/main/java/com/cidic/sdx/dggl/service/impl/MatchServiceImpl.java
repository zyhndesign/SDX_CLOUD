package com.cidic.sdx.dggl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.MatchDao;
import com.cidic.sdx.dggl.model.Match;
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
			return ResponseCodeUtil.MATCH_OPERATION_FAILURE;
		}
	}

	@Override
	public List<Match> findMatchByUser(int userId, int offset, int limit) {
		
		return matchDaoImpl.findMatchByUser(userId, offset, limit);
	}

}
