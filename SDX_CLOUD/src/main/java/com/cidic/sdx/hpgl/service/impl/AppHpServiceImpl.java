package com.cidic.sdx.hpgl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cidic.sdx.hpgl.dao.AppHpDao;
import com.cidic.sdx.hpgl.model.AppHpModel;
import com.cidic.sdx.hpgl.service.AppHpService;

@Service
@Component
@Qualifier(value = "appHpServiceImpl")
public class AppHpServiceImpl implements AppHpService {

	@Autowired
	@Qualifier(value = "appHpDaoImpl")
	private AppHpDao appHpDaoImpl;
	
	@Override
	public List<AppHpModel> getHpData(int offset, int limit) {
		return appHpDaoImpl.getHpData(offset, limit);
	}

	@Override
	public AppHpModel getHpDetailDataById(int id) {
		return appHpDaoImpl.getHpDetailDataById(id);
	}

	@Override
	public List<AppHpModel> getInnerClothData(int offset, int limit) {
		// TODO Auto-generated method stub
		return appHpDaoImpl.getInnerClothData(offset, limit);
	}

	@Override
	public List<AppHpModel> getOutterClothData(int offset, int limit) {
		// TODO Auto-generated method stub
		return appHpDaoImpl.getOutterClothData(offset, limit);
	}

	@Override
	public List<AppHpModel> getTrouserClothData(int offset, int limit) {
		// TODO Auto-generated method stub
		return appHpDaoImpl.getTrouserClothData(offset, limit);
	}

	
}
