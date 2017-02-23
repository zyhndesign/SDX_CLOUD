package com.cidic.sdx;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.service.AppUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class hibernateTest {

	@Autowired
	@Qualifier("appUserServiceImpl")
	private AppUserService appUserServiceImpl;
	
	@Test
	public void getData(){
		//User user = new User("jack", "111111", "http://icon.aliyun.com/asdasdasdasdasdasd", new Date());
		//appUserServiceImpl.createUser(user);
		
		Optional<User> user = appUserServiceImpl.findByUsername("jack bauer");
		//User userObject = 
		//System.out.println("=====2:"+userObject);
	}
	
}
