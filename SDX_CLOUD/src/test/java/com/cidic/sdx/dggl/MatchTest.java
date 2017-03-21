package com.cidic.sdx.dggl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.sdx.dggl.model.Match;
import com.cidic.sdx.dggl.model.Matchlist;
import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.service.MatchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class MatchTest {

	@Autowired
	@Qualifier("matchServiceImpl")
	private MatchService matchServiceImpl;
	
	//@Test
	public void createMatch(){
		for (int i = 0; i < 201; i++){
			Match match = new Match();
			
			User user = new User();
			user.setId(6);
			match.setUser(user);
			
			match.setSeriesname("圣德西时尚系列" + i);
			match.setCreatetime(new Date());
			match.setDraftstatus((byte) 0);
			
			Set<Matchlist> matchlists = new HashSet<Matchlist>(4);
			Matchlist matchList1 = new Matchlist();
			matchList1.setMatch(match);
			matchList1.setCreatetime(new Date());
			matchList1.setInnerClothId(10);
			matchList1.setOutClothId(12);
			matchList1.setTrousersId(13);
			matchList1.setModelurl("http://sports.qq.com/kbsweb/game.htm?mid=100000:1469818");
			matchlists.add(matchList1);
			
			Matchlist matchList2 = new Matchlist();
			matchList2.setMatch(match);
			matchList2.setCreatetime(new Date());
			matchList2.setInnerClothId(10);
			matchList2.setOutClothId(12);
			matchList2.setTrousersId(13);
			matchList2.setModelurl("http://finance.sina.com.cn/money/bank/bank_hydt/2017-02-24/doc-ifyavvsh6192163.shtml");
			matchlists.add(matchList2);
			
			Matchlist matchList3 = new Matchlist();
			matchList3.setMatch(match);
			matchList3.setCreatetime(new Date());
			matchList3.setInnerClothId(10);
			matchList3.setOutClothId(12);
			matchList3.setTrousersId(13);
			matchList3.setModelurl("http://sports.qq.com/kbsweb/game.htm?mid=100000:1469818");
			matchlists.add(matchList3);
			
			Matchlist matchList4 = new Matchlist();
			matchList4.setMatch(match);
			matchList4.setCreatetime(new Date());
			matchList4.setInnerClothId(10);
			matchList4.setOutClothId(12);
			matchList4.setTrousersId(13);
			matchList4.setModelurl("http://finance.sina.com.cn/money/bank/bank_hydt/2017-02-24/doc-ifyavvsh6192163.shtml");
			matchlists.add(matchList4);
			
			match.setMatchlists(matchlists);
			
			matchServiceImpl.createMatch(match);
		}
		
	}
	
	//@Test
	public void updateMatch(){
		
		Match match = new Match();
		match.setId(3);
		User user = new User();
		user.setId(6);
		match.setUser(user);
		
		match.setSeriesname("圣德西欧洲风系列");
		match.setCreatetime(new Date());
		match.setDraftstatus((byte) 0);
		
		Set<Matchlist> matchlists = new HashSet<Matchlist>(4);
		Matchlist matchList1 = new Matchlist();
		matchList1.setMatch(match);
		matchList1.setCreatetime(new Date());
		matchList1.setInnerClothId(10);
		matchList1.setOutClothId(12);
		matchList1.setTrousersId(13);
		matchList1.setModelurl("http://sports.qq.com/kbsweb/game.htm?mid=100000:1469818");
		matchlists.add(matchList1);
		
		Matchlist matchList2 = new Matchlist();
		matchList2.setMatch(match);
		matchList2.setCreatetime(new Date());
		matchList2.setInnerClothId(10);
		matchList2.setOutClothId(12);
		matchList2.setTrousersId(13);
		matchList2.setModelurl("http://finance.sina.com.cn/money/bank/bank_hydt/2017-02-24/doc-ifyavvsh6192163.shtml");
		matchlists.add(matchList2);
		
		Matchlist matchList3 = new Matchlist();
		matchList3.setMatch(match);
		matchList3.setCreatetime(new Date());
		matchList3.setInnerClothId(10);
		matchList3.setOutClothId(12);
		matchList3.setTrousersId(13);
		matchList3.setModelurl("http://sports.qq.com/kbsweb/game.htm?mid=100000:1469818");
		matchlists.add(matchList3);
		
		Matchlist matchList4 = new Matchlist();
		matchList4.setMatch(match);
		matchList4.setCreatetime(new Date());
		matchList4.setInnerClothId(10);
		matchList4.setOutClothId(12);
		matchList4.setTrousersId(13);
		matchList4.setModelurl("http://finance.sina.com.cn/money/bank/bank_hydt/2017-02-24/doc-ifyavvsh6192163.shtml");
		matchlists.add(matchList4);
		
		match.setMatchlists(matchlists);
		
		matchServiceImpl.updateMatch(match);
	}
	
	//@Test
	public void deleteMatch(){
		matchServiceImpl.deleteMatch(2);
	}
	
	@Test
	public void findMatch(){
		List<Match> matchList = matchServiceImpl.findMatchByUser(6, 0, 10);
		for (Match match : matchList){
			System.out.println("***********************************************************");
			System.out.println(match.getSeriesname());
			
			Set<Matchlist> setList = match.getMatchlists();
			for (Matchlist mList : setList){
				System.out.println(mList.getModelurl());
			}
			System.out.println("***********************************************************");
			System.out.println();
			System.out.println();
		}
	}
}
