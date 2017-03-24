package com.cidic.sdx.dggl.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.sdx.dggl.model.Match;
import com.cidic.sdx.dggl.model.MatchListModel;
import com.cidic.sdx.dggl.service.MatchService;
import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.hpgl.model.ListResultModel;
import com.cidic.sdx.hpgl.model.ResultModel;
import com.cidic.sdx.util.ResponseCodeUtil;
import com.cidic.sdx.util.WebRequestUtil;

@Controller
@RequestMapping("/dggl/match")
public class MatchController {

	@Autowired
	@Qualifier(value = "matchServiceImpl")
	private MatchService matchServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(SdxException.class)
	public @ResponseBody ResultModel handleCustomException(SdxException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	@RequestMapping(value = "/matchMgr", method = RequestMethod.GET)
	public String matchMgr(Locale locale, Model model) {
		return "matchMgr";
	}
	
	@RequestMapping(value = "/matchOfGuide", method = RequestMethod.GET)
	public String matchOfGuide(Locale locale, Model model) {
		return "matchOfGuide";
	}
	
	@RequestMapping(value = "/createMatch", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createMatch(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Match match) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = matchServiceImpl.createMatch(match);
		if (result == ResponseCodeUtil.MATCH_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}
	
	@RequestMapping(value = "/updateMatch", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateMatch(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Match match) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = matchServiceImpl.updateMatch(match);
		if (result == ResponseCodeUtil.MATCH_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}
	
	@RequestMapping(value = "/deleteMatch", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteMatch(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int matchId) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Match match = new Match();
		match.setId(matchId);
		int result = matchServiceImpl.deleteMatch(matchId);
		if (result == ResponseCodeUtil.MATCH_OPERATION_SUCCESS) {
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

		List<Match> feedBackList = matchServiceImpl.findMatchByUser(userId, offset, limit);

		resultModel.setResultCode(200);
		resultModel.setSuccess(true);
		resultModel.setObject(feedBackList);
		return resultModel;
	}
	
	@RequestMapping(value = "/getMatchByShareStatus", method = RequestMethod.POST)
	@ResponseBody
	public ListResultModel getMatchByShareStatus(HttpServletRequest request, HttpServletResponse response,
		 @RequestParam int shareStatus,@RequestParam(required=false) int userId, 
		 @RequestParam int iDisplayStart, @RequestParam int iDisplayLength,@RequestParam String sEcho){
		
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();
		try {
			MatchListModel matchListModel = matchServiceImpl.getMatchByShareStatus(userId,shareStatus, iDisplayStart, iDisplayLength);
			listResultModel.setAaData(matchListModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(matchListModel.getCount());
			listResultModel.setiTotalDisplayRecords(matchListModel.getCount());
			listResultModel.setSuccess(true);
		}
		catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
    
	@RequestMapping(value = "/getAppMatchByShareStatus", method = RequestMethod.POST)
	@ResponseBody
	public ListResultModel getAppMatchByShareStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam int shareStatus, @RequestParam int iDisplayStart, @RequestParam int iDisplayLength,@RequestParam String sEcho){
		
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();
		try {
			MatchListModel matchListModel = matchServiceImpl.getAppMatchByShareStatus(userId, shareStatus, iDisplayStart, iDisplayLength);
			listResultModel.setAaData(matchListModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(matchListModel.getCount());
			listResultModel.setiTotalDisplayRecords(matchListModel.getCount());
			listResultModel.setSuccess(true);
		}
		catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	@RequestMapping(value = "/getMatchByDataStatus", method = RequestMethod.POST)
	@ResponseBody
    public ResultModel getMatchByDataStatus(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam int userId, @RequestParam int dataStatus, @RequestParam int iDisplayStart, @RequestParam int iDisplayLength,@RequestParam String sEcho){
    	WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();

		List<Match> matchList = matchServiceImpl.getMatchByDataStatus(userId, dataStatus, iDisplayStart, iDisplayLength);

		resultModel.setResultCode(200);
		resultModel.setSuccess(true);
		resultModel.setObject(matchList);
		return resultModel;
		
		
    }
}
