package com.cidic.sdx.hpgl.dao;

import java.util.Map;

public interface DateTimeDao {

	public Map<String,String> getDateTimeDataByKey(String key);
	
	public long insertDateTimeData(String key, String value);
	
	public void updateDateTimeData(String parentKey,String key, String value);
	
	public void deleteDateTimeData(String parentKey,String key);
}
