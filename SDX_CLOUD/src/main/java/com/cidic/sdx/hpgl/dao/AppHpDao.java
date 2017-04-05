package com.cidic.sdx.hpgl.dao;

import java.util.List;

import com.cidic.sdx.hpgl.model.AppHpModel;
import com.cidic.sdx.hpgl.model.HPModel;

public interface AppHpDao {

	public List<AppHpModel> getHpData(int offset, int limit);
	
	public List<AppHpModel> getInnerClothData(int offset, int limit);
	
	public List<AppHpModel> getOutterClothData(int offset, int limit);
	
	public List<AppHpModel> getTrouserClothData(int offset, int limit);
	
	public AppHpModel getHpDetailDataById(int id);
}
