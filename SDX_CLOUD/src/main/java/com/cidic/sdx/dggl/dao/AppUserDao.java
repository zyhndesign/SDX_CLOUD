package com.cidic.sdx.dggl.dao;

import java.util.List;
import java.util.Optional;

import com.cidic.sdx.dggl.model.User;

public interface AppUserDao {

	public void createUser(User user);
	
    public void updateUser(User user);
    
    public void deleteUser(int userId);
    
    Optional<User> findByUsername(String username);
    
    Optional<User> authorityCheck(String username, String password);
    
    public List<User> getUserListByPage(int limit, int offset);
    
    public Long getUserCount();
    
    public Optional<User> findUserById(int userId);
    
    public List<User> findUserByShopIdAndUsername(int shopId, String username,int iDisplayStart,int iDisplayLength);
    
    public Long getUserCountByCondition(int shopId, String username);
    
    public void updatePwd(String serialnumber, String password, String slot);
    
    public void disableAccount(int userId);
    
    public void enableAccount(int userId);
}
