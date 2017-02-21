package com.cidic.sdx.dggl.service;

import com.cidic.sdx.dggl.model.User;

public interface AppUserService {

	public void createUser(User user);
	
    public void updateUser(User user);
    
    public void deleteUser(Long userId);
    
    User findByUsername(String username);
}
