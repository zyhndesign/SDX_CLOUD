package com.cidic.sdx.dggl.controller;

import java.util.Date;
import java.util.List;

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

import com.cidic.sdx.dggl.model.Feedback;
import com.cidic.sdx.dggl.model.HotMatchModel;
import com.cidic.sdx.dggl.model.Match;
import com.cidic.sdx.dggl.model.Matchlist;
import com.cidic.sdx.dggl.service.FeedbackService;
import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.hpgl.model.ResultModel;
import com.cidic.sdx.util.ResponseCodeUtil;
import com.cidic.sdx.util.WebRequestUtil;

@Controller
@RequestMapping("/dggl/feedback")
public class FeedbackController {

	@Autowired
	@Qualifier(value = "feedbackServiceImpl")
	private FeedbackService feedbackServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(SdxException.class)
	public @ResponseBody ResultModel handleCustomException(SdxException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}

	@RequestMapping(value = "/createFeedback", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createFeedback(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam int matchId) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Feedback feedback = new Feedback();
		feedback.setUserId(userId);
		Match match = new Match();
		match.setId(matchId);
		feedback.setMatch(match);
		feedback.setCreatetime(new Date());
		
		int result = feedbackServiceImpl.createFeedback(feedback);
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

	@RequestMapping(value = "/deleteFeedback", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteFeedback(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam int matchId) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Feedback feedback = new Feedback();
		feedback.setUserId(userId);
		Match match = new Match();
		match.setId(matchId);
		feedback.setMatch(match);

		int result = feedbackServiceImpl.updateFeedback(feedback);
		if (result == ResponseCodeUtil.FEEDBACK_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}

	@RequestMapping(value = "/getDataByUserId", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getDataByUserId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam int limit, @RequestParam int offset) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();

		try {

			List<HotMatchModel> feedBackList = feedbackServiceImpl.getFeedbackListPageByUserId(userId, limit, offset);

			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(feedBackList);
		}
		catch (Exception e) {
			e.printStackTrace();
			resultModel.setSuccess(false);
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/getTopThreeDataByUserId", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getTopThreeDataByUserId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();

		try {
			List<HotMatchModel> feedBackList = feedbackServiceImpl.getTopThreeDataByUserId(userId);

			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(feedBackList);
		}
		catch (Exception e) {
			e.printStackTrace();
			resultModel.setSuccess(false);
		}
		return resultModel;
		
	}
}
