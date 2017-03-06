package com.cidic.sdx.dggl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.model.UserListModel;
import com.cidic.sdx.dggl.service.AppUserService;
import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.hpgl.controller.BrandSettingController;
import com.cidic.sdx.hpgl.model.ListResultModel;
import com.cidic.sdx.hpgl.model.ResultModel;
import com.cidic.sdx.util.ResponseCodeUtil;
import com.cidic.sdx.util.WebRequestUtil;

@Controller
@RequestMapping("/dggl/appUser")
public class AppUserController {

	private static final Logger logger = LoggerFactory.getLogger(BrandSettingController.class);

	@Autowired
	@Qualifier(value = "appUserServiceImpl")
	private AppUserService appUserServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(SdxException.class)
	public @ResponseBody ResultModel handleCustomException(SdxException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}

	@RequestMapping(value = "/authorityCheck", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel authorityCheck(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String username, @RequestParam String password) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		String msg = "";
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				resultModel.setResultCode(200);
				resultModel.setSuccess(true);
			} else {
				resultModel.setResultCode(500);
				resultModel.setSuccess(false);
			}
		} catch (IncorrectCredentialsException e) {
			msg = "登录密码错误.";
		} catch (ExcessiveAttemptsException e) {
			msg = "登录失败次数过多";
		} catch (LockedAccountException e) {
			msg = "帐号已被锁定.";
		} catch (DisabledAccountException e) {
			msg = "帐号已被禁用. ";
		} catch (ExpiredCredentialsException e) {
			msg = "帐号已过期.";
		} catch (UnknownAccountException e) {
			msg = "帐号不存在.";
		} catch (UnauthorizedException e) {
			msg = "您没有得到相应的授权！";
		}
		System.out.println(msg);
		resultModel.setMessage(msg);
		return resultModel;
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String username, @RequestParam String password) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		User userObject = new User();
		userObject.setPassword(password);
		userObject.setUsername(username);
		userObject.setValid(1);
		int result = appUserServiceImpl.createUser(userObject);
		if (result == ResponseCodeUtil.UESR_CREATE_EXIST) {
			throw new SdxException(300, "用户已存在");
		} else if (result == ResponseCodeUtil.UESR_OPERATION_SUCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateUser(HttpServletRequest request, HttpServletResponse response, @RequestParam int userId,
			@RequestParam String username, @RequestParam String password, @RequestParam String headIcon) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		User userObject = new User();
		userObject.setPassword(password);
		userObject.setUsername(username);
		userObject.setHeadicon(headIcon);
		userObject.setId(userId);
		int result = appUserServiceImpl.updateUser(userObject);
		if (result == ResponseCodeUtil.UESR_OPERATION_SUCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteUser(HttpServletRequest request, HttpServletResponse response, @RequestParam int userId) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = appUserServiceImpl.deleteUser(userId);
		if (result == ResponseCodeUtil.UESR_OPERATION_SUCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}

	@RequestMapping(value = "/getData", method = RequestMethod.GET)
	@ResponseBody
	public ListResultModel getData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int iDisplayLength, @RequestParam int iDisplayStart, @RequestParam String sEcho) {

		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();
		try {
			UserListModel userListModel = appUserServiceImpl.getUserListByPage(iDisplayLength, iDisplayStart);
			listResultModel.setAaData(userListModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords((int) userListModel.getCount());
			listResultModel.setiTotalDisplayRecords((int) userListModel.getList().size());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}

	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel loginSuccess(HttpServletRequest request, HttpServletResponse response) {
		resultModel = new ResultModel();
		resultModel.setResultCode(200);
		resultModel.setSuccess(true);
		return resultModel;
	}
	
	@RequestMapping(value = "/loginFailure", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel loginFailure(HttpServletRequest request, HttpServletResponse response) {
		resultModel = new ResultModel();
		resultModel.setResultCode(500);
		resultModel.setSuccess(true);
		return resultModel;
	}
}
