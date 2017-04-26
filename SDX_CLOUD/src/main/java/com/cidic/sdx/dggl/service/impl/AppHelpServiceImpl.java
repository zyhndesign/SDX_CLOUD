package com.cidic.sdx.dggl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.AppHelpDao;
import com.cidic.sdx.dggl.model.Apphelp;
import com.cidic.sdx.dggl.service.AppHelpService;
import com.cidic.sdx.util.ResponseCodeUtil;

@Repository
@Component
@Qualifier(value = "appHelpServiceImpl")
@Transactional
public class AppHelpServiceImpl implements AppHelpService {

	@Autowired
	@Qualifier("appHelpDaoImpl")
	private AppHelpDao appHelpDaoImpl;
	
	@Override
	public int createHelpInfo(Apphelp appHelp) {
		try {
			appHelpDaoImpl.createHelpInfo(appHelp);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {

			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}
}
