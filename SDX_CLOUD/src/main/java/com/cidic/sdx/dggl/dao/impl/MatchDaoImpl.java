package com.cidic.sdx.dggl.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.MatchDao;
import com.cidic.sdx.dggl.model.Match;

@Repository
@Transactional
@Component
@Qualifier(value = "matchDaoImpl")
public class MatchDaoImpl implements MatchDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createMatch(Match match) {
		
	}

	@Override
	public void updateMatch(Match match) {
		
	}

	@Override
	public void deleteMatch(int matchId) {
		
	}

	@Override
	public List<Match> findMatchByUser(int userId) {
		return null;
	}

}
