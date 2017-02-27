package com.cidic.sdx.hpgl.service;

import java.util.List;

import com.cidic.sdx.hpgl.model.HPListModel;

public interface HpIndexService {

	public HPListModel getIndexDataByTag(List<String> tagList, int iDisplayStart, int iDisplayLength);

	// 获取图片缺失的数据
	public HPListModel getLostImageData(int iDisplayStart, int iDisplayLength);

	// 获取链接缺失的数据
	public HPListModel getLostURLData(int iDisplayStart, int iDisplayLength);
}
