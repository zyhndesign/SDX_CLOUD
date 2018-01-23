package com.cidic.sdx.dggl.service;

import java.util.List;
import java.util.Map;

import com.cidic.sdx.dggl.model.Match;
import com.cidic.sdx.dggl.model.MatchListModel;

public interface MatchService {

	public int createMatch(Match match);
	
    public int updateMatch(Match match);
    
    public int deleteMatch(int matchId);
    
    public List<Match> findMatchByUser(int userId, int offset, int limit);
    
    public int updateShareStatus(int userId, int shareStatus);
    
    public int updateDraftStatus(int matchId, int draftStatus);
    
    public int updateBackStatus(int matchId, int backStatus);
    
    public int updateShareAndDraftStatus(int matchId, int shareStatus, int draftStatus);
    
    public MatchListModel getMatchByShareStatus(int userId,int shareStatus, int offset, int limit);
    
    public List<Match> getAppMatchByShareStatus(int userId, int shareStatus, int offset, int limit);
    
    public List<Match> getAppMatchByDraftStatus(int userId, int draftStatus, int offset, int limit);
    
    public List<Match> getAppMatchByBackStatus(int userId, int backStatus, int offset, int limit);
    
    public List<Match> getMatchByDataStatus(int userId, int dataStatus, int offset, int limit);
    
    public Match findMatchByMatchId(int id);
    
    public List<Match> getMatchByPushHistory(String vipName,int userId,int limit, int offset);
    
    public Map<String,Integer> getStatisticsDataByWeek(int userId);
    
    public Map<String,Integer> getStatisticsDataByMonth(int userId);
    
    public Map<String,Integer> getStatisticsDataByYear(int userId);
}
