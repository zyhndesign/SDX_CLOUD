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
		String hql = "select m.innerClothId as innerClothId,m.outClothId as outClothId, m.trousersId as trousersId,"
				+ "f.userId as userId,m.id as matchlistId,m.modelurl as modelurl,"
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
            String modelurl = (String)o[5];
            int countLike = ((Number)o[6]).intValue();
            hotMatchModel.setModelurl(modelurl);
            hotMatchModel.setInnerClothId(innerClothId);
            hotMatchModel.setOutClothId(outClothId);
            hotMatchModel.setTrousersId(trousersId);
            hotMatchModel.setUserId(userid);
            hotMatchModel.setMatchlistId(matchlistId);
            hotMatchModel.setCountLike(countLike);
            hotList.add(hotMatchModel);
        }
        return hotList;
	}

	@Override
	public List<HotMatchModel> getTopThreeDataByUserId(int userId){
		Session session = sessionFactory.getCurrentSession();
		String hql = "select m.innerClothId as innerClothId,m.outClothId as outClothId, m.trousersId as trousersId,f.userId as userId,"
				+ " m.id as matchlistId,m.modelurl as modelurl,"
				+ " count(*) as countLike from Feedback f inner join f.matchlist m where f.userId = ? group by f.matchlist order by countLike";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId); 
        query.setFirstResult(0);    
        query.setMaxResults(3); 
        @SuppressWarnings("unchecked")
		List list = query.list();
        
        List<HotMatchModel> hotList = new ArrayList<HotMatchModel>(3);
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
            String modelurl = (String)o[5];
            int countLike = ((Number)o[6]).intValue();
            hotMatchModel.setModelurl(modelurl);
            hotMatchModel.setInnerClothId(innerClothId);
            hotMatchModel.setOutClothId(outClothId);
            hotMatchModel.setTrousersId(trousersId);
            hotMatchModel.setUserId(userid);
            hotMatchModel.setMatchlistId(matchlistId);
            hotMatchModel.setCountLike(countLike);
            hotList.add(hotMatchModel);
        }
        return hotList;
	}
	
	public Optional<Feedback> getFeedbackByUserIdAndMatchlistID(int userId, int matchlistId, int vipId){
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Feedback where userId = ? and matchlistId = ? and vipId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId); 
        query.setParameter(1, matchlistId); 
        query.setParameter(2, vipId); 
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

	@Override
	public List<String> getFeedbackVipName(int userId, int matchlistId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select v.vipname from Feedback f, Vipuser v where f.vipId = v.id and f.userId = ? and matchlistId = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, matchlistId);
        query.setFirstResult(0);    
        query.setMaxResults(9); 
        @SuppressWarnings("unchecked")
		List list = query.list();
        
        List<String> listResult = new ArrayList<String>(10);

        for(int i=0;i<list.size();i++)
        {
            listResult.add((String)list.get(i));
        }
        return listResult;
	}

	@Override
	public List<Feedback> getFeedbackDataByVipIdAndMatchlistIds(List<Integer> ids, int vipId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Feedback f where f.vipId = (:vipId) and f.userId = (:userId) and f.matchlist.id in (:ids)";
		Query query = session.createQuery(hql);
		query.setParameter("vipId", vipId);
        query.setParameterList("ids", ids);
        query.setParameter("userId", userId);
        @SuppressWarnings("unchecked")
        List<Feedback> list = query.list();
		return list;
	}

	@Override
	public List<HotMatchModel> getFeedbackListPage(int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select m.innerClothId as innerClothId,m.outClothId as outClothId, m.trousersId as trousersId,"
				+ "f.userId as userId,m.id as matchlistId,m.modelurl as modelurl,"
				+ " count(*) as countLike from Feedback f inner join f.matchlist m group by f.matchlist order by countLike desc";
		Query query = session.createQuery(hql);
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
            String modelurl = (String)o[5];
            int countLike = ((Number)o[6]).intValue();
            hotMatchModel.setModelurl(modelurl);
            hotMatchModel.setInnerClothId(innerClothId);
            hotMatchModel.setOutClothId(outClothId);
            hotMatchModel.setTrousersId(trousersId);
            hotMatchModel.setUserId(userid);
            hotMatchModel.setMatchlistId(matchlistId);
            hotMatchModel.setCountLike(countLike);
            hotList.add(hotMatchModel);
        }
        return hotList;
	}

	@Override
	public int getFeedbackCount() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(*) from (select count(*) from feedback feedback0_  inner join matchlist matchlist1 on feedback0_.matchlistId=matchlist1.Id  group by"
				+ " feedback0_.matchlistId  ) countFeedback";
		Query query = session.createSQLQuery(sql);
                
        return Integer.parseInt(query.uniqueResult().toString());
	}
}
