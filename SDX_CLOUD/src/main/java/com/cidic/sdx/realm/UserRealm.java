package com.cidic.sdx.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cidic.sdx.model.UserModel;
import com.cidic.sdx.service.UserService;


public class UserRealm extends AuthorizingRealm{

	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userServiceImpl;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userServiceImpl.findRoles(username));
        authorizationInfo.setStringPermissions(userServiceImpl.findPermissions(username));

        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		
        UserModel user = userServiceImpl.findByUsername(username);
        
        if(user == null) {
            throw new UnknownAccountException();//没找到帐�?
        }

        if(Boolean.TRUE.equals(user.isLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实�?
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户�?
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()  //realm name
        );
        return authenticationInfo;
	}

}
