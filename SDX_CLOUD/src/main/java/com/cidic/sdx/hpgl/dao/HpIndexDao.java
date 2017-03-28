package com.cidic.sdx.hpgl.dao;

import java.util.List;
import java.util.Map;

import com.cidic.sdx.dggl.model.CostumeModel;
import com.cidic.sdx.hpgl.model.HPListModel;

public interface HpIndexDao {

	//根据标签进行数据查询
	public HPListModel getIndexDataByTag(Map<String,List<String>> mapTagList,int iDisplayStart,int iDisplayLength);
	
	//获取图片缺失的数据
	public HPListModel getLostImageData(int iDisplayStart,int iDisplayLength);
	
	//获取链接缺失的数据
	public HPListModel getLostURLData(int iDisplayStart,int iDisplayLength);
	
	//获取 裤装（1），外套（2），内搭 （3）列表数据
	public HPListModel getMatchListByCategoryType(int categoryType, int offset, int limit);
	
	public CostumeModel getClothUrl(int id);
}
