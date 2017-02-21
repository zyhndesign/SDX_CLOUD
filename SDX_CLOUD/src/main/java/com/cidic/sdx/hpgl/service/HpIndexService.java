package com.cidic.sdx.hpgl.service;

import java.util.List;

import com.cidic.sdx.hpgl.model.HPListModel;

public interface HpIndexService {

	public HPListModel getIndexDataByTag(List<String> tagList,int iDisplayStart,int iDisplayLength);
	
}
