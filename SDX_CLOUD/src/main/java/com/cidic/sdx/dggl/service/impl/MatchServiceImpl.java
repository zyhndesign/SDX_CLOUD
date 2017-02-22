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

@Repository
@Transactional
@Component
@Qualifier(value = "matchServiceImpl")
public class MatchServiceImpl implements MatchService {

	@Autowired
	@Qualifier("matchDaoImpl")
	private MatchDao matchDaoImpl;
	
	@Override
	public void createMatch(Match match) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMatch(Match match) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMatch(int matchId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Match> findMatchByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
