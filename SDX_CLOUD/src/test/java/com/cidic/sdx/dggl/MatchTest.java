package com.cidic.sdx.dggl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.sdx.dggl.service.MatchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class MatchTest {

	@Autowired
	@Qualifier("matchServiceImpl")
	private MatchService matchServiceImpl;
	
	@Test
	public void createMatch(){
		
	}
	
	@Test
	public void updateMatch(){
		
	}
	
	@Test
	public void deleteMatch(){
		
	}
	
	@Test
	public void findMatch(){
		
	}
}
