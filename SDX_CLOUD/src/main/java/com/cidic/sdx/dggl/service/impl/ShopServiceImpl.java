package com.cidic.sdx.dggl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.ShopDao;
import com.cidic.sdx.dggl.model.Shop;
import com.cidic.sdx.dggl.model.ShopListModel;
import com.cidic.sdx.dggl.service.ShopService;
import com.cidic.sdx.util.ResponseCodeUtil;

@Repository
@Transactional
@Component
@Qualifier(value = "shopServiceImpl")
public class ShopServiceImpl implements ShopService {

	@Autowired
	@Qualifier("shopDaoImpl")
	private ShopDao shopDaoImpl;
	
	@Override
	public int createShop(Shop shop) {
		try{
			shopDaoImpl.createShop(shop);
			return ResponseCodeUtil.SHOP_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.SHOP_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateShop(Shop shop) {
		try{
			shopDaoImpl.updateShop(shop);
			return ResponseCodeUtil.SHOP_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.SHOP_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteShop(Shop shop) {
		try{
			shopDaoImpl.deleteShop(shop);
			return ResponseCodeUtil.SHOP_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.SHOP_OPERATION_FAILURE;
		}
	}

	@Override
	public ShopListModel getShopList(int iDisplayStart, int iDisplayLength) {
		List<Shop> list = shopDaoImpl.getShopList(iDisplayStart, iDisplayLength);
		Long count = shopDaoImpl.getCountShop();
		ShopListModel shopListModel = new ShopListModel();
		shopListModel.setList(list);
		shopListModel.setCount(count);
		return shopListModel;
	}

	@Override
	public Shop loadShopById(int id) {
		return shopDaoImpl.loadShopById(id);
	}

}
