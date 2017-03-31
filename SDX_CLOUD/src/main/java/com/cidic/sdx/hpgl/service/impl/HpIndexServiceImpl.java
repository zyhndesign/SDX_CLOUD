package com.cidic.sdx.hpgl.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cidic.sdx.hpgl.dao.HpIndexDao;
import com.cidic.sdx.hpgl.model.HPListModel;
import com.cidic.sdx.hpgl.service.HpIndexService;
import com.cidic.sdx.util.RedisVariableUtil;

@Service
@Component
@Qualifier(value = "hpIndexServiceImpl")
public class HpIndexServiceImpl implements HpIndexService {

	@Autowired
	@Qualifier(value = "hpIndexDaoImpl")
	private HpIndexDao hpIndexDaoImpl;

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

	@Override
	public HPListModel getIndexDataByTag(String brand, String color, String category, String size, int iDisplayStart,
			int iDisplayLength) {
		Map<String,List<String>> mapTagList = new HashMap<String,List<String>>();
		
		if (brand != null && !brand.equals("")) {
			List<String> brandList = new ArrayList<>();
			String[] brandArray = brand.split("\\,");
			String prefix = RedisVariableUtil.BRAND_TAG_PREFIX + RedisVariableUtil.DIVISION_CHAR;
			Arrays.asList(brandArray).stream().forEach((b) -> {
				brandList.add(prefix + b);
			});
			mapTagList.put(RedisVariableUtil.BRAND_PREFIX, brandList);
		}

		if (color != null && !color.equals("")) {
			List<String> colorList = new ArrayList<>();
			String[] colorArray = color.split("\\,");
			String prefix = RedisVariableUtil.COLOR_TAG_PREFIX + RedisVariableUtil.DIVISION_CHAR;
			Arrays.asList(colorArray).stream().forEach((b) -> {
				colorList.add(prefix + b);
			});
			mapTagList.put(RedisVariableUtil.COLOR_PREFIX, colorList);
		}

		if (size != null && !size.equals("")) {
			List<String> sizeList = new ArrayList<>();
			String[] sizeArray = size.split("\\,");
			String prefix = RedisVariableUtil.SIZE_TAG_PREFIX + RedisVariableUtil.DIVISION_CHAR;
			Arrays.asList(sizeArray).stream().forEach((b) -> {
				sizeList.add(prefix + b);
			});
			mapTagList.put(RedisVariableUtil.SIZE_PREFIX, sizeList);
		}

		if (category != null && !category.equals("")) {
			List<String> categoryList = new ArrayList<>();
			String[] categoryArray = category.split("\\,");
			String prefix = RedisVariableUtil.CATEGORY_TAG_PREFIX + RedisVariableUtil.DIVISION_CHAR;
			Arrays.asList(categoryArray).stream().forEach((b) -> {
				categoryList.add(prefix + b);
			});
			mapTagList.put(RedisVariableUtil.CATEGORY_PREFIX, categoryList);
		}
		return hpIndexDaoImpl.getIndexDataByTag(mapTagList,iDisplayStart,iDisplayLength);
	}

	@Override
	public HPListModel getAllLostData(int iDisplayStart, int iDisplayLength) {
		return hpIndexDaoImpl.getAllLostData(iDisplayStart, iDisplayLength);
	}

	@Override
	public HPListModel getAllData(int iDisplayStart, int iDisplayLength) {
		return hpIndexDaoImpl.getIndexDataByTag(null,iDisplayStart,iDisplayLength);
	}

	@Override
	public HPListModel getAllIntegrityData(int iDisplayStart, int iDisplayLength) {
		return hpIndexDaoImpl.getAllIntegrityData(iDisplayStart, iDisplayLength);
	}

}
