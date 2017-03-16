package com.cidic.sdx.realm;

import java.util.Optional;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.service.AppUserService;

public class AppUserRealm extends AuthorizingRealm {

	@Autowired
	@Qualifier(value = "appUserServiceImpl")
	private AppUserService appUserServiceImpl;
	
	private User successUser;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { //授权
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException { //认证
		
		String username = (String)token.getPrincipal();
		
		Optional<User> user = appUserServiceImpl.findByUsername(username);
        
        if(!user.isPresent()) {
            throw new UnknownAccountException();//没找到帐�?
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.get().getUsername(), //用户�?
                user.get().getPassword(), //密码
                ByteSource.Util.bytes(user.get().getCredentialsSalt()),
                getName()  //realm name
        );
        
        this.setSuccessUser(user.orElse(null));
        
        return authenticationInfo;
	}

	public User getSuccessUser() {
		return successUser;
	}

	public void setSuccessUser(User successUser) {
		this.successUser = successUser;
	}

	
}
