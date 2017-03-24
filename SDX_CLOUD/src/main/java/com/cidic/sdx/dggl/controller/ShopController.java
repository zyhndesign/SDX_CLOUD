package com.cidic.sdx.dggl.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cidic.sdx.dggl.model.Shop;
import com.cidic.sdx.dggl.model.ShopListModel;
import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.service.ShopService;
import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.hpgl.controller.BrandSettingController;
import com.cidic.sdx.hpgl.model.ListResultModel;
import com.cidic.sdx.hpgl.model.ResultModel;
import com.cidic.sdx.util.ResponseCodeUtil;
import com.cidic.sdx.util.WebRequestUtil;

@Controller
@RequestMapping("/dggl/shop")
public class ShopController {

	private static final Logger logger = LoggerFactory.getLogger(BrandSettingController.class);

	@Autowired
	@Qualifier(value = "shopServiceImpl")
	private ShopService shopServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(SdxException.class)
	public @ResponseBody ResultModel handleCustomException(SdxException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}

	@RequestMapping(value = "/shopMgr", method = RequestMethod.GET)
	public String shopMgr(Locale locale, Model model) {
		return "shopMgr";
	}
	
	@RequestMapping(value = "/shopCOU", method = RequestMethod.GET)
	public ModelAndView shopCOU(Locale locale, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shopCOU");
		return modelAndView;
	}
	
	@RequestMapping(value = "/shopCOU/{id}", method = RequestMethod.GET)
    public ModelAndView updateShopCOU(HttpServletRequest request, @PathVariable int id) {

        Shop shop = null;
        if (id > 0) {
            shop = shopServiceImpl.loadShopById(id);
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("shopCOU");
        view.addObject("shop", shop);
        return view;
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createShop(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Shop shop) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = shopServiceImpl.createShop(shop);
		if (result == ResponseCodeUtil.SHOP_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateShop(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Shop shop) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = shopServiceImpl.updateShop(shop);
		if (result == ResponseCodeUtil.SHOP_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteShop(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int shopId) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Shop shop = new Shop();
		shop.setId(shopId);
		int result = shopServiceImpl.deleteShop(shop);
		if (result == ResponseCodeUtil.SHOP_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new SdxException(500, "操作失败");
		}
	}
	
	@RequestMapping(value = "/getData", method = RequestMethod.POST)
	@ResponseBody
	public ListResultModel getData(HttpServletRequest request, HttpServletResponse response, @RequestParam int iDisplayLength, @RequestParam int iDisplayStart,@RequestParam String sEcho) {

		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();
		try {
			ShopListModel shopListModel = shopServiceImpl.getShopList(iDisplayStart, iDisplayLength);
			listResultModel.setAaData(shopListModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords((int)shopListModel.getCount().longValue());
			listResultModel.setiTotalDisplayRecords((int)shopListModel.getCount().longValue());
			listResultModel.setSuccess(true);
		}
		catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
}
