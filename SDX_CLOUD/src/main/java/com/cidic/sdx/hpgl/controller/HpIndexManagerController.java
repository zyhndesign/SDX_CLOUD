package com.cidic.sdx.hpgl.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.hpgl.model.HPListModel;
import com.cidic.sdx.hpgl.model.HPModel;
import com.cidic.sdx.hpgl.model.ListResultModel;
import com.cidic.sdx.hpgl.model.ResultModel;
import com.cidic.sdx.hpgl.service.HpIndexService;
import com.cidic.sdx.hpgl.service.HpManageService;
import com.cidic.sdx.util.WebRequestUtil;

@Controller
@RequestMapping("/hpgl/hpIndexManage")
public class HpIndexManagerController {

	private static final Logger logger = LoggerFactory.getLogger(HpIndexManagerController.class);

	@Autowired
	@Qualifier(value = "hpIndexServiceImpl")
	private HpIndexService hpIndexServiceImpl;

	@Autowired
	@Qualifier(value = "hpManageServiceImpl")
	private HpManageService hpManageServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(SdxException.class)
	public @ResponseBody ResultModel handleCustomException(SdxException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}

	@RequestMapping(value = "/hpIndex", method = RequestMethod.GET)
	public String userMgr(Locale locale, Model model) {
		return "hpIndex";
	}

	@RequestMapping(value = "/getData", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ListResultModel getData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) int dataCategory, @RequestParam(required = false) String brand,
			@RequestParam(required = false) String color, @RequestParam(required = false) String size,
			@RequestParam(required = false) String category,
			@RequestParam(required = false) int dataStatus,
			@RequestParam int iDisplayLength,
			@RequestParam int iDisplayStart, @RequestParam String sEcho,
			@RequestParam(required = false) String hp_num) {

		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();

		try {
			if (dataCategory == 0) {
				if (hp_num == null || hp_num.equals("")) {
					
					HPListModel resultData = hpIndexServiceImpl.getIndexDataByTag(brand, color, category, size, iDisplayStart, iDisplayLength);

					listResultModel.setAaData(resultData.getList());
					listResultModel.setsEcho(sEcho);
					listResultModel.setiTotalRecords((int) resultData.getCount());
					listResultModel.setiTotalDisplayRecords((int) resultData.getCount());
					listResultModel.setSuccess(true);
				} else {
					listResultModel = this.getHpDataByHpNum(hp_num, sEcho);
				}
			} else if (dataCategory == 1) { // URL缺失
				if (hp_num == null || hp_num.equals("")) {
					HPListModel resultData = null;
					if (dataStatus == 0){
						resultData = hpIndexServiceImpl.getAllIntegrityData(iDisplayStart, iDisplayLength);
					}
					else if (dataStatus == 1){
						resultData = hpIndexServiceImpl.getLostImageData(iDisplayStart, iDisplayLength);
					}
					else if (dataStatus == 2){
						resultData = hpIndexServiceImpl.getLostURLData(iDisplayStart, iDisplayLength);				
					}
					else if (dataStatus == 3){
						resultData = hpIndexServiceImpl.getAllLostData(iDisplayStart, iDisplayLength);
					}
					
					if (resultData != null){
						listResultModel.setAaData(resultData.getList());
						listResultModel.setsEcho(sEcho);
						listResultModel.setiTotalRecords((int) resultData.getCount());
						listResultModel.setiTotalDisplayRecords((int) resultData.getCount());
						listResultModel.setSuccess(true);
					}
				}
				else{
					listResultModel = this.getHpDataByHpNum(hp_num, sEcho);
				}
			}
			else if(dataCategory == -1){
				HPListModel resultData = hpIndexServiceImpl.getAllData(iDisplayStart, iDisplayLength);

				listResultModel.setAaData(resultData.getList());
				listResultModel.setsEcho(sEcho);
				listResultModel.setiTotalRecords((int) resultData.getCount());
				listResultModel.setiTotalDisplayRecords((int) resultData.getCount());
				listResultModel.setSuccess(true);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	private ListResultModel getHpDataByHpNum(String hp_num,String sEcho){
		ListResultModel listResultModel = new ListResultModel();
		HPModel hpModel = hpManageServiceImpl.getHpDataByHpNum(hp_num);

		List<HPModel> list = new ArrayList<>(1);
		if (hpModel != null) {
			list.add(hpModel);
			listResultModel.setiTotalRecords(1);
			listResultModel.setiTotalDisplayRecords(1);
		} else {
			listResultModel.setiTotalRecords(0);
			listResultModel.setiTotalDisplayRecords(0);
		}

		listResultModel.setAaData(list);
		listResultModel.setsEcho(sEcho);
		listResultModel.setSuccess(true);
		return listResultModel;
	}
	
	@RequestMapping(value = "/getAppData", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResultModel getAppData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String brand,
			@RequestParam(required = false) String category){

		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ResultModel resultModel = new ResultModel();

		try {
			List<HPModel> list = hpIndexServiceImpl.getAppIndexDataByTag(brand, category);
			resultModel.setSuccess(true);
			resultModel.setObject(list);	
		}
		catch (Exception e) {
			e.printStackTrace();
			resultModel.setSuccess(false);
		}
		return resultModel;
	}
}
