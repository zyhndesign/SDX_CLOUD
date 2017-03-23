package com.cidic.sdx.hpgl.service;

import java.util.List;

import com.cidic.sdx.hpgl.model.DateTimeModel;

public interface DatetimeService {
	
	public List<DateTimeModel> getDateTimeData(String key);
	
	public Long insertDateTimeData(String key, String value);
	
	public void updateDateTimeData(String parentKey,String key, String value);
	
	public void deleteDateTimedData(String parentKey,String key);
	
}
