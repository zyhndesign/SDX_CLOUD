package com.cidic.sdx.hpgl.service;

import com.cidic.sdx.hpgl.model.AppHpModel;
import com.cidic.sdx.hpgl.model.HPModel;

public interface AppHpService {

	public AppHpModel getHpData(int offset, int limit);
	
	public HPModel getHpDetailDataById(int id);
}
