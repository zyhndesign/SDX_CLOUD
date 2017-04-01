package com.cidic.sdx.hpgl.dao.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cidic.sdx.hpgl.dao.AppHpDao;
import com.cidic.sdx.hpgl.model.AppHpModel;
import com.cidic.sdx.hpgl.model.HPModel;

@Service
@Component
@Qualifier(value = "appHpDaoImpl")
public class AppHpDaoImpl implements AppHpDao {

	@Autowired
	@Qualifier(value = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;

	@Resource(name="redisTemplate")
    HashOperations<String, String, String> hashOperations;
	
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
