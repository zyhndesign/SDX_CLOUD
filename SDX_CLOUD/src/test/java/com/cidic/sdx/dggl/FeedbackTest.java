package com.cidic.sdx.dggl;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.model.Matchlist;
import com.cidic.sdx.dggl.service.FeedbackService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class FeedbackTest {

	@Autowired
	@Qualifier("feedbackServiceImpl")
	private FeedbackService feedbackServiceImpl;
	
	@Test
	public void createFeedback(){
		/*
		Feedback feedback = new Feedback();
		feedback.setLikeId(9);
		
		Matchlist matchList = new Matchlist();
		matchList.setId(807);
		feedback.setMatchlist(matchList);
		
		System.out.println(feedbackServiceImpl.createFeedback(feedback));
		
		*/
		for (int i = 0; i < 100000; i++){
			Random random1 = new Random();
			IntStream intStream1 = random1.ints(5, 817);
			
			Random random2 = new Random();
			IntStream intStream2 = random2.ints(2, 20);
			Feedback feedback = new Feedback();
			feedback.setLikeId(intStream2.limit(1).sum());
			
			Matchlist matchList = new Matchlist();
			matchList.setId(intStream1.limit(1).sum());
			feedback.setMatchlist(matchList);
			feedback.setCreatetime(new Date());
			feedbackServiceImpl.createFeedback(feedback);
		}
		
	}
	
	//@Test
	public void findFeedback(){
		int userId = 12;
		List<Feedback> list = feedbackServiceImpl.getFeedbackListPageByUserId(userId,10,0);
		System.out.println(list.size());
		for (Feedback feedback : list){
			Matchlist matchList = feedback.getMatchlist();
			System.out.println(matchList.getModelurl());
		}
	}
	
	//@Test
	public void updateFeedback(){
		
		Feedback feedback = new Feedback();
		feedback.setId(21);
		feedback.setLikeId(9);
		
		Matchlist matchList = new Matchlist();
		matchList.setId(807);
		feedback.setMatchlist(matchList);
		
		System.out.println(feedbackServiceImpl.updateFeedback(feedback));
	}
}
