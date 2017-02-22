package com.cidic.sdx.dggl.dao;

import java.util.List;

import com.cidic.sdx.dggl.model.Match;

public interface MatchDao {

	public void createMatch(Match match);
	
    public void updateMatch(Match match);
    
    public void deleteMatch(int matchId);
    
    public List<Match> findMatchByUser(int userId);
}
