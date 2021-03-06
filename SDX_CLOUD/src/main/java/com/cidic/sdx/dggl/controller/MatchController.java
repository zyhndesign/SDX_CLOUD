package com.cidic.sdx.dggl.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cidic.sdx.dggl.model.Match;
import com.cidic.sdx.dggl.model.MatchListModel;
import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.service.AppUserService;
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

	@Autowired
	@Qualifier(value = "appUserServiceImpl")
	private AppUserService appUserServiceImpl;
	
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
	
	@RequestMapping(value = "/matchDetail/{id}", method = RequestMethod.GET)
	public ModelAndView pDetail(HttpServletRequest request, @PathVariable int id) {

		Match match = null;
		if (id > 0) {
			match = matchServiceImpl.findMatchByMatchId(id);
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("/detail/matchDetail");
		view.addObject("match", match);
		return view;
	}
	
	@RequestMapping(value = "/shareDetail/{id}", method = RequestMethod.GET)
	public ModelAndView shareDetail(HttpServletRequest request, @PathVariable int id) {

		Match match = null;
		if (id > 0) {
			match = matchServiceImpl.findMatchByMatchId(id);
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("/sharePage");
		view.addObject("match", match);
		return view;
	}
	
	
	@RequestMapping(value = "/getDataByMatchId", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getDataByMatchId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int id) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();

		resultModel.setResultCode(200);
		resultModel.setSuccess(true);
		resultModel.setObject(matchServiceImpl.findMatchByMatchId(id));
		return resultModel;
	}
	
	@RequestMapping(value = "/matchOfGuide/{id}", method = RequestMethod.GET)
    public ModelAndView matchOfGuide(HttpServletRequest request, @PathVariable int id) {

        User userModel = null;
        if (id > 0) {
            userModel = appUserServiceImpl.findUserById(id).get();
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("matchOfGuide");
        view.addObject("user", userModel);
        return view;
    }
	
	@RequestMapping(value = "/createMatch", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createMatch(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Match match) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		
		int result = matchServiceImpl.createMatch(match);
		if (result > 0) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(result);
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
	
	@RequestMapping(value = "/updateShareStatus", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateShareStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int matchId, @RequestParam int shareStatus) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = matchServiceImpl.updateShareStatus(matchId, shareStatus);
		if (result == ResponseCodeUtil.MATCH_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}
	
	@RequestMapping(value = "/updateDraftStatus", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateDraftStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int matchId, @RequestParam int draftStatus) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = matchServiceImpl.updateDraftStatus(matchId, draftStatus);
		if (result == ResponseCodeUtil.MATCH_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}
	
	@RequestMapping(value = "/updateBackStatus", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateBackStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int matchId, @RequestParam int backStatus) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = matchServiceImpl.updateBackStatus(matchId, backStatus);
		if (result == ResponseCodeUtil.MATCH_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}
	
	@RequestMapping(value = "/updateShareAndDraftStatus", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateShareAndDraftStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int matchId, @RequestParam int draftStatus, @RequestParam int shareStatus) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = matchServiceImpl.updateShareAndDraftStatus(matchId, shareStatus, draftStatus);
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
		 @RequestParam int shareStatus, @RequestParam(required=false) Integer userId, 
		 @RequestParam int iDisplayStart, @RequestParam int iDisplayLength,@RequestParam String sEcho){
		
		if (userId == null){
			userId = 0;
		}
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();
		try {
			MatchListModel matchListModel = matchServiceImpl.getMatchByShareStatus(userId, shareStatus, iDisplayStart, iDisplayLength);
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
    
	@RequestMapping(value = "/getAppMatchByDraftStatus", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getAppMatchByDraftStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam int draftStatus, @RequestParam int offset, @RequestParam int limit){
		
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ResultModel resultModel = new ResultModel();
		try {
		    List<Match> matchList = matchServiceImpl.getAppMatchByDraftStatus(userId, draftStatus, offset, limit);
		    resultModel.setObject(matchList);
			resultModel.setSuccess(true);
			resultModel.setResultCode(200);
		}
		catch (Exception e) {
			e.printStackTrace();
			resultModel.setSuccess(false);
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/getAppMatchByShareStatus", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getAppMatchByShareStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam int shareStatus, @RequestParam int offset, @RequestParam int limit){
		
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ResultModel resultModel = new ResultModel();
		try {
			List<Match> matchList = matchServiceImpl.getAppMatchByShareStatus(userId, shareStatus, offset, limit);
			for (Match match : matchList){
				match.setUser(null);
			}
			resultModel.setObject(matchList);
			resultModel.setSuccess(true);
			resultModel.setResultCode(200);
		}
		catch (Exception e) {
			e.printStackTrace();
			resultModel.setSuccess(false);
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/getAppMatchByBackStatus", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getAppMatchByBackStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam int backStatus, @RequestParam int offset, @RequestParam int limit){
		
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ResultModel resultModel = new ResultModel();
		try {
		    List<Match> matchList = matchServiceImpl.getAppMatchByBackStatus(userId, backStatus, offset, limit);
		    resultModel.setObject(matchList);
			resultModel.setSuccess(true);
			resultModel.setResultCode(200);
		}
		catch (Exception e) {
			e.printStackTrace();
			resultModel.setSuccess(false);
		}
		return resultModel;
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
	
	@RequestMapping(value = "/getStatisticsDataByWeek", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getStatisticsDataByWeek(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(required=false) Integer userId){
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ResultModel resultModel = new ResultModel();
		try {
			if (userId == null){
				userId = 0;
			}
		    Map<String,Integer> map = matchServiceImpl.getStatisticsDataByWeek(userId);
		    resultModel.setObject(map);
			resultModel.setSuccess(true);
			resultModel.setResultCode(200);
		}
		catch (Exception e) {
			e.printStackTrace();
			resultModel.setSuccess(false);
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/getStatisticsDataByMonth", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getStatisticsDataByMonth(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(required=false) Integer userId){
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ResultModel resultModel = new ResultModel();
		try {
			if (userId == null){
				userId = 0;
			}
			Map<String,Integer> map = matchServiceImpl.getStatisticsDataByMonth(userId);
		    resultModel.setObject(map);
			resultModel.setSuccess(true);
			resultModel.setResultCode(200);
		}
		catch (Exception e) {
			e.printStackTrace();
			resultModel.setSuccess(false);
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/getStatisticsDataByYear", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getStatisticsDataByYear(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(required=false) Integer userId){
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ResultModel resultModel = new ResultModel();
		try {
			if (userId == null){
				userId = 0;
			}
			Map<String,Integer> map = matchServiceImpl.getStatisticsDataByYear(userId);
		    resultModel.setObject(map);
			resultModel.setSuccess(true);
			resultModel.setResultCode(200);
		}
		catch (Exception e) {
			e.printStackTrace();
			resultModel.setSuccess(false);
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/getPushHistoryByVipNameAndUserId", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getPushHistoryByVipNameAndUserId(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam int userId, @RequestParam String vipName, @RequestParam int limit, @RequestParam int offset){
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ResultModel resultModel = new ResultModel();
		try {
		    List<Match> matchList = matchServiceImpl.getMatchByPushHistory(vipName, userId, limit, offset);
		    resultModel.setObject(matchList);
			resultModel.setSuccess(true);
			resultModel.setResultCode(200);
		}
		catch (Exception e) {
			e.printStackTrace();
			resultModel.setSuccess(false);
		}
		return resultModel;
	}
}
