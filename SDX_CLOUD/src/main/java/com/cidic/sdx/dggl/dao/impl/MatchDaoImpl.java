package com.cidic.sdx.dggl.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.dggl.dao.MatchDao;
import com.cidic.sdx.dggl.model.Match;

@Repository
@Component
@Qualifier(value = "matchDaoImpl")
public class MatchDaoImpl implements MatchDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public int createMatch(Match match) {
		Session session = sessionFactory.getCurrentSession();
		return (int) session.save(match);
	}

	@Override
	public void updateMatch(Match match) {
		Session session = sessionFactory.getCurrentSession();
		session.update(match);
	}

	@Override
	public void deleteMatch(int matchId) {
		Session session = sessionFactory.getCurrentSession();
		Match match = new Match();
		match.setId(matchId);
		session.delete(match);
	}

	
	@Override
	public List<Match> findMatchByUser(int userId, int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Match where userId = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        query.setFirstResult(offset);    
        query.setMaxResults(limit);
        query.setCacheable(true);
        @SuppressWarnings("unchecked")
        List<Match> list = query.list();
		return list;
	}

	@Override
	public int getAppMatchShareCountByUser(int userId, int shareStatus) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select count(m) from Match where userId = ? and sharestatus = ? and draftstatus = ?";
		final Query query = session.createQuery(hql); 
		query.setParameter(0, userId);
		query.setParameter(1, shareStatus);
		query.setParameter(2, 0);
        return (int)query.uniqueResult();
	}

	@Override
	public void updateShareStatus(int matchId, int shareStatus) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update from Match set sharestatus = ? where id = ?";
		final Query query = session.createQuery(hql); 
		query.setParameter(1, matchId);
		query.setParameter(0, shareStatus);
		query.executeUpdate();
	}
	
	@Override
	public void updateDraftStatus(int matchId, int draftStatus) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update from Match set draftstatus = ? where matchId = ?";
		final Query query = session.createQuery(hql); 
		query.setParameter(1, matchId);
		query.setParameter(0, draftStatus);
		query.executeUpdate();
	}

	@Override
	public List<Match> getMatchByShareStatus(int userId, int shareStatus, int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		Query query = null;
		if (shareStatus == -1){
			if (userId == 0){
				hql = " from Match where draftstatus = ?";
				query = session.createQuery(hql);
		        query.setParameter(0, (byte)0);
			}
			else{
				hql = " from Match where userId = ? and draftstatus = ?";
				query = session.createQuery(hql);
				query.setParameter(0, userId);
		        query.setParameter(1, (byte)0);
			}
		}
		else{
			if (userId == 0){
				hql = " from Match where sharestatus = ? and draftstatus = ?";
				query = session.createQuery(hql);
				query.setParameter(0, (byte)shareStatus);
				query.setParameter(1, (byte)0);
			}
			else{
				hql = " from Match where sharestatus = ? and draftstatus = ? and userId = ?";
				query = session.createQuery(hql);
				query.setParameter(0, (byte)shareStatus);
				query.setParameter(1, (byte)0);
				query.setParameter(2, userId);
			}
		}
		
        query.setFirstResult(offset);    
        query.setMaxResults(limit);
        query.setCacheable(true);
        @SuppressWarnings("unchecked")
        List<Match> list = query.list();
		return list;
	}
	
	@Override
	public List<Match> getAppMatchByShareStatus(int userId, int shareStatus, int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Match where userId = ? and sharestatus = ? and draftstatus = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, (byte)shareStatus);
        query.setParameter(2, (byte)0);
        query.setFirstResult(offset);    
        query.setMaxResults(limit);
        query.setCacheable(true);
        @SuppressWarnings("unchecked")
        List<Match> list = query.list();
		return list;
	}

	@Override
	public List<Match> getAppMatchByDraftStatus(int userId, int draftStatus, int offset, int limit){
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Match where userId = ? and draftstatus = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, (byte)1);
        query.setFirstResult(offset);    
        query.setMaxResults(limit);
        query.setCacheable(true);
        @SuppressWarnings("unchecked")
        List<Match> list = query.list();
		return list;
	}
	
	@Override
	public List<Match> getMatchByDataStatus(int userId, int dataStatus, int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Match where userId = ? and draftstatus = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(0, (byte)dataStatus);
        query.setFirstResult(offset);    
        query.setMaxResults(limit);
        query.setCacheable(true);
        @SuppressWarnings("unchecked")
        List<Match> list = query.list();
		return list;
	}

	@Override
	public List<Match> getMatchByDraftStatus(int userId, int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Match where userId = ? and draftstatus = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, 1);
        query.setFirstResult(offset);    
        query.setMaxResults(limit);
        query.setCacheable(true);
        @SuppressWarnings("unchecked")
        List<Match> list = query.list();
		return list;
	}

	@Override
	public int getMatchShareCountByUser(int userId, int shareStatus) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		Query query = null; 
		if (shareStatus == -1){
			if (userId == 0){
				hql = " select count(m) from Match m where draftstatus = ?";
				query = session.createQuery(hql); 
				query.setParameter(0, (byte)0);
			}
			else{
				hql = " select count(m) from Match m where draftstatus = ? and userId = ?";
				query = session.createQuery(hql);
				query.setParameter(0, (byte)0);
				query.setParameter(1, userId);
			}
		}
		else{
			if (userId == 0){
				hql = " select count(m) from Match m where sharestatus = ? and draftstatus = ?";
				query = session.createQuery(hql); 
				query.setParameter(0, (byte)shareStatus);
				query.setParameter(1, (byte)0);
			}
			else{
				hql = " select count(m) from Match m where sharestatus = ? and draftstatus = ? and userId = ?";
				query = session.createQuery(hql); 
				query.setParameter(0, (byte)shareStatus);
				query.setParameter(1, (byte)0);
				query.setParameter(2, userId);
			}
		}
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public void updateBackStatus(int userId, int backStatus) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update from Match set backstatus = ? where matchId = ?";
		final Query query = session.createQuery(hql); 
		query.setParameter(1, userId);
		query.setParameter(0, (byte)backStatus);
		query.executeUpdate();
	}

	@Override
	public List<Match> getAppMatchByBackStatus(int userId, int backStatus, int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Match where userId = ? and backstatus = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, (byte)backStatus);
        query.setFirstResult(offset);    
        query.setMaxResults(limit);
        query.setCacheable(true);
        @SuppressWarnings("unchecked")
        List<Match> list = query.list();
		return list;
	}

	@Override
	public Match findMatchByMatchId(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Match) session.get(Match.class, id);
	}

	@Override
	public List<Match> getMatchByIds(List<Integer> ids) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Match m where m.id in (:ids)";
		Query query = session.createQuery(hql);
        query.setParameterList("ids", ids);
        @SuppressWarnings("unchecked")
        List<Match> list = query.list();
		return list;
	}

	@Override
	public void updateShareAndDraftStatus(int matchId, int shareStatus, int draftStatus) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update from Match set draftstatus = ?, sharestatus = ? where matchId = ?";
		final Query query = session.createQuery(hql); 
		query.setParameter(2, matchId);
		query.setParameter(1, shareStatus);
		query.setParameter(0, draftStatus);
		query.executeUpdate();
	}

	@Override
	public Map<String, Integer> getStatisticsDataByWeek(int userId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT DATE_FORMAT(createtime,'%Y-%u') as time,sum(Id)  matchCount FROM sdx_cloud.match where userId = ? GROUP BY  time";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, userId);
		List list = query.list();
		Map<String, Integer> map = null;
		for(int i = 0; i < list.size(); i++)
        {
			map = new HashMap<String, Integer>();
			Object []o = (Object[])list.get(i);
			String groupName = (String)o[0];
			int count = (Integer)o[1];
			map.put(groupName, count);
        }
		return map;
	}

	@Override
	public Map<String, Integer> getStatisticsDataByMonth(int userId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT DATE_FORMAT(createtime,'%Y-%m') as time,sum(Id)  matchCount FROM sdx_cloud.match userId = ?  GROUP BY  time";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, userId);
		List list = query.list();
		Map<String, Integer> map = null;
		for(int i = 0; i < list.size(); i++)
        {
			map = new HashMap<String, Integer>();
			Object []o = (Object[])list.get(i);
			String groupName = (String)o[0];
			int count = (Integer)o[1];
			map.put(groupName, count);
        }
		return map;
	}

	@Override
	public Map<String, Integer> getStatisticsDataByYear(int userId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT DATE_FORMAT(createtime,'%Y') as time,sum(Id)  matchCount FROM sdx_cloud.match userId = ?  GROUP BY  time";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, userId);
		List list = query.list();
		Map<String, Integer> map = null;
		for(int i = 0; i < list.size(); i++)
        {
			map = new HashMap<String, Integer>();
			Object []o = (Object[])list.get(i);
			String groupName = (String)o[0];
			int count = (Integer)o[1];
			map.put(groupName, count);
        }
		return map;
	}
	
}
