package com.cidic.sdx.hpgl.service;

import java.util.List;

import com.cidic.sdx.hpgl.model.HPListModel;
import com.cidic.sdx.hpgl.model.HPModel;

public interface HpIndexService {

	public HPListModel getIndexDataByTag(String brand, String color, String category, String size, int iDisplayStart, int iDisplayLength);

	public List<HPModel> getAppIndexDataByTag(String brand, String category);
	
	// 获取图片缺失的数据
	public HPListModel getLostImageData(int iDisplayStart, int iDisplayLength);

	// 获取链接缺失的数据
	public HPListModel getLostURLData(int iDisplayStart, int iDisplayLength);
	
	// 图片和链接数据都缺失的数据
	public HPListModel getAllLostData(int iDisplayStart, int iDisplayLength);
	
	// 图片和链接数据都缺失的数据
	public HPListModel getAllIntegrityData(int iDisplayStart, int iDisplayLength);
		
	//获取 裤装（1），外套（2），内搭 （3）列表数据
	public HPListModel getMatchListByCategoryType(int categoryType, int offset, int limit);
	
	// 图片和链接数据都缺失的数据
	public HPListModel getAllData(int iDisplayStart, int iDisplayLength);
	
	
}
