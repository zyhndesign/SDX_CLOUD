package com.cidic.sdx.hpgl.dao;

import java.util.List;

import com.cidic.sdx.hpgl.model.HPListModel;

public interface HpIndexDao {

	//根据标签进行数据查询
	public HPListModel getIndexDataByTag(List<String> tagList,int iDisplayStart,int iDisplayLength);
	
	//获取图片缺失的数据
	public HPListModel getLostImageData(int iDisplayStart,int iDisplayLength);
	
	//获取链接缺失的数据
	public HPListModel getLostURLData(int iDisplayStart,int iDisplayLength);
}
