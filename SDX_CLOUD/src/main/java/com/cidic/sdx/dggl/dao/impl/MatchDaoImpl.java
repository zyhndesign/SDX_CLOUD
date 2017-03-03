package com.cidic.sdx.dggl.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	public void createMatch(Match match) {
		Session session = sessionFactory.getCurrentSession();
		session.save(match);
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

}
