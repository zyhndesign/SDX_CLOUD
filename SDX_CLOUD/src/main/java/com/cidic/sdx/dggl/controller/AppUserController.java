package com.cidic.sdx.dggl.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/appUser")
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
		Optional<User> user = appUserServiceImpl.authorityCheck(username, password);
		if (user.isPresent()){
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		else{
			throw new SdxException(500, "权限验证失败");
		}
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
		if (result == ResponseCodeUtil.UESR_CREATE_EXIST){
			throw new SdxException(300, "用户已存在");
		}
		else if(result == ResponseCodeUtil.UESR_OPERATION_SUCESS){
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		else{
			throw new SdxException(500, "操作失败");
		}
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateUser(HttpServletRequest request, HttpServletResponse response,@RequestParam int userId,
			@RequestParam String username, @RequestParam String password, @RequestParam String headIcon) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		User userObject = new User();
		userObject.setPassword(password);
		userObject.setUsername(username);
		userObject.setHeadicon(headIcon);
		userObject.setId(userId);
		int result = appUserServiceImpl.updateUser(userObject);
		if (result == ResponseCodeUtil.UESR_OPERATION_SUCESS){
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		else{
			throw new SdxException(500, "操作失败");
		}
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = appUserServiceImpl.deleteUser(userId);
		if (result == ResponseCodeUtil.UESR_OPERATION_SUCESS){
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		else{
			throw new SdxException(500, "操作失败");
		}
	}
	
	@RequestMapping(value = "/getData", method = RequestMethod.GET)
	@ResponseBody
	public ListResultModel getData(HttpServletRequest request, HttpServletResponse response, @RequestParam int iDisplayLength, @RequestParam int iDisplayStart,@RequestParam String sEcho) {

		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();
		try {
			UserListModel userListModel= appUserServiceImpl.getUserListByPage(iDisplayLength,iDisplayStart);
			listResultModel.setAaData(userListModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords((int)userListModel.getCount());
			listResultModel.setiTotalDisplayRecords((int)userListModel.getList().size());
			listResultModel.setSuccess(true);
		}
		catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
}
