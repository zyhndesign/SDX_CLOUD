package com.cidic.sdx.hpgl.dao;

import java.util.List;

import com.cidic.sdx.hpgl.model.AppHpModel;
import com.cidic.sdx.hpgl.model.HPModel;

public interface AppHpDao {

	public List<AppHpModel> getHpData(int offset, int limit);
	
	public AppHpModel getHpDetailDataById(int id);
}
