package com.cidic.sdx.dggl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.model.UserListModel;
import com.cidic.sdx.dggl.service.AppUserService;
import com.cidic.sdx.util.ResponseCodeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserTest {

	@Autowired
	@Qualifier("appUserServiceImpl")
	private AppUserService appUserServiceImpl;
	
	//@Test
	public void createUser(){
		User user = new User("liling", "111111", "http://icon.aliyun.com/asdasdasdasdasdasd", new Date());
		int createResult = appUserServiceImpl.createUser(user);
		switch (createResult){
			case ResponseCodeUtil.UESR_CREATE_EXIST:
				System.out.println("用户已经存在！");
				break;
			case ResponseCodeUtil.UESR_OPERATION_SUCESS:
				System.out.println("创建用户成功！");
				break;
			case ResponseCodeUtil.UESR_OPERATION_FAILURE:
				System.out.println("创建用户失败！");
				break;
			default:
				System.out.println("其他原因！");
		}
	}
	
	//@Test
	public void deleteUser(){
		
		int deleteResult = appUserServiceImpl.deleteUser(1);
		if (deleteResult == ResponseCodeUtil.UESR_OPERATION_SUCESS){
			System.out.println("删除操作成功!");
		}
		else if (deleteResult == ResponseCodeUtil.UESR_OPERATION_FAILURE){
			System.out.println("删除操作失败！");
		}
	}
	
	//@Test
	public void updateUser(){
		User user = new User();
		user.setId(6);
		user.setPassword("111111");
		int updateResult = appUserServiceImpl.updateUser(user);
		if (updateResult == ResponseCodeUtil.UESR_OPERATION_SUCESS){
			System.out.println("更新操作成功!");
		}
		else if (updateResult == ResponseCodeUtil.UESR_OPERATION_FAILURE){
			System.out.println("更新操作失败！");
		}
	}
	
	//@Test
	public void findUser(){
		Optional<User> user1 = appUserServiceImpl.findByUsername("jack bauer");
		user1.ifPresent((optUser)->{System.out.println(optUser.getUsername());});
		Optional<User> user2 = appUserServiceImpl.findByUsername("jack");
		user2.ifPresent((optUser)->{System.out.println(optUser.getUsername());});
	}
	
	//@Test
	public void getUserByPage(){
		UserListModel list = appUserServiceImpl.getUserListByPage(10, 0);
		for (User user : list.getList()){
			System.out.println(user.getUsername());
		}
	}
	
	@Test
	public void getUserByCondition(){
		UserListModel list = appUserServiceImpl.getUserListByPage(10, 0);
		for (User user : list.getList()){
			System.out.println(user.getUsername());
			System.out.println(user.getShop().getShopname());
		}
	}
}
