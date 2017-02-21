package com.cidic.sdx.dggl.dao;

import com.cidic.sdx.dggl.model.User;

public interface AppUserDao {

	public void createUser(User user);
	
    public void updateUser(User user);
    
    public void deleteUser(Long userId);
    
    User findByUsername(String username);
}
