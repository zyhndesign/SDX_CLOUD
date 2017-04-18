package com.cidic.sdx.dggl.dao;

import java.util.List;

import com.cidic.sdx.dggl.model.Match;

public interface MatchDao {

	public void createMatch(Match match);
	
    public void updateMatch(Match match);
    
    public void deleteMatch(int matchId);
    
    public List<Match> findMatchByUser(int userId, int offset, int limit);
    
    public int getAppMatchShareCountByUser(int userId, int shareStatus);
 
    public int getMatchShareCountByUser(int userId,int shareStatus);
    
    public void updateShareStatus(int userId, int shareStatus);
    
    public void updateDraftStatus(int userId, int draftStatus);
    
    public List<Match> getMatchByShareStatus(int userId,int shareStatus, int offset, int limit);
    
    public List<Match> getAppMatchByShareStatus(int userId, int shareStatus, int offset, int limit);
    
    public List<Match> getAppMatchByDraftStatus(int userId, int draftStatus, int offset, int limit);
    
    public List<Match> getMatchByDataStatus(int userId, int dataStatus, int offset, int limit);
    
    public List<Match> getMatchByDraftStatus(int userId, int offset, int limit);
    
    public List<Match> getMatchByIds(List<Integer> ids);
}
