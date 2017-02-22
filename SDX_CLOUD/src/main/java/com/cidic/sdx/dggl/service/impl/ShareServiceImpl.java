package com.cidic.sdx.dggl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.ShareDao;
import com.cidic.sdx.dggl.model.Share;
import com.cidic.sdx.dggl.service.ShareService;

@Repository
@Transactional
@Component
@Qualifier(value = "shareServiceImpl")
public class ShareServiceImpl implements ShareService {

	@Autowired
	@Qualifier("shareDaoImpl")
	private ShareDao shareDaoImpl;
	
	@Override
	public void createShare(Share share) {
		
	}

}
