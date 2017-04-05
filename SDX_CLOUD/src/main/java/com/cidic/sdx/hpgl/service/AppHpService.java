package com.cidic.sdx.hpgl.service;

import java.util.List;

import com.cidic.sdx.hpgl.model.AppHpModel;

public interface AppHpService {

	public List<AppHpModel> getHpData(int offset, int limit);
	
	public AppHpModel getHpDetailDataById(int id);
}
