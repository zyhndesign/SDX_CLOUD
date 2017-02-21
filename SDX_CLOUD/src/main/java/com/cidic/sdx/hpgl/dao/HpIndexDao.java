package com.cidic.sdx.hpgl.dao;

import java.util.List;

import com.cidic.sdx.hpgl.model.HPListModel;

public interface HpIndexDao {

	public HPListModel getIndexDataByTag(List<String> tagList,int iDisplayStart,int iDisplayLength);
	
}
