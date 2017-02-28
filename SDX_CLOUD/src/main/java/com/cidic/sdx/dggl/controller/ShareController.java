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

import com.cidic.sdx.dggl.model.Match;
import com.cidic.sdx.dggl.model.Share;
import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.service.ShareService;
import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.hpgl.model.ResultModel;
import com.cidic.sdx.util.ResponseCodeUtil;
import com.cidic.sdx.util.WebRequestUtil;

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
	
	@RequestMapping(value = "/createShare", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createFeedback(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam int matchId,@RequestParam String shareContent,@RequestParam String sharedlist) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Share share = new Share();
		Match match = new Match();
		match.setId(matchId);
		User user = new User();
		user.setId(userId);
		share.setUser(user);
		share.setMatch(match);
		share.setShareContent(shareContent);
		share.setSharedlist(sharedlist);
		
		int result = shareServiceImpl.createShare(share);
		if (result == ResponseCodeUtil.FEEDBACK_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else if (result == ResponseCodeUtil.FEEDBACK_OPERATION_EXIST) {
			resultModel.setResultCode(300); // 已经点赞
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}
}
