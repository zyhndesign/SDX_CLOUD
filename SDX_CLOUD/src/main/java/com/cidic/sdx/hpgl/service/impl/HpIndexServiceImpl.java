package com.cidic.sdx.hpgl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cidic.sdx.hpgl.dao.HpIndexDao;
import com.cidic.sdx.hpgl.model.HPListModel;
import com.cidic.sdx.hpgl.service.HpIndexService;

@Service
@Component
@Qualifier(value = "hpIndexServiceImpl")
public class HpIndexServiceImpl implements HpIndexService {

	@Autowired
	@Qualifier(value = "hpIndexDaoImpl")
	private HpIndexDao hpIndexDaoImpl;
	
	@Override
	public HPListModel getIndexDataByTag(List<String> tagList,int iDisplayStart,int iDisplayLength) {
		return hpIndexDaoImpl.getIndexDataByTag(tagList,iDisplayStart,iDisplayLength);
	}

	@Override
	public HPListModel getLostImageData(int iDisplayStart, int iDisplayLength) {
		// TODO Auto-generated method stub
		return hpIndexDaoImpl.getLostImageData(iDisplayStart, iDisplayLength);
	}

	@Override
	public HPListModel getLostURLData(int iDisplayStart, int iDisplayLength) {
		// TODO Auto-generated method stub
		return hpIndexDaoImpl.getLostURLData(iDisplayStart, iDisplayLength);
	}

	@Override
	public HPListModel getMatchListByCategoryType(int categoryType, int offset, int limit) {
		// TODO Auto-generated method stub
		return hpIndexDaoImpl.getMatchListByCategoryType(categoryType, offset, limit);
	}

}
