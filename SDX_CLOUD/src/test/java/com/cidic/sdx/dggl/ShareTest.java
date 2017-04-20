package com.cidic.sdx.dggl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.sdx.dggl.model.Match;
import com.cidic.sdx.dggl.model.Share;
import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.service.ShareService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ShareTest {

	@Autowired
	@Qualifier("shareServiceImpl")
	private ShareService shareServiceImpl;
	
	@Test
	public void createShare(){
		Match match = new Match();
		match.setId(212);
		Share share = new Share();
		share.setMatch(match);
		share.setShareContent("getSession()方法从当前事务或者一个新的事务中获得session,如果想从一个新的事务中获得session");
		User user = new User();
		user.setId(121);
		share.setUser(user);
		share.setSharedlist("jack,jhon,bill,mark,page,湖南,长沙");
		shareServiceImpl.createShare(share);
	}
}
