package com.cidic.sdx.dggl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.sdx.dggl.model.Apphelp;
import com.cidic.sdx.dggl.service.AppHelpService;
import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.hpgl.model.ResultModel;
import com.cidic.sdx.util.ResponseCodeUtil;
import com.cidic.sdx.util.WebRequestUtil;

@Controller
@RequestMapping("/dggl/appHelper")
public class AppHelpController {

	@Autowired
	@Qualifier(value = "appHelpServiceImpl")
	private AppHelpService appHelpServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(SdxException.class)
	public @ResponseBody ResultModel handleCustomException(SdxException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	@RequestMapping(value = "/createInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam String info) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Apphelp appHelp = new Apphelp();
		appHelp.setInfo(info);
		appHelp.setUserId(userId);
		int result = appHelpServiceImpl.createHelpInfo(appHelp);
		if (result == ResponseCodeUtil.UESR_OPERATION_SUCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}
}
