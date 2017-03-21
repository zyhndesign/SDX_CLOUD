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

import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.hpgl.model.HPListModel;
import com.cidic.sdx.hpgl.model.ListResultModel;
import com.cidic.sdx.hpgl.model.ResultModel;
import com.cidic.sdx.hpgl.service.HpIndexService;
import com.cidic.sdx.util.WebRequestUtil;

@Controller
@RequestMapping("/hpgl/appHp")
public class ClothingController {

	@Autowired
	@Qualifier(value = "hpIndexServiceImpl")
	private HpIndexService hpIndexServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(SdxException.class)
	public @ResponseBody ResultModel handleCustomException(SdxException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}

	@RequestMapping(value = "/getDataByCategoryId", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ListResultModel getDataByCategoryId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int category, @RequestParam int limit, @RequestParam int offset) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();
		try {

			HPListModel resultData = hpIndexServiceImpl.getMatchListByCategoryType(category, offset, limit);

			listResultModel.setAaData(resultData.getList());
			listResultModel.setiTotalRecords((int) resultData.getCount());
			listResultModel.setiTotalDisplayRecords((int) resultData.getList().size());
			listResultModel.setSuccess(true);
			
			listResultModel.setiTotalRecords(0);
			listResultModel.setiTotalDisplayRecords(0);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
}
