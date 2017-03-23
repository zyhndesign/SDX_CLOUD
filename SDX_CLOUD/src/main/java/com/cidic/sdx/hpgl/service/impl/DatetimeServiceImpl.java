package com.cidic.sdx.hpgl.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cidic.sdx.hpgl.dao.DateTimeDao;
import com.cidic.sdx.hpgl.model.DateTimeModel;
import com.cidic.sdx.hpgl.service.DatetimeService;
import com.cidic.sdx.util.RedisVariableUtil;

@Service
@Component
@Qualifier(value = "dateTimeServiceImpl")
public class DatetimeServiceImpl implements DatetimeService {

	private String datetime_key = RedisVariableUtil.DATETIME_PREFIX + RedisVariableUtil.DIVISION_CHAR;
	
	@Autowired
	@Qualifier(value = "brandDaoImpl")
	private DateTimeDao dateTimeDaoImpl;
	
	@Override
	public List<DateTimeModel> getDateTimeData(String key) {
		String brandKey = datetime_key + key;
		Map<String,String> map = dateTimeDaoImpl.getDateTimeDataByKey(brandKey);
		List<DateTimeModel> list = new ArrayList<DateTimeModel>();
		map.forEach((k,v)->{
			DateTimeModel dateTimeModel = new DateTimeModel();
			dateTimeModel.setId(Integer.parseInt(k.split("\\"+RedisVariableUtil.DIVISION_CHAR)[1]));
			dateTimeModel.setName(v);
			dateTimeModel.setpId(Integer.parseInt(key));
			list.add(dateTimeModel);
			Collections.reverse(list);
		});
		return list;
	}

	@Override
	public Long insertDateTimeData(String key, String value) {
		return dateTimeDaoImpl.insertDateTimeData(key, value);
	}

	@Override
	public void updateDateTimeData(String parentKey, String key, String value) {
		dateTimeDaoImpl.updateDateTimeData(parentKey, key, value);
	}

	@Override
	public void deleteDateTimedData(String parentKey, String key) {
		dateTimeDaoImpl.deleteDateTimeData(parentKey, key);
	}

}
