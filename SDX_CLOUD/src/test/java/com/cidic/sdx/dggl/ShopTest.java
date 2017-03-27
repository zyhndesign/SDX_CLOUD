package com.cidic.sdx.dggl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.sdx.dggl.model.Shop;
import com.cidic.sdx.dggl.service.ShopService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ShopTest {

	@Autowired
	@Qualifier("shopServiceImpl")
	private ShopService shopServiceImpl;
	
	//@Test
	public void testLoadShop(){
		Shop shop = shopServiceImpl.loadShopById(1);
		System.out.println(shop.getShopname());
	}
	
	//@Test
	public void deleteShop(){
		Shop shop = new Shop();
		shop.setId(3);
		shopServiceImpl.deleteShop(shop);
	}
	
	@Test
	public void updateShop(){
		Shop shop = new Shop();
		shop.setId(3);
		shop.setShopname("河西店");
		shop.setCreatetime(new Date());
		shopServiceImpl.updateShop(shop);
	}
}
