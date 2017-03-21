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

import com.cidic.sdx.dggl.dao.FeedbackDao;
import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.model.HotMatchModel;

@Repository
@Component
@Qualifier(value = "feedbackDaoImpl")
public class FeedbackDaoImpl implements FeedbackDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createFeedback(Feedback feedback) {
		Session session = sessionFactory.getCurrentSession();
		session.save(feedback);
	}

	@Override
	public List<HotMatchModel> getFeedbackListPageByUserId(int userId,int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select m.innerClothId as innerClothId,m.outClothId as outClothId, m.trousersId as trousersId,f.userId as userId,m.id as matchlistId,"
				+ " count(*) as countLike from Feedback f inner join f.matchlist m where f.userId = ? group by f.matchlist order by countLike";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId); 
        query.setFirstResult(offset);    
        query.setMaxResults(limit); 
        @SuppressWarnings("unchecked")
		List list = query.list();
        
        List<HotMatchModel> hotList = new ArrayList<HotMatchModel>(10);
        HotMatchModel hotMatchModel = null;
        for(int i=0;i<list.size();i++)
        {
        	hotMatchModel = new HotMatchModel();
            Object []o = (Object[])list.get(i);
            int innerClothId = (Integer)o[0];
            int outClothId = (Integer)o[1];
            int trousersId = (Integer)o[2];
            int userid = (Integer)o[3];
            int matchlistId = (Integer)o[4];
            int countLike = ((Number)o[5]).intValue();
            
            hotMatchModel.setInnerClothId(innerClothId);
            hotMatchModel.setOutClothId(outClothId);
            hotMatchModel.setTrousersId(trousersId);
            hotMatchModel.setUserId(userid);
            hotMatchModel.setMatchlistId(matchlistId);
            hotMatchModel.setCountLike(countLike);
        }
        return hotList;
	}

	public Optional<Feedback> getFeedbackByUserIdAndMatchlistID(int userId, int matchlistId){
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Feedback where userId = ? and matchlistId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId); 
        query.setParameter(1, matchlistId); 
        query.setMaxResults(1);
        query.setCacheable(true);
        Optional<Feedback> optFeedback = Optional.ofNullable((Feedback)query.uniqueResult());
        return optFeedback;
	}
	
	public int deleteFeedback(int userId, int matchlistId){
		Session session = sessionFactory.getCurrentSession();
		String sql = " delete from feedback where likeId= " + userId + " and matchlistId=" + matchlistId;
		Query query = session.createSQLQuery(sql);
		return query.executeUpdate();
	}
}
