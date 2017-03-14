package com.cidic.sdx.dggl.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dggl.dao.VipUserDao;
import com.cidic.sdx.dggl.model.Vipuser;
import com.cidic.sdx.dggl.service.VipUserService;
import com.cidic.sdx.util.ResponseCodeUtil;

@Repository
@Component
@Qualifier(value = "vipUserServiceImpl")
@Transactional
public class VipUserServiceImpl implements VipUserService {

	@Autowired
	@Qualifier("vipUserDaoImpl")
	private VipUserDao vipUserDaoImpl;
	
	@Override
	public int createVipUser(Vipuser user) {
		try{
			vipUserDaoImpl.createVipUser(user);
			return ResponseCodeUtil.VIP_UESR_OPERATION_SUCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.VIP_UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateVipUser(Vipuser user) {
		try{
			vipUserDaoImpl.updateVipUser(user);
			return ResponseCodeUtil.VIP_UESR_OPERATION_SUCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.VIP_UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteVipUser(int userId) {
		try{
			vipUserDaoImpl.deleteVipUser(userId);
			return ResponseCodeUtil.VIP_UESR_OPERATION_SUCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.VIP_UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public List<Vipuser> getVipuserByShoppingGuideId(int shoppingGuideId) {

		try{
			return vipUserDaoImpl.getVipuserByShoppingGuideId(shoppingGuideId);
		}
		catch(Exception e){
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<Vipuser> getVipuserByPage(int limit, int offset) {

		try{
			return vipUserDaoImpl.getVipuserByPage(limit, offset);
		}
		catch(Exception e){
			
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public Optional<Vipuser> getVipuserByCardNumber(String cardNumber) {

		try{
			return vipUserDaoImpl.getVipuserByCardNumber(cardNumber);
		}
		catch(Exception e){
			
			e.printStackTrace();
			return Optional.empty();
		}
	}

}
