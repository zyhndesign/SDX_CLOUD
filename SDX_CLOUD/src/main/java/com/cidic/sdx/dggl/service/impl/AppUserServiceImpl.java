package com.cidic.sdx.dggl.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.dggl.dao.AppUserDao;
import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.service.AppUserService;
import com.cidic.sdx.util.ResponseCodeUtil;

@Repository
@Component
@Qualifier(value = "appUserServiceImpl")
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	@Qualifier("appUserDaoImpl")
	private AppUserDao appUserDaoImpl;

	@Override
	public int createUser(User user) {
		try {
			Optional<User> optUser = appUserDaoImpl.findByUsername(user.getUsername());
			if (optUser.isPresent()) {
				return ResponseCodeUtil.UESR_CREATE_EXIST;
			} else {
				appUserDaoImpl.createUser(user);
				return ResponseCodeUtil.UESR_OPERATION_SUCESS;
			}
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateUser(User user) {
		try{
			appUserDaoImpl.updateUser(user);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
		
	}

	@Override
	public int deleteUser(int userId) {
		try{
			appUserDaoImpl.deleteUser(userId);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		}
		catch(Exception e){
			System.out.print(e.getMessage());
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public Optional<User> findByUsername(String username) {

		return appUserDaoImpl.findByUsername(username);
	}

	@Override
	public Optional<User> authorityCheck(String username, String password) {

		return appUserDaoImpl.authorityCheck(username, password);
	}

	@Override
	public List<User> getUserListByPage(int limit, int offset) {

		return appUserDaoImpl.getUserListByPage(limit, offset);
	}

}
