package com.cidic.sdx.hpgl.dao;

import com.cidic.sdx.hpgl.model.AppHpModel;
import com.cidic.sdx.hpgl.model.HPModel;

public interface AppHpDao {

	public AppHpModel getHpData(int offset, int limit);
	
	public HPModel getHpDetailDataById(int id);
}
