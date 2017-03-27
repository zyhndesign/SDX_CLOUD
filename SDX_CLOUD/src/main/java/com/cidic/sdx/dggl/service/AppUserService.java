package com.cidic.sdx.dggl.service;

import java.util.Optional;

import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.model.UserListModel;

public interface AppUserService {

	public int createUser(User user);
	
    public int updateUser(User user);
    
    public int deleteUser(int userId);
    
    Optional<User> findByUsername(String username);
    
    Optional<User> authorityCheck(String username, String password);
    
    public UserListModel getUserListByPage(int limit, int offset);
    
    public Optional<User> findUserById(int userId);
    
    public UserListModel getUserListByCondition(int shopId, String username, int iDisplayStart, int iDisplayLength);
    
    public int updatePwd(String serialnumber, String password);
    
    public int disableAccount(int userId);
    
    public int enableAccount(int userId);
}
