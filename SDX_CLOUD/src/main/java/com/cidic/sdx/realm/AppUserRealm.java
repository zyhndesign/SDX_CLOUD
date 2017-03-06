package com.cidic.sdx.realm;

import java.util.Optional;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
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
import com.cidic.sdx.hpgl.model.UserModel;

public class AppUserRealm extends AuthorizingRealm {

	@Autowired
	@Qualifier(value = "appUserServiceImpl")
	private AppUserService appUserServiceImpl;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { //授权
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException { //认证
		
		String username = (String)token.getPrincipal();
		
		System.out.println("**************************************************************");
        
		Optional<User> user = appUserServiceImpl.findByUsername(username);
        
        if(!user.isPresent()) {
            throw new UnknownAccountException();//没找到帐�?
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实�?
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.get().getUsername(), //用户�?
                user.get().getPassword(), //密码
                ByteSource.Util.bytes(user.get().getCredentialsSalt()),
                getName()  //realm name
        );
        return authenticationInfo;
	}

}
