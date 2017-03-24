package com.cidic.sdx.dggl.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.AppUserDao;
import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.model.UserListModel;
import com.cidic.sdx.dggl.service.AppUserService;
import com.cidic.sdx.util.PasswordHelper;
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
				PasswordHelper.encryptAppPassword(user);
				appUserDaoImpl.createUser(user);
				return ResponseCodeUtil.UESR_OPERATION_SUCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateUser(User user) {
		try {
			appUserDaoImpl.updateUser(user);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {

			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

	@Override
	public int deleteUser(int userId) {
		try {
			appUserDaoImpl.deleteUser(userId);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
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
	@Transactional(rollbackFor = Exception.class)
	public UserListModel getUserListByPage(int limit, int offset) {
		UserListModel userListModel = new UserListModel();
		userListModel.setCount(appUserDaoImpl.getUserCount());
		userListModel.setList(appUserDaoImpl.getUserListByPage(limit, offset));
		return userListModel;
	}

	@Override
	public Optional<User> findUserById(int userId) {
		// TODO Auto-generated method stub
		return appUserDaoImpl.findUserById(userId);
	}

	@Override
	public UserListModel getUserListByCondition(int shopId, String username, int iDisplayStart, int iDisplayLength) {

		List<User> list = appUserDaoImpl.findUserByShopIdAndUsername(shopId, username, iDisplayStart, iDisplayLength);
		Long count = appUserDaoImpl.getUserCountByCondition(shopId, username);
		UserListModel userListModel = new UserListModel();
		userListModel.setList(list);
		userListModel.setCount(count);
		return userListModel;
	}

	@Override
	public int updatePwd(String serialnumber, String password) {
		try {
			User user = new User();
			user.setSerialnumber(serialnumber);
			user.setPassword(password);
			PasswordHelper.encryptAppPassword(user);
			appUserDaoImpl.updatePwd(serialnumber, user.getPassword(), user.getSlot());
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

}
