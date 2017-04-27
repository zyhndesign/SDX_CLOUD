package com.cidic.sdx.hpgl.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.hpgl.dao.HpManageDao;
import com.cidic.sdx.hpgl.model.HPListModel;
import com.cidic.sdx.hpgl.model.HPModel;
import com.cidic.sdx.util.RedisVariableUtil;

@Repository
@Component
@Qualifier(value = "hpManageDaoImpl")
public class HpManageDaoImpl implements HpManageDao {
	
	private static final Logger logger = LoggerFactory.getLogger(HpManageDaoImpl.class);
	
	@Autowired
	@Qualifier(value = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public void insertHpData(HPModel hpModel) {
		String id_key = RedisVariableUtil.HP_RECORD_PREFIX + "Id";

	    redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {

				RedisSerializer<String> ser = redisTemplate.getStringSerializer();

				byte[] bIdKey = ser.serialize(id_key);
				long id = connection.incr(bIdKey);
				
				connection.multi();
				connection.lPush(ser.serialize("HpIDList"), ser.serialize(String.valueOf(id)));
				//将记录Id存入哈希�?
				byte[] hKey = ser.serialize(RedisVariableUtil.HP_RECORD_PREFIX + RedisVariableUtil.DIVISION_CHAR + id);
				
				connection.hSet(hKey,ser.serialize("hp_num"), ser.serialize(hpModel.getHp_num()));
				connection.hSet(hKey,ser.serialize("brand"), ser.serialize(hpModel.getBrand()));
				connection.hSet(hKey,ser.serialize("category"), ser.serialize(hpModel.getCategory()));
				connection.hSet(hKey,ser.serialize("size"), ser.serialize(hpModel.getSize()));
				connection.hSet(hKey,ser.serialize("color"), ser.serialize(hpModel.getColor()));
				connection.hSet(hKey,ser.serialize("price"), ser.serialize(hpModel.getPrice()));
				
				connection.hSet(hKey,ser.serialize("state"), ser.serialize(hpModel.getState()));
				connection.hSet(hKey,ser.serialize("hpName"), ser.serialize(hpModel.getHpName()));
				connection.hSet(hKey,ser.serialize("createTime"), ser.serialize(hpModel.getCreateTime()));
				connection.hSet(hKey,ser.serialize("remark"), ser.serialize(hpModel.getRemark()));
				connection.hSet(hKey,ser.serialize("unit"), ser.serialize(hpModel.getUnit()));
				connection.hSet(hKey,ser.serialize("isPanDian"), ser.serialize(hpModel.getIsPanDian()));
				connection.hSet(hKey,ser.serialize("kuanXing"), ser.serialize(hpModel.getKuanXing()));
				connection.hSet(hKey,ser.serialize("banXing"), ser.serialize(hpModel.getBanXing()));
				connection.hSet(hKey,ser.serialize("fPrice"), ser.serialize(hpModel.getfPrice()));
				connection.hSet(hKey,ser.serialize("sPrice"), ser.serialize(hpModel.getsPrice()));
				
				connection.hSet(hKey,ser.serialize("tPrice"), ser.serialize(hpModel.gettPrice()));
				connection.hSet(hKey,ser.serialize("f1Price"), ser.serialize(hpModel.getF1Price()));
				connection.hSet(hKey,ser.serialize("f2Price"), ser.serialize(hpModel.getF2Price()));
				connection.hSet(hKey,ser.serialize("f3Price"), ser.serialize(hpModel.getF3Price()));
				connection.hSet(hKey,ser.serialize("upDown"), ser.serialize(hpModel.getUpDown()));
				connection.hSet(hKey,ser.serialize("huoPan"), ser.serialize(hpModel.getHuoPan()));
				connection.hSet(hKey,ser.serialize("cunhuo_type"), ser.serialize(hpModel.getCunhuo_type()));
				connection.hSet(hKey,ser.serialize("priceSegment"), ser.serialize(hpModel.getPriceSegment()));
				connection.hSet(hKey,ser.serialize("productionType"), ser.serialize(hpModel.getProductionType()));
				connection.hSet(hKey,ser.serialize("releventMetting"), ser.serialize(hpModel.getReleventMetting()));
				
				connection.hSet(hKey,ser.serialize("mettingTime"), ser.serialize(hpModel.getMettingTime()));
				connection.hSet(hKey,ser.serialize("productionArea"), ser.serialize(hpModel.getProductionArea()));
				connection.hSet(hKey,ser.serialize("entryPerson"), ser.serialize(hpModel.getEntryPerson()));
				connection.hSet(hKey,ser.serialize("entryTime"), ser.serialize(hpModel.getEntryTime()));
				connection.hSet(hKey,ser.serialize("updatePerson"), ser.serialize(hpModel.getUpdatePerson()));
				connection.hSet(hKey,ser.serialize("updateTime"), ser.serialize(hpModel.getUpdateTime()));
				connection.hSet(hKey,ser.serialize("effectPerson"), ser.serialize(hpModel.getEffectPerson()));
				connection.hSet(hKey,ser.serialize("effectTime"), ser.serialize(hpModel.getEffectTime()));
				connection.hSet(hKey,ser.serialize("failurePerson"), ser.serialize(hpModel.getFailurePerson()));
				connection.hSet(hKey,ser.serialize("failureTime"), ser.serialize(hpModel.getFailureTime()));
				connection.hSet(hKey,ser.serialize("timeCategory"), ser.serialize(hpModel.getTimeCategory()));
				connection.hSet(hKey,ser.serialize("productURL"), ser.serialize(hpModel.getProductUrl()));
				
				if (hpModel.getImageUrl1() != null && !hpModel.getImageUrl1().equals("")){
					connection.hSet(hKey,ser.serialize("imageUrl1"), ser.serialize(hpModel.getImageUrl1()));
				}
				
				if (hpModel.getImageUrl2() != null  && !hpModel.getImageUrl2().equals("")){
					connection.hSet(hKey,ser.serialize("imageUrl2"), ser.serialize(hpModel.getImageUrl2()));
				}

				if (hpModel.getImageUrl3() != null  && !hpModel.getImageUrl3().equals("")){
					connection.hSet(hKey,ser.serialize("imageUrl3"), ser.serialize(hpModel.getImageUrl3()));
				}
				
				
				String[] brandArray = hpModel.getBrand().split("\\,");
				for (String s : brandArray){
					String key = RedisVariableUtil.BRAND_TAG_PREFIX + RedisVariableUtil.DIVISION_CHAR +s;
					connection.sAdd(ser.serialize(key), ser.serialize(String.valueOf(id)));
				}
				
				String[] categoryArray = hpModel.getCategory().split("\\,");
				for (String s : categoryArray){
					String key = RedisVariableUtil.CATEGORY_TAG_PREFIX + RedisVariableUtil.DIVISION_CHAR +s; 
					connection.sAdd(ser.serialize(key), ser.serialize(String.valueOf(id)));
				}

				String[] sizeArray = hpModel.getSize().split("\\,");
				for (String s : sizeArray){
					String key = RedisVariableUtil.SIZE_TAG_PREFIX + RedisVariableUtil.DIVISION_CHAR +s;
					connection.sAdd(ser.serialize(key), ser.serialize(String.valueOf(id)));
				}
				
				String[] colorArray = hpModel.getColor().split("\\,");
				for (String s : colorArray){
					String key = RedisVariableUtil.COLOR_TAG_PREFIX + RedisVariableUtil.DIVISION_CHAR +s;
					connection.sAdd(ser.serialize(key), ser.serialize(String.valueOf(id)));
				}
				
				String[] timeArray = hpModel.getTimeCategory().split("\\,");
				for (String s : timeArray){
					String key = RedisVariableUtil.DATETIME_TAG_PREFIX + RedisVariableUtil.DIVISION_CHAR +s;
					connection.sAdd(ser.serialize(key), ser.serialize(String.valueOf(id)));
				}
				
				connection.hSet(ser.serialize("HpIDNum"),  ser.serialize(hpModel.getHp_num()), ser.serialize(String.valueOf(id)));

				//链接缺失
				int urlSign = RedisVariableUtil.DATA_STATUS_INTEGRITY;
				if (hpModel.getProductUrl() == null || hpModel.getProductUrl().equals("")){
					connection.lPush(ser.serialize(RedisVariableUtil.LOST_URL_LIST), ser.serialize(String.valueOf(id)));
					urlSign = RedisVariableUtil.DATA_STATUS_URL_LOST;
					connection.hSet(hKey,ser.serialize("dataStatus"), ser.serialize(String.valueOf(RedisVariableUtil.DATA_STATUS_URL_LOST)));
				}
				
				//图片缺失
				int imageSign = RedisVariableUtil.DATA_STATUS_INTEGRITY;
				if (hpModel.getImageUrl1() == null || hpModel.getImageUrl1().equals("")
						|| hpModel.getImageUrl2() == null || hpModel.getImageUrl2().equals("")
						|| hpModel.getImageUrl3() == null || hpModel.getImageUrl3().equals("")){
					connection.lPush(ser.serialize(RedisVariableUtil.LOST_IMAGE_LIST), ser.serialize(String.valueOf(id)));
					imageSign = RedisVariableUtil.DATA_STATUS_IMAGE_LOST;
					connection.hSet(hKey,ser.serialize("dataStatus"), ser.serialize(String.valueOf(RedisVariableUtil.DATA_STATUS_IMAGE_LOST)));
				}
				//图片链接都缺失
				if (urlSign == RedisVariableUtil.DATA_STATUS_URL_LOST && imageSign == RedisVariableUtil.DATA_STATUS_IMAGE_LOST){
					connection.lPush(ser.serialize(RedisVariableUtil.LOST_ALL_LIST), ser.serialize(String.valueOf(id)));
				}
				
				//数据完整
				if (urlSign == RedisVariableUtil.DATA_STATUS_INTEGRITY && imageSign == RedisVariableUtil.DATA_STATUS_INTEGRITY){
					connection.lPush(ser.serialize(RedisVariableUtil.DATA_INTEGRITY_LIST), ser.serialize(String.valueOf(id)));
					
					if (hpModel.getCategory().contains("1,")){ //库装
						connection.lPush(ser.serialize(RedisVariableUtil.LIST_TROUSER_CLOTH), ser.serialize(String.valueOf(id)));
					}
					else if (hpModel.getCategory().contains("2,")){ //外套
						connection.lPush(ser.serialize(RedisVariableUtil.LIST_OUTTER_CLOTH), ser.serialize(String.valueOf(id)));
					}
					else if (hpModel.getCategory().contains("3,")){ //内搭
						connection.lPush(ser.serialize(RedisVariableUtil.LIST_INNER_CLOTH), ser.serialize(String.valueOf(id)));
					}
				}
				
				if (urlSign == RedisVariableUtil.DATA_STATUS_URL_LOST && imageSign == RedisVariableUtil.DATA_STATUS_IMAGE_LOST){
					connection.hSet(hKey,ser.serialize("dataStatus"), ser.serialize(String.valueOf(RedisVariableUtil.DATA_STATUS_ALL_LOST)));
				}
				else if (urlSign == RedisVariableUtil.DATA_STATUS_INTEGRITY && imageSign == RedisVariableUtil.DATA_STATUS_INTEGRITY){
					connection.hSet(hKey,ser.serialize("dataStatus"), ser.serialize(String.valueOf(RedisVariableUtil.DATA_STATUS_INTEGRITY)));
				}
				
				
				List<Object> resultList = connection.exec();
				
				System.out.println("执行命令数量:"+ resultList.size());
				resultList.stream().forEach((o)->{
					System.out.println("命令执行结果:"+o.toString());
				});
				
				return id;
			}
		});
	}

	@Override
	public void updateHpData(HPModel hpModel) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {

				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				
				connection.multi();
				int id = hpModel.getId();

				byte[] hKey = ser.serialize(RedisVariableUtil.HP_RECORD_PREFIX + RedisVariableUtil.DIVISION_CHAR + id);
				connection.hSet(hKey,ser.serialize("hp_num"), ser.serialize(hpModel.getHp_num()));
				connection.hSet(hKey,ser.serialize("price"), ser.serialize(hpModel.getPrice()));
				
				if (hpModel.getImageUrl1() != null){
					connection.hSet(hKey,ser.serialize("imageUrl1"), ser.serialize(hpModel.getImageUrl1()));
				}
				if (hpModel.getImageUrl2() != null){
					connection.hSet(hKey,ser.serialize("imageUrl2"), ser.serialize(hpModel.getImageUrl2()));				
				}
				if (hpModel.getImageUrl3() != null){
					connection.hSet(hKey,ser.serialize("imageUrl3"), ser.serialize(hpModel.getImageUrl3()));
				}

				if (hpModel.getProductUrl() != null){
					connection.hSet(hKey,ser.serialize("productURL"), ser.serialize(hpModel.getProductUrl()));
				}
				
				int imageSign = RedisVariableUtil.DATA_STATUS_INTEGRITY;
				if (hpModel.getImageUrl1() != null && !hpModel.getImageUrl1().equals("")
						&& hpModel.getImageUrl2() != null && !hpModel.getImageUrl2().equals("")
						&& hpModel.getImageUrl3() != null && !hpModel.getImageUrl3().equals("")){
					connection.lRem(ser.serialize(RedisVariableUtil.LOST_IMAGE_LIST), 0, ser.serialize(String.valueOf(id)));
				}
				else{
					connection.hSet(hKey,ser.serialize("dataStatus"), ser.serialize(String.valueOf(RedisVariableUtil.DATA_STATUS_IMAGE_LOST)));
					imageSign = RedisVariableUtil.DATA_STATUS_IMAGE_LOST;
				}

				int urlSign = RedisVariableUtil.DATA_STATUS_INTEGRITY;
				if (hpModel.getProductUrl() != null && !hpModel.getProductUrl().equals("")){
					connection.lRem(ser.serialize(RedisVariableUtil.LOST_URL_LIST), 0, ser.serialize(String.valueOf(id)));
				}
				else{
					connection.hSet(hKey,ser.serialize("dataStatus"), ser.serialize(String.valueOf(RedisVariableUtil.DATA_STATUS_URL_LOST)));
					urlSign = RedisVariableUtil.DATA_STATUS_URL_LOST;
				}

				if (imageSign == RedisVariableUtil.DATA_STATUS_IMAGE_LOST && urlSign == RedisVariableUtil.DATA_STATUS_URL_LOST){
					connection.hSet(hKey,ser.serialize("dataStatus"), ser.serialize(String.valueOf(RedisVariableUtil.DATA_STATUS_ALL_LOST))); 
				}
				
				if (imageSign == RedisVariableUtil.DATA_STATUS_INTEGRITY && urlSign == RedisVariableUtil.DATA_STATUS_INTEGRITY){
					connection.hSet(hKey,ser.serialize("dataStatus"), ser.serialize(String.valueOf(RedisVariableUtil.DATA_STATUS_INTEGRITY))); //数据完整
					connection.lPush(ser.serialize(RedisVariableUtil.DATA_INTEGRITY_LIST), ser.serialize(String.valueOf(id)));
					if (hpModel.getCategory().contains("1,")){ //库装
						connection.lPush(ser.serialize(RedisVariableUtil.LIST_TROUSER_CLOTH), ser.serialize(String.valueOf(id)));
					}
					else if (hpModel.getCategory().contains("2,")){ //外套
						connection.lPush(ser.serialize(RedisVariableUtil.LIST_OUTTER_CLOTH), ser.serialize(String.valueOf(id)));
					}
					else if (hpModel.getCategory().contains("3,")){ //内搭
						connection.lPush(ser.serialize(RedisVariableUtil.LIST_INNER_CLOTH), ser.serialize(String.valueOf(id)));
					}
				}
				else{
					connection.lRem(ser.serialize(RedisVariableUtil.DATA_INTEGRITY_LIST), 0, ser.serialize(String.valueOf(id)));
				}
				List<Object> resultList = connection.exec();
				logger.info("执行命令数量�?", resultList.size());
				
				return null;
			}
		});
	}

	@Override
	public void deleteHpData(String id) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {

				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				
				connection.multi();
				connection.del(ser.serialize(RedisVariableUtil.HP_RECORD_PREFIX + RedisVariableUtil.DIVISION_CHAR + id));
				connection.lRem(ser.serialize(RedisVariableUtil.HP_RECORD_PREFIX + "Id"), 0, ser.serialize(id));
				connection.lRem(ser.serialize(RedisVariableUtil.LIST_OUTTER_CLOTH), 0, ser.serialize(id));
				connection.lRem(ser.serialize(RedisVariableUtil.LIST_INNER_CLOTH), 0, ser.serialize(id));
				connection.lRem(ser.serialize(RedisVariableUtil.LIST_TROUSER_CLOTH), 0, ser.serialize(id));
				connection.sRem(ser.serialize(RedisVariableUtil.LOST_URL_LIST), ser.serialize(id));
				connection.sRem(ser.serialize(RedisVariableUtil.LOST_IMAGE_LIST), ser.serialize(id));
				
				Set<byte[]> tagKeys = connection.keys(ser.serialize("tag_*"));
				for (byte[] tagKey : tagKeys){
					connection.sRem(tagKey, ser.serialize(id));
				}
				List<Object> resultList = connection.exec();
				logger.info("执行命令数量�?", resultList.size());
				resultList.stream().forEach((o)->{
					logger.info("命令执行结果�?",o.toString());
				});
				return null;
			}
		});
	}

	@Override
	public HPListModel getHpData(int iDisplayStart,int iDisplayLength) {
		String id_key = "HpIDList";
		
		return redisTemplate.execute(new RedisCallback<HPListModel>() {
			@Override
			public  HPListModel doInRedis(RedisConnection connection) throws DataAccessException {

				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				HPListModel hpListModel = new HPListModel();
				
				
				return hpListModel;
			}
		});
		
	}

	@Override
	public HPModel getHpDataById(int id) {
		
		return redisTemplate.execute(new RedisCallback<HPModel>() {
			@Override
			public  HPModel doInRedis(RedisConnection connection) throws DataAccessException {

				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				
				return getHPModelById(id,connection,ser);
			}
		});
	}

	@Override
	public HPModel getHpDataByHpNum(String hp_num) {
		return redisTemplate.execute(new RedisCallback<HPModel>() {
			@Override
			public  HPModel doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();

				byte[] bId = connection.hGet(ser.serialize("HpIDNum"), ser.serialize(hp_num));
				if (bId == null){
					return null;
				}
				else{
					int id = Integer.parseInt(ser.deserialize(bId));
					
					return getHPModelById(id,connection,ser);
				}
				
			}
		});
	}

	private HPModel getHPModelById(int id,RedisConnection connection,RedisSerializer<String> ser){
		
		Map<byte[],byte[]> map = connection.hGetAll(ser.serialize(RedisVariableUtil.HP_RECORD_PREFIX + RedisVariableUtil.DIVISION_CHAR + id));
		Map<byte[],byte[]> brandMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.BRAND_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
		Map<byte[],byte[]> categoryMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.CATEGORY_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
		Map<byte[],byte[]> colorMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.COLOR_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
		Map<byte[],byte[]> timeCategoryMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.DATETIME_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
		
		Map<byte[],byte[]> tempColorMap = new HashMap<>();
		tempColorMap.putAll(colorMapList);
		tempColorMap.forEach((k,v)->{
			Map<byte[],byte[]> subColorMap = connection.hGetAll(k);
			colorMapList.putAll(subColorMap);
		});

		Map<byte[],byte[]> sizeMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.SIZE_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
		
		HPModel hpModel = null;
		
		hpModel = new HPModel();
		Map<String, String> resultMap = new HashMap<>();
		map.forEach((k, v) -> {
			resultMap.put(ser.deserialize(k), ser.deserialize(v));
		});
		hpModel.setId(id);
		hpModel.setHp_num(resultMap.get("hp_num"));
		hpModel.setBrand(resultMap.get("brand"));
		hpModel.setCategory(resultMap.get("category"));
		hpModel.setHpName(resultMap.get("hpName"));
		hpModel.setSize(resultMap.get("size"));
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
		
		return hpModel;
	}

	@Override
	public Map<String, String> initExcelBaseData() {
		
		return redisTemplate.execute(new RedisCallback<Map<String, String>>() {
			@Override
			public  Map<String, String> doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();

				Map<byte[],byte[]> brandMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.BRAND_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
				Map<byte[],byte[]> categoryMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.CATEGORY_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
				Map<byte[],byte[]> colorMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.COLOR_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
				Map<byte[],byte[]> sizeMapList = connection.hGetAll(ser.serialize(RedisVariableUtil.SIZE_PREFIX + RedisVariableUtil.DIVISION_CHAR + "0"));
				
				Map<String, String> map = new HashMap<String,String>();
				
				brandMapList.forEach((k,v)->{
					
				});
				
				categoryMapList.forEach((k,v)->{
									
				});
				
				colorMapList.forEach((k,v)->{
					
				});
				
				sizeMapList.forEach((k,v)->{
					
				});
				
				return map;
				
			}
		});
		
		
	}
}
