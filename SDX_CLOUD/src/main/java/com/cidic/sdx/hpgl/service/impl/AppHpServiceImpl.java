package com.cidic.sdx.hpgl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cidic.sdx.hpgl.dao.AppHpDao;
import com.cidic.sdx.hpgl.model.AppHpModel;
import com.cidic.sdx.hpgl.model.HPModel;
import com.cidic.sdx.hpgl.service.AppHpService;

@Service
@Component
@Qualifier(value = "appHpServiceImpl")
public class AppHpServiceImpl implements AppHpService {

	@Autowired
	@Qualifier(value = "appHpDaoImpl")
	private AppHpDao appHpDaoImpl;
	
	@Override
	public AppHpModel getHpData(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HPModel getHpDetailDataById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
