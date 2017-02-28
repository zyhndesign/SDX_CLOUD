package com.cidic.sdx.dggl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.sdx.dggl.service.ShareService;
import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.hpgl.model.ResultModel;

@Controller
@RequestMapping("/share")
public class ShareController {

	@Autowired
	@Qualifier(value = "shareServiceImpl")
	private ShareService shareServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(SdxException.class)
	public @ResponseBody ResultModel handleCustomException(SdxException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}
}
