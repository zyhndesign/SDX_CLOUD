package com.cidic.sdx.dggl.service;

import java.util.List;
import com.cidic.sdx.dggl.model.Match;

public interface MatchService {

	public int createMatch(Match match);
	
    public int updateMatch(Match match);
    
    public int deleteMatch(int matchId);
    
    public List<Match> findMatchByUser(int userId, int offset, int limit);
}
