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
import com.cidic.sdx.dggl.service.AppUserService;
import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.hpgl.controller.BrandSettingController;
import com.cidic.sdx.hpgl.model.ResultModel;
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
}
