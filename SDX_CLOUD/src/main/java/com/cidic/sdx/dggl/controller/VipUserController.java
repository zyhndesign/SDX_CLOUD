package com.cidic.sdx.dggl.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.sdx.dggl.model.Vipuser;
import com.cidic.sdx.dggl.service.VipUserService;
import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.hpgl.controller.BrandSettingController;
import com.cidic.sdx.hpgl.model.ResultModel;
import com.cidic.sdx.util.ResponseCodeUtil;
import com.cidic.sdx.util.WebRequestUtil;

@Controller
@RequestMapping("/dggl/appVipUser")
public class VipUserController {

	private static final Logger logger = LoggerFactory.getLogger(BrandSettingController.class);

	@Autowired
	@Qualifier(value = "vipUserServiceImpl")
	private VipUserService vipUserServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(SdxException.class)
	public @ResponseBody ResultModel handleCustomException(SdxException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createVipUser(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Vipuser user) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = vipUserServiceImpl.createVipUser(user);
		if (result == ResponseCodeUtil.MATCH_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateVipUser(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Vipuser user) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = vipUserServiceImpl.updateVipUser(user);
		if (result == ResponseCodeUtil.MATCH_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteVipUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = vipUserServiceImpl.deleteVipUser(userId);
		if (result == ResponseCodeUtil.MATCH_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}

	// 用于app加载当前导购的客户
	@RequestMapping(value = "/getVipuserByShoppingGuideId", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getVipuserByShoppingGuideId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int shoppingGuideId) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		List<Vipuser> feedBackList = vipUserServiceImpl.getVipuserByShoppingGuideId(shoppingGuideId);

		resultModel.setResultCode(200);
		resultModel.setSuccess(true);
		resultModel.setObject(feedBackList);
		return resultModel;
	}

	// 用于后台管理VIP客户
	@RequestMapping(value = "/getVipuserByPage", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getVipuserByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int limit, @RequestParam int offset) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		List<Vipuser> feedBackList = vipUserServiceImpl.getVipuserByPage(limit, offset);

		resultModel.setResultCode(200);
		resultModel.setSuccess(true);
		resultModel.setObject(feedBackList);
		return resultModel;
	}

	// 根据卡号查询Vip信息
	@RequestMapping(value = "/getVipuserByCardNumber", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getVipuserByCardNumber(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String cardNumber) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Optional<Vipuser> vipUser = vipUserServiceImpl.getVipuserByCardNumber(cardNumber);

		resultModel.setResultCode(200);
		resultModel.setSuccess(true);
		resultModel.setObject(vipUser);
		return resultModel;
	}
}
