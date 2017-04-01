package com.cidic.sdx.hpgl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cidic.sdx.hpgl.model.AppResultModel;
import com.cidic.sdx.hpgl.service.AppHpService;
import com.cidic.sdx.hpgl.service.TagService;

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
	
	@RequestMapping(value = "/getHpData", method = RequestMethod.GET)
	public AppResultModel getHpData(@RequestParam int offset, @RequestParam int limit){
		
		return null;
	}
	
	@RequestMapping(value = "/getHpDetailDataById", method = RequestMethod.GET)
	public AppResultModel getHpDetailDataById(@RequestParam int id){
		
		return null;
	}
}
