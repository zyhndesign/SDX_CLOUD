package com.cidic.sdx.dggl.service;

import java.util.List;
import com.cidic.sdx.dggl.model.Match;

public interface MatchService {

	public void createMatch(Match match);
	
    public void updateMatch(Match match);
    
    public void deleteMatch(int matchId);
    
    public List<Match> findMatchByUser(int userId);
}
