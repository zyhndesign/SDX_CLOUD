package com.cidic.sdx.dggl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.ShareDao;
import com.cidic.sdx.dggl.model.Share;
import com.cidic.sdx.dggl.service.ShareService;
import com.cidic.sdx.util.ResponseCodeUtil;

@Repository
@Transactional
@Component
@Qualifier(value = "shareServiceImpl")
public class ShareServiceImpl implements ShareService {

	@Autowired
	@Qualifier("shareDaoImpl")
	private ShareDao shareDaoImpl;
	
	@Override
	public int createShare(Share share) {
		try{
			shareDaoImpl.createShare(share);
			return ResponseCodeUtil.SHARE_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.SHARE_OPERATION_FAILURE;
		}
	}

	@Override
	public List<Share> getShareList(int matchId, int userId) {
		// TODO Auto-generated method stub
		return shareDaoImpl.getShareList(matchId, userId);
	}

	@Override
	public Share getShareByCode(String code) {
		// TODO Auto-generated method stub
		return shareDaoImpl.getShareByCode(code);
	}

}
