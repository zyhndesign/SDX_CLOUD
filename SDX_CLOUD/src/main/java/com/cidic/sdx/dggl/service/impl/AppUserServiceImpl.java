package com.cidic.sdx.dggl.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
@Transactional
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
	public int updatePwd(int userId, String password, String newPwd, String username) {
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			try {
				subject.login(token);
				if (subject.isAuthenticated()) {
					User user = new User();
					user.setId(userId);
					user.setPassword(newPwd);
					user.setUsername(username);
					PasswordHelper.encryptAppPassword(user);

					appUserDaoImpl.updatePwd(userId, user.getPassword(), user.getSlot());
					return ResponseCodeUtil.UESR_OPERATION_SUCESS;
				} else {
					return ResponseCodeUtil.USER_OLDPWD_ERROR;
				}
			} catch (IncorrectCredentialsException e) {
				return ResponseCodeUtil.USER_OLDPWD_ERROR;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

	@Override
	public int disableAccount(int userId) {
		try {
			appUserDaoImpl.disableAccount(userId);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int enableAccount(int userId) {
		try {
			appUserDaoImpl.enableAccount(userId);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int appUpdateData(String phone, String headicon, int id) {
		try {
			appUserDaoImpl.appUpdateData(phone, headicon, id);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public List<User> getAllUserForSelect() {
		return appUserDaoImpl.getAllUserForSelect();
	}

}
