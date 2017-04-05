package com.cidic.sdx.hpgl.controller;

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

import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.hpgl.model.AppResultModel;
import com.cidic.sdx.hpgl.model.HPModel;
import com.cidic.sdx.hpgl.model.ResultModel;
import com.cidic.sdx.hpgl.service.AppHpService;
import com.cidic.sdx.hpgl.service.TagService;
import com.cidic.sdx.util.HpModelUtil;
import com.cidic.sdx.util.WebRequestUtil;

@Controller
@RequestMapping("/hpgl/app/hpManage")
public class AppHpDataController {

	private static final Logger logger = LoggerFactory.getLogger(BrandSettingController.class);

	@Autowired
	@Qualifier(value = "appHpServiceImpl")
	private AppHpService appHpServiceImpl;

	@Autowired
	@Qualifier(value = "tagServiceImpl")
	private TagService tagServiceImpl;
	
	@ExceptionHandler(SdxException.class)
	public @ResponseBody AppResultModel handleCustomException(SdxException ex) {
		AppResultModel appResultModel = new AppResultModel();
		appResultModel.setResultCode(ex.getErrCode());
		appResultModel.setMessage(ex.getErrMsg());
		appResultModel.setSuccess(false);
		return appResultModel;
	}
	
	@RequestMapping(value = "/getHpData", method = RequestMethod.GET)
	public AppResultModel getHpData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int offset, @RequestParam int limit){

		WebRequestUtil.AccrossAreaRequestSet(request, response);
		AppResultModel appResultModel = new AppResultModel();
		try {
			
			appResultModel.setSuccess(true);
			appResultModel.setObject(appHpServiceImpl.getHpData(offset, limit));
			appResultModel.setMessage("操作成功");
			appResultModel.setResultCode(200);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new SdxException(500, "写入数据失败");
		}
		return appResultModel;
	}
	
	@RequestMapping(value = "/getHpDetailDataById", method = RequestMethod.GET)
	public AppResultModel getHpDetailDataById(HttpServletRequest request, HttpServletResponse response,@RequestParam int id){
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		AppResultModel appResultModel = new AppResultModel();
		try {
			
			appResultModel.setSuccess(true);
			appResultModel.setObject(appHpServiceImpl.getHpDetailDataById(id));
			appResultModel.setMessage("操作成功");
			appResultModel.setResultCode(200);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new SdxException(500, "写入数据失败");
		}
		return appResultModel;
	}
	
	@RequestMapping(value = "/getHpDataByCategoryId", method = RequestMethod.GET)
	public AppResultModel getHpDataByCategoryId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int offset, @RequestParam int limit,@RequestParam int categoryId){

		WebRequestUtil.AccrossAreaRequestSet(request, response);
		AppResultModel appResultModel = new AppResultModel();
		try {
			
			appResultModel.setSuccess(true);
			if (categoryId == 0){
				appResultModel.setObject(appHpServiceImpl.getInnerClothData(offset, limit));
			}
			else if (categoryId == 1){
				appResultModel.setObject(appHpServiceImpl.getOutterClothData(offset, limit));
			}
			else if (categoryId == 2){
				appResultModel.setObject(appHpServiceImpl.getTrouserClothData(offset, limit));
			}
			
			appResultModel.setMessage("操作成功");
			appResultModel.setResultCode(200);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new SdxException(500, "写入数据失败");
		}
		return appResultModel;
	}

}
