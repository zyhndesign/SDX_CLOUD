package com.cidic.sdx.dggl.dao;

import java.util.List;
import java.util.Optional;

import com.cidic.sdx.dggl.model.Shop;
import com.cidic.sdx.dggl.model.Vipuser;

public interface VipUserDao {

	public void createVipUser(Vipuser user);
	
    public void updateVipUser(Vipuser user);
    
    public void deleteVipUser(int userId);
    
    //用于app加载当前导购的客户
    public List<Vipuser> getVipuserByShoppingGuideId(int shoppingGuideId);
    
    //用于后台管理VIP客户
    public List<Vipuser> getVipuserByPage(int limit, int offset);
    
    public int getVipuserCount();
    
    //根据卡号查询Vip信息
    public Optional<Vipuser> getVipuserByCardNumber(String cardNumber);
    
    //统计当前导购有多少客户
    public int getCountVipuserByShoppingGuideId(int shoppingGuideId);
    
    public Vipuser loadVipCustomerById(int id);
}
