package com.cidic.sdx.dggl.service;

import java.util.List;
import java.util.Optional;

import com.cidic.sdx.dggl.model.Vipuser;
import com.cidic.sdx.dggl.model.VipuserModel;


public interface VipUserService {

	public int createVipUser(Vipuser user);
	
    public int updateVipUser(Vipuser user);
    
    public int deleteVipUser(int userId);
    
    //用于app加载当前导购的客户
    public List<Vipuser> getVipuserByShoppingGuideId(int shoppingGuideId);
    
    //用于后台管理VIP客户
    public VipuserModel getVipuserByPage(int limit, int offset);
    
    //根据卡号查询Vip信息
    public Optional<Vipuser> getVipuserByCardNumber(String cardNumber);
}
