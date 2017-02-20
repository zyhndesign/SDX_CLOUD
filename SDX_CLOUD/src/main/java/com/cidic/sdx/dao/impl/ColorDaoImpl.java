package com.cidic.sdx.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.dao.ColorDao;
import com.cidic.sdx.util.RedisVariableUtil;

@Repository
@Component
@Qualifier(value = "colorDaoImpl")
public class ColorDaoImpl implements ColorDao {

	@Autowired
	@Qualifier(value = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource(name="redisTemplate")
    HashOperations<String, String, String> hashOperations;
	
	@Override
	public Map<String, String> getColorDataByKey(String key) {
		Map<Object, Object> map = hashOperations.getOperations().boundHashOps(key).entries();
		Map<String, String> resultMap = new HashMap<>();
		map.forEach((k, v) -> {
			resultMap.put(k.toString(), v.toString());
		});
		return resultMap;
	}

	@Override
	public long insertColorData(String key, String value) {
		String id_key = RedisVariableUtil.COLOR_PREFIX + "Id";
		long id = redisTemplate.opsForValue().increment(id_key, 1);
		hashOperations.put(RedisVariableUtil.COLOR_PREFIX + RedisVariableUtil.DIVISION_CHAR + key,
				RedisVariableUtil.COLOR_PREFIX + RedisVariableUtil.DIVISION_CHAR + id, value);
		return id;
	}

	@Override
	public void updateColorData(String parentKey, String key, String value) {
		hashOperations.put(RedisVariableUtil.COLOR_PREFIX + RedisVariableUtil.DIVISION_CHAR + parentKey,
				RedisVariableUtil.COLOR_PREFIX + RedisVariableUtil.DIVISION_CHAR + key, value);
	}

	@Override
	public void deleteColorData(String parentKey, String key) {
		hashOperations.delete(RedisVariableUtil.COLOR_PREFIX + RedisVariableUtil.DIVISION_CHAR + parentKey,
				RedisVariableUtil.COLOR_PREFIX + RedisVariableUtil.DIVISION_CHAR + key);
	}

}
