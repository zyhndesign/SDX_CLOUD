package com.cidic.sdx.dggl.service;

import java.util.List;
import com.cidic.sdx.dggl.model.Match;

public interface MatchService {

	public int createMatch(Match match);
	
    public int updateMatch(Match match);
    
    public int deleteMatch(int matchId);
    
    public List<Match> findMatchByUser(int userId, int offset, int limit);
    
    public void updateShareStatus(int userId, int shareStatus);
    
    public List<Match> getMatchByShareStatus(int userId, int shareStatus, int offset, int limit);
    
    public List<Match> getMatchByDataStatus(int userId, int dataStatus, int offset, int limit);
}
