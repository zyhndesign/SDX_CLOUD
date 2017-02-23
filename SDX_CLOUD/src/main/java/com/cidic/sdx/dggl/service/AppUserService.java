package com.cidic.sdx.dggl.service;

import java.util.List;
import java.util.Optional;

import com.cidic.sdx.dggl.model.User;

public interface AppUserService {

	public int createUser(User user);
	
    public int updateUser(User user);
    
    public int deleteUser(int userId);
    
    Optional<User> findByUsername(String username);
    
    Optional<User> authorityCheck(String username, String password);
    
    public List<User> getUserListByPage(int limit, int offset);
}
