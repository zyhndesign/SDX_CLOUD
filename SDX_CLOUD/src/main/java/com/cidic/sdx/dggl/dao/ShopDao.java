package com.cidic.sdx.dggl.dao;

import java.util.List;

import com.cidic.sdx.dggl.model.Shop;

public interface ShopDao {

	public void createShop(Shop shop);
	
	public void updateShop(Shop shop);
	
	public void deleteShop(Shop shop);
	
	public List<Shop> getShopList(int iDisplayStart,int iDisplayLength);
	
	public Long getCountShop();
}
