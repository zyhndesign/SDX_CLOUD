package com.cidic.sdx.hpgl.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cidic.sdx.hpgl.dao.AppHpDao;
import com.cidic.sdx.hpgl.model.AppHpModel;
import com.cidic.sdx.hpgl.model.HPListModel;
import com.cidic.sdx.hpgl.model.HPModel;
import com.cidic.sdx.util.RedisVariableUtil;

@Service
@Component
@Qualifier(value = "appHpDaoImpl")
public class AppHpDaoImpl implements AppHpDao {

	@Autowired
	@Qualifier(value = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;

	@Resource(name = "redisTemplate")
	HashOperations<String, String, String> hashOperations;

	@Override
	public List<AppHpModel> getHpData(int offset, int limit) {

		return redisTemplate.execute(new RedisCallback<List<AppHpModel>>() {

			@Override
			public List<AppHpModel> doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				List<byte[]> id_list = connection.lRange(ser.serialize(RedisVariableUtil.DATA_INTEGRITY_LIST), offset,
						offset + limit - 1);
				return getListModel(connection, id_list);
			}
		});
	}

	@Override
	public List<AppHpModel> getInnerClothData(int offset, int limit) {
		return redisTemplate.execute(new RedisCallback<List<AppHpModel>>() {

			@Override
			public List<AppHpModel> doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				List<byte[]> id_list = connection.lRange(ser.serialize(RedisVariableUtil.LIST_INNER_CLOTH), offset,
						offset + limit - 1);
				return getListModel(connection, id_list);
			}
		});
	}

	@Override
	public List<AppHpModel> getOutterClothData(int offset, int limit) {
		return redisTemplate.execute(new RedisCallback<List<AppHpModel>>() {

			@Override
			public List<AppHpModel> doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				List<byte[]> id_list = connection.lRange(ser.serialize(RedisVariableUtil.LIST_OUTTER_CLOTH), offset,
						offset + limit - 1);
				return getListModel(connection, id_list);
			}
		});
	}

	@Override
	public List<AppHpModel> getTrouserClothData(int offset, int limit) {
		return redisTemplate.execute(new RedisCallback<List<AppHpModel>>() {

			@Override
			public List<AppHpModel> doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				List<byte[]> id_list = connection.lRange(ser.serialize(RedisVariableUtil.LIST_TROUSER_CLOTH), offset,
						offset + limit - 1);
				return getListModel(connection, id_list);
			}
		});
	}
	
	@Override
	public AppHpModel getHpDetailDataById(int id) {
		return redisTemplate.execute(new RedisCallback<AppHpModel>() {

			@Override
			public AppHpModel doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				Map<byte[], byte[]> map = connection.hGetAll(ser.serialize(
						RedisVariableUtil.HP_RECORD_PREFIX + RedisVariableUtil.DIVISION_CHAR + id));
				AppHpModel appHpModel = new AppHpModel();
				Map<String, String> resultMap = new HashMap<>();
				map.forEach((k, v) -> {
					resultMap.put(ser.deserialize(k), ser.deserialize(v));
				});
				appHpModel.setId(id);
				appHpModel.setHpNum(resultMap.get("hp_num"));

				appHpModel.setPrice(resultMap.get("price"));
				appHpModel.setImageUrl1(resultMap.get("imageUrl1"));
				appHpModel.setImageUrl2(resultMap.get("imageUrl2"));
				appHpModel.setImageUrl3(resultMap.get("imageUrl3"));
				appHpModel.setHpName(resultMap.get("hpName"));
				return appHpModel;
			}
		});
	}

	public List<AppHpModel> getListModel(RedisConnection connection, List<byte[]> id_list) {

		RedisSerializer<String> ser = redisTemplate.getStringSerializer();

		List<AppHpModel> hpModelList = new ArrayList<>();
		AppHpModel appHpModel = null;
		for (byte[] id : id_list) {
			Map<byte[], byte[]> map = connection.hGetAll(ser.serialize(
					RedisVariableUtil.HP_RECORD_PREFIX + RedisVariableUtil.DIVISION_CHAR + ser.deserialize(id)));
			appHpModel = new AppHpModel();
			Map<String, String> resultMap = new HashMap<>();
			map.forEach((k, v) -> {
				resultMap.put(ser.deserialize(k), ser.deserialize(v));
			});
			appHpModel.setId(Integer.parseInt(ser.deserialize(id)));
			appHpModel.setHpNum(resultMap.get("hp_num"));

			appHpModel.setPrice(resultMap.get("price"));
			appHpModel.setImageUrl1(resultMap.get("imageUrl1"));
			appHpModel.setImageUrl2(resultMap.get("imageUrl2"));
			appHpModel.setImageUrl3(resultMap.get("imageUrl3"));
			appHpModel.setHpName(resultMap.get("hpName"));
			hpModelList.add(appHpModel);
		}
		return hpModelList;
	}

}
