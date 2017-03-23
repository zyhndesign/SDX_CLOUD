package com.cidic.sdx.dggl.service;

import com.cidic.sdx.dggl.model.Shop;
import com.cidic.sdx.dggl.model.ShopListModel;

public interface ShopService {
	
	public int createShop(Shop shop);
	
	public int updateShop(Shop shop);
	
	public int deleteShop(Shop shop);
	
	public ShopListModel getShopList(int iDisplayStart,int iDisplayLength);
}
