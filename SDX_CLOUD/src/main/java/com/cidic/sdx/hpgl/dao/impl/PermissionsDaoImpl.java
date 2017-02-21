package com.cidic.sdx.hpgl.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.data.redis.hash.DecoratingStringHashMapper;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.hpgl.dao.PermissionDao;
import com.cidic.sdx.hpgl.model.PermissionsModel;
import com.cidic.sdx.util.RedisVariableUtil;

@Repository
@Component
@Qualifier(value = "permissionsDaoImpl")
public class PermissionsDaoImpl implements PermissionDao {

	@Autowired
	@Qualifier(value = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource(name="redisTemplate")
    HashOperations<String, String, String> hashOperations;
	
    private final HashMapper<PermissionsModel, String, String> mapper =
    	     new DecoratingStringHashMapper<PermissionsModel>(new BeanUtilsHashMapper<PermissionsModel>(PermissionsModel.class));
    
    public void writeHash(String key, PermissionsModel permissionsModel) {
      Map<String, String> mappedHash = mapper.toHash(permissionsModel);
      hashOperations.putAll(key, mappedHash);
    }
    
	@Override
	public PermissionsModel createPermission(PermissionsModel permission) {
		long id = redisTemplate.opsForValue().increment(RedisVariableUtil.PERMISSION_PRIFIX + "Id", 1);
		permission.setId((int)id);
		writeHash(RedisVariableUtil.PERMISSION_PRIFIX +":"+id, permission);
		return permission;
	}

	@Override
	public void deletePermission(Long permissionId) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {

				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				byte[] roleKey = ser.serialize(RedisVariableUtil.PERMISSION_PRIFIX +":"+permissionId);
				connection.hDel(roleKey, ser.serialize("permission"),ser.serialize("description"));
				return null;
			}
		});
	}

}
