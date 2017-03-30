package com.cidic.sdx.hpgl.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.dggl.model.CostumeModel;
import com.cidic.sdx.hpgl.dao.HpIndexDao;
import com.cidic.sdx.hpgl.model.HPListModel;
import com.cidic.sdx.hpgl.model.HPModel;
import com.cidic.sdx.util.RedisVariableUtil;

@Repository
@Component
@Qualifier(value = "hpIndexDaoImpl")
public class HpIndexDaoImpl implements HpIndexDao {

	@Autowired
	@Qualifier(value = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource(name="redisTemplate")
    HashOperations<String, String, String> hashOperations;
	
	private String cacheKey;
	
	String id_key = "HpIDList";
	
	@Override
	public HPListModel getIndexDataByTag(Map<String,List<String>> mapTagList,int iDisplayStart,int iDisplayLength) {
		
		StringBuilder tagListStr = new StringBuilder();
		mapTagList.forEach((k,v)->{
			v.forEach((s)->tagListStr.append(s));
		});
		cacheKey = DigestUtils.md5Hex(tagListStr.toString());
		
		return redisTemplate.execute(new RedisCallback<HPListModel>() {
			
			@Override
			public  HPListModel doInRedis(RedisConnection connection) throws DataAccessException {
				
				HPListModel hpListModel = new HPListModel();
				
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				List<byte[]> id_list = null;
				
				if (mapTagList.size() == 0){
					id_list = connection.lRange(ser.serialize(id_key), iDisplayStart, iDisplayStart + iDisplayLength - 1);
				}
				else{
					if (!connection.exists(ser.serialize(cacheKey))){
						
						List<String> brandList = mapTagList.get(RedisVariableUtil.BRAND_PREFIX);
						List<String> colorList = mapTagList.get(RedisVariableUtil.COLOR_PREFIX);
						List<String> categoryList = mapTagList.get(RedisVariableUtil.CATEGORY_PREFIX);
						List<String> sizeList = mapTagList.get(RedisVariableUtil.SIZE_PREFIX);
						
						List<String> calculateKeys = new ArrayList<>();
						String calculateBrandResult = RedisVariableUtil.BRAND_PREFIX + RedisVariableUtil.DIVISION_CHAR + cacheKey;
						if (brandList != null){
							 redisTemplate.opsForSet().unionAndStore(brandList.get(0), brandList, calculateBrandResult);
							 calculateKeys.add(calculateBrandResult);
						}
						
						String calculateColorResult = RedisVariableUtil.COLOR_PREFIX + RedisVariableUtil.DIVISION_CHAR + cacheKey;
						if (colorList != null){
							 redisTemplate.opsForSet().unionAndStore(colorList.get(0), colorList, calculateColorResult);
							 calculateKeys.add(calculateColorResult);
						}
						
						String calculateCategoryResult = RedisVariableUtil.CATEGORY_PREFIX + RedisVariableUtil.DIVISION_CHAR + cacheKey;
						if (categoryList != null){
							 redisTemplate.opsForSet().unionAndStore(categoryList.get(0), categoryList, calculateCategoryResult);
							 calculateKeys.add(calculateCategoryResult);
						}
						
						String calculateSizeResult = RedisVariableUtil.SIZE_PREFIX + RedisVariableUtil.DIVISION_CHAR + cacheKey;
						if (sizeList != null){
							 redisTemplate.opsForSet().unionAndStore(sizeList.get(0), sizeList, calculateSizeResult);
							 calculateKeys.add(calculateSizeResult);
						}
						
						Set<String> result = redisTemplate.opsForSet().intersect(calculateKeys.get(0), calculateKeys);
						
						for (String data : result){
							connection.lPush(ser.serialize(cacheKey), ser.serialize(data));
						}
						
						connection.expire(ser.serialize(cacheKey), 300);
						
						calculateKeys.forEach((s)->{
							redisTemplate.delete(s);
						});
						
					}
					id_list = connection.lRange(ser.serialize(cacheKey), iDisplayStart, iDisplayStart + iDisplayLength - 1);
				}

				hpListModel.setCount(connection.lLen(ser.serialize(cacheKey)));
				
				List<HPModel> hpModelList = getListModel(connection,id_list);
				
				hpListModel.setList(hpModelList);
			
				return hpListModel;
			}
		});
	}

	@Override
	public HPListModel getLostImageData(int iDisplayStart, int iDisplayLength) {
		
		return redisTemplate.execute(new RedisCallback<HPListModel>() {
			
			@Override
			public  HPListModel doInRedis(RedisConnection connection) throws DataAccessException {

				HPListModel hpListModel = new HPListModel();
				
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				List<byte[]> id_list = connection.lRange(ser.serialize(RedisVariableUtil.LOST_IMAGE_LIST), iDisplayStart, iDisplayStart + iDisplayLength - 1);
				hpListModel.setCount(connection.lLen(ser.serialize(RedisVariableUtil.LOST_IMAGE_LIST)));
				List<HPModel> hpModelList = getListModel(connection,id_list);
				
				hpListModel.setList(hpModelList);
			
				return hpListModel;
			}
		});
	}

	@Override
	public HPListModel getLostURLData(int iDisplayStart, int iDisplayLength) {
		return redisTemplate.execute(new RedisCallback<HPListModel>() {
			
			@Override
			public  HPListModel doInRedis(RedisConnection connection) throws DataAccessException {

				HPListModel hpListModel = new HPListModel();
				
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				List<byte[]> id_list = connection.lRange(ser.serialize(RedisVariableUtil.LOST_URL_LIST), iDisplayStart, iDisplayStart + iDisplayLength - 1);
				hpListModel.setCount(connection.lLen(ser.serialize(RedisVariableUtil.LOST_URL_LIST)));
				List<HPModel> hpModelList = getListModel(connection,id_list);
				hpListModel.setList(hpModelList);
			
				return hpListModel;
			}
		});
	}
	
	@Override
	public HPListModel getAllLostData(int iDisplayStart, int iDisplayLength) {
		return redisTemplate.execute(new RedisCallback<HPListModel>() {
			
			@Override
			public  HPListModel doInRedis(RedisConnection connection) throws DataAccessException {

				HPListModel hpListModel = new HPListModel();
				
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				List<byte[]> id_list = connection.lRange(ser.serialize(RedisVariableUtil.LOST_ALL_LIST), iDisplayStart, iDisplayStart + iDisplayLength - 1);
				hpListModel.setCount(connection.lLen(ser.serialize(RedisVariableUtil.LOST_ALL_LIST)));
				List<HPModel> hpModelList = getListModel(connection,id_list);
				
				hpListModel.setList(hpModelList);
			
				return hpListModel;
			}
		});
	}
	
	@Override
	public HPListModel getMatchListByCategoryType(int categoryType, int offset, int limit){

		return redisTemplate.execute(new RedisCallback<HPListModel>() {
			@Override
			public  HPListModel doInRedis(RedisConnection connection) throws DataAccessException {
				HPListModel hpListModel = new HPListModel();
				
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();

				List<byte[]> id_list = null;
				
				if (categoryType == 1){ //库装
					id_list = connection.lRange(ser.serialize(RedisVariableUtil.LIST_TROUSER_CLOTH), offset, offset + limit - 1);
					hpListModel.setCount(connection.lLen(ser.serialize(RedisVariableUtil.LIST_TROUSER_CLOTH)));
				}
				else if (categoryType == 2){ //外套
					id_list = connection.lRange(ser.serialize(RedisVariableUtil.LIST_OUTTER_CLOTH), offset, offset + limit - 1);
					hpListModel.setCount(connection.lLen(ser.serialize(RedisVariableUtil.LIST_TROUSER_CLOTH)));
				}
				else if (categoryType == 3){ //内搭
					id_list = connection.lRange(ser.serialize(RedisVariableUtil.LIST_INNER_CLOTH), offset, offset + limit - 1);
					hpListModel.setCount(connection.lLen(ser.serialize(RedisVariableUtil.LIST_TROUSER_CLOTH)));
				}
				
				List<HPModel> hpModelList = getListModel(connection,id_list);
				hpListModel.setList(hpModelList);
			
				return hpListModel;
				
			}
		});
	}

	public List<HPModel> getListModel(RedisConnection connection,List<byte[]> id_list){
		
		RedisSerializer<String> ser = redisTemplate.getStringSerializer();
		Map<byte[],byte[]> brandMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.BRAND_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
		Map<byte[],byte[]> categoryMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.CATEGORY_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
		Map<byte[],byte[]> colorMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.COLOR_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
		Map<byte[],byte[]> tempColorMap = new HashMap<>();
		Map<byte[],byte[]> timeCategoryMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.DATETIME_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
		
		tempColorMap.putAll(colorMapList);
		tempColorMap.forEach((k,v)->{
			Map<byte[],byte[]> subColorMap = connection.hGetAll(k);
			colorMapList.putAll(subColorMap);
		});
		
		Map<byte[],byte[]> sizeMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.SIZE_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
		
		//connection.sInter(id_list);
		
		List<HPModel> hpModelList = new ArrayList<>();
		HPModel hpModel = null;
		for (byte[] id : id_list){
			Map<byte[],byte[]> map = connection.hGetAll(ser.serialize(RedisVariableUtil.HP_RECORD_PREFIX + RedisVariableUtil.DIVISION_CHAR + ser.deserialize(id)));
			hpModel = new HPModel();
			Map<String, String> resultMap = new HashMap<>();
			map.forEach((k, v) -> {
				resultMap.put(ser.deserialize(k), ser.deserialize(v));
			});
			hpModel.setId(Integer.parseInt(ser.deserialize(id)));
			hpModel.setHp_num(resultMap.get("hp_num"));
			hpModel.setBrand(resultMap.get("brand"));
			hpModel.setCategory(resultMap.get("category"));
			hpModel.setSize(String.valueOf(resultMap.get("size").charAt(0)));
			hpModel.setColor(resultMap.get("color"));
			hpModel.setPrice(resultMap.get("price"));
			hpModel.setImageUrl1(resultMap.get("imageUrl1"));
			hpModel.setImageUrl2(resultMap.get("imageUrl2"));
			hpModel.setImageUrl3(resultMap.get("imageUrl3"));
			hpModel.setCreateTime(resultMap.get("createTime"));
			hpModel.setTimeCategory(resultMap.get("timeCategory"));
			
			StringBuilder brandList = new StringBuilder();
			StringBuilder categoryList  = new StringBuilder();
			StringBuilder sizeList = new StringBuilder();
			StringBuilder colorList = new StringBuilder();
			StringBuilder timeCategoryList = new StringBuilder();
			
			String[] brandArray = resultMap.get("brand").split("\\,");
			int brandCount = 0;
			for (String brand : brandArray){
				
				String tempKey = RedisVariableUtil.BRAND_PREFIX + RedisVariableUtil.DIVISION_CHAR +brand;
				
				brandList.append(ser.deserialize(brandMapList.get(ser.serialize(tempKey))));
				++brandCount;
				if (brandCount != brandArray.length){
					brandList.append("/");
				}
				Map<byte[],byte[]> tempmap = connection.hGetAll(ser.serialize(tempKey));
				tempmap.forEach((k,v)->{
					brandMapList.put(k, v);
				});
			}
			
			int categoryCount = 0;
			String[] categoryArray = resultMap.get("category").split("\\,");
			for (String category : categoryArray){
				String tempKey = RedisVariableUtil.CATEGORY_PREFIX + RedisVariableUtil.DIVISION_CHAR +category;
				Map<byte[],byte[]> tempmap = connection.hGetAll(ser.serialize(tempKey));
				tempmap.forEach((k,v)->{
					categoryMapList.put(k, v);
				});
			}
			
			for (String category : categoryArray){
				String tempKey = RedisVariableUtil.CATEGORY_PREFIX + RedisVariableUtil.DIVISION_CHAR +category;
				categoryList.append(ser.deserialize(categoryMapList.get(ser.serialize(tempKey))));
				++categoryCount;
				if (categoryCount != categoryArray.length){
					categoryList.append("/");
				}
				
			}
			
			int sizeCount = 0;
			String[] sizeArray = resultMap.get("size").split("\\,");
			for (String size : sizeArray){
				String tempKey = RedisVariableUtil.SIZE_PREFIX + RedisVariableUtil.DIVISION_CHAR +size;
				sizeList.append(ser.deserialize(sizeMapList.get(ser.serialize(tempKey))).charAt(0));
				++sizeCount;
				if (sizeCount != sizeArray.length){
					sizeList.append("/");
				}
				Map<byte[],byte[]> tempmap = connection.hGetAll(ser.serialize(tempKey));
				tempmap.forEach((k,v)->{
					sizeMapList.put(k, v);
				});
			}
			
			int colorCount = 0;
			
			String[] colorArray = resultMap.get("color").split("\\,");
			for (String color : colorArray){
				String tempKey = RedisVariableUtil.COLOR_PREFIX + RedisVariableUtil.DIVISION_CHAR +color;
				colorList.append(ser.deserialize(colorMapList.get(ser.serialize(tempKey))));
				++colorCount;
				if (colorCount != colorArray.length){
					colorList.append("/");
				}
			}
			
			int timeCategoryCount = 0;
			String[] timeCategoryArray = resultMap.get("timeCategory").split("\\,");
			for (String timeCategory : timeCategoryArray){
				String tempKey = RedisVariableUtil.DATETIME_PREFIX + RedisVariableUtil.DIVISION_CHAR + timeCategory;
				Map<byte[],byte[]> tempmap = connection.hGetAll(ser.serialize(tempKey));
				tempmap.forEach((k,v)->{
					timeCategoryMapList.put(k, v);
				});
			}
			
			for (String timeCategory : timeCategoryArray){
				String tempKey = RedisVariableUtil.DATETIME_PREFIX + RedisVariableUtil.DIVISION_CHAR + timeCategory;
				timeCategoryList.append(ser.deserialize(timeCategoryMapList.get(ser.serialize(tempKey))));
				++timeCategoryCount;
				if (timeCategoryCount != timeCategoryArray.length){
					timeCategoryList.append("/");
				}
			}
			
			hpModel.setBrandList(brandList.toString());
			hpModel.setCategoryList(categoryList.toString());
			hpModel.setColorList(colorList.toString());
			hpModel.setSizeList(sizeList.toString());
			hpModel.setTimeCategoryList(timeCategoryList.toString());
			
			hpModelList.add(hpModel);
		}
		return hpModelList;
	}
	
	@Override
	public CostumeModel getClothUrl(int id){
		Map<Object, Object> map = hashOperations.getOperations().boundHashOps(RedisVariableUtil.HP_RECORD_PREFIX + RedisVariableUtil.DIVISION_CHAR + id).entries();
		CostumeModel costumeModel = new CostumeModel();
		costumeModel.setProductImageUrl(map.get("imageUrl1").toString());
		costumeModel.setFrontViewUrl(map.get("imageUrl2").toString());
		costumeModel.setBackViewUrl(map.get("imageUrl3").toString());
		return costumeModel;
	}

	
}
