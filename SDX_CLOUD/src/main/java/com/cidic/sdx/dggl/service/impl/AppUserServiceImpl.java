package com.cidic.sdx.dggl.service.impl;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
