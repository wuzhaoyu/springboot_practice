package com.shiro;

import com.entity.Permissions;
import com.entity.Role;
import com.entity.User;
import com.wzy.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * 类功能说明:   配置校验规则 用户，角色，权限
 * 类修改者	创建日期2020/10/23
 * 修改说明
 *
 * @author com.com.com.wzy
 * @version V1.0
 * @description 说明：
 **/
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    /**
     * 权限配置类
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户名
        String loginName = (String)principalCollection.getPrimaryPrincipal();
        // 查询用户信息 角色 权限
        User user = loginService.getUserByName(loginName);
        // 添加至shiro配置类
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permissions permission : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permission.getPermissionsName());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 权限认证类
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这个地方的认证信息
        if(!StringUtils.hasLength((String)authenticationToken.getPrincipal())){
            return null;
        }
        String userName = authenticationToken.getPrincipal().toString();
        User user = loginService.getUserByName(userName);
        if(user == null ){
            return null;
        }else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName,user.getPassword(),getName());
            return simpleAuthenticationInfo;
        }

    }
}
