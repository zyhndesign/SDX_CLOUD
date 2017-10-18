package com.cidic.sdx.dggl.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.ShareDao;
import com.cidic.sdx.dggl.model.HotMatchModel;
import com.cidic.sdx.dggl.model.Match;
import com.cidic.sdx.dggl.model.Share;
import com.cidic.sdx.dggl.model.User;

@Repository
@Transactional
@Component
@Qualifier(value = "shareDaoImpl")
public class ShareDaoImpl implements ShareDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createShare(Share share) {
		Session session = sessionFactory.getCurrentSession();
		session.save(share);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Share> getShareList(int matchId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select shareContent,sharedlist from Share where userId = ? and matchId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		query.setParameter(1, matchId);
		
		return query.list();
	}

	@Override
	public List<Integer> getVipuserShareList(int userId, String vipName, int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select s.match.id from Share s where s.user.id = ? and s.sharedlist like ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId); 
        query.setParameter(1, "%"+vipName +"%");
        query.setFirstResult(offset);    
        query.setMaxResults(limit);
		List<Integer> list = query.list();
        return list;
	}

	@Override
	public Share getShareByCode(String code) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Share where code = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, code); 
        @SuppressWarnings("unchecked")
		List<Share> list = query.list();
        if (list.size() > 0){
        	return (Share)list.get(0);
        }
        else{
        	return new Share();
        }
	}	
}
