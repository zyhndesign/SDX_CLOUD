package com.cidic.sdx.dggl.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.dggl.dao.AppUserDao;
import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.service.AppUserService;

@Repository
@Component
@Qualifier(value = "appUserServiceImpl")
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	@Qualifier("appUserDaoImpl")
	private AppUserDao appUserDaoImpl;
	
	@Override
	public void createUser(User user) {
		appUserDaoImpl.createUser(user);
	}

	@Override
	public void updateUser(User user) {
		appUserDaoImpl.updateUser(user);
	}

	@Override
	public void deleteUser(int userId) {
		appUserDaoImpl.deleteUser(userId);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		
		return appUserDaoImpl.findByUsername(username);
	}

	@Override
	public Optional<User> authorityCheck(String username, String password) {
		
		return appUserDaoImpl.authorityCheck(username, password);
	}

}
