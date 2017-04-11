package com.cidic.sdx.dggl.controller;

import java.util.List;
import java.util.Locale;
import java.util.Set;

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
import com.cidic.sdx.dggl.model.Matchlist;
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
		Set<Matchlist> matchlist = match.getMatchlists();
		for (Matchlist m : matchlist ){
			System.out.println(m.getModelurl());
		}
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
	public ListResultModel getAppMatchByDraftStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam int draftStatus, @RequestParam int offset, @RequestParam int limit){
		
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();
		try {
		    List<Match> matchList = matchServiceImpl.getAppMatchByDraftStatus(userId, draftStatus, offset, limit);
			listResultModel.setAaData(matchList);
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
			@RequestParam int userId, @RequestParam int shareStatus, @RequestParam int offset, @RequestParam int limit){
		
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();
		try {
			List<Match> matchList = matchServiceImpl.getAppMatchByShareStatus(userId, shareStatus, offset, limit);
			listResultModel.setAaData(matchList);
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
