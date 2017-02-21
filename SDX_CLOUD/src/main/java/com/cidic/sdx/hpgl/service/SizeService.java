package com.cidic.sdx.hpgl.service;

import java.util.List;

import com.cidic.sdx.hpgl.model.SizeModel;

public interface SizeService {
	
	public List<SizeModel> getSizeData(String key);
	
	public Long insertSizeData(String key, String value);
	
	public void updateSizeData(String parentKey,String key, String value);
	
	public void deleteSizeData(String parentKey,String key);
}
