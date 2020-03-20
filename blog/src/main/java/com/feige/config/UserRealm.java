package com.feige.config;

import com.feige.common.utils.SelectParam;
import com.feige.pojo.Role;
import com.feige.pojo.User;
import com.feige.service.PermissionService;
import com.feige.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;


public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //System.out.println("执行了doGetAuthorizationInfo方法");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //从数据库中获取用户的权限
        Subject subject = SecurityUtils.getSubject();
        //获取从SimpleAuthenticationInfo传上来的username
        String username = (String) subject.getPrincipal();
        //获取该用户的所有权限
        //Admin admin = adminService.getAdmin(username);
        //把该用户的所有权限封装成一个set集合
        HashSet<String> roles = new HashSet<>();
        for (Role role : permissionService.getPermissions(new SelectParam(username))) {
            roles.add(role.getPermission());
        }
        //给用户授权
        info.addStringPermissions(roles);
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //System.out.println("执行了doGetAuthenticationInfo方法");
        //用户名，密码~ 数据中取
        UsernamePasswordToken usernamePassword = (UsernamePasswordToken)authenticationToken;
        //System.out.println(usernamePassword.getUsername());
        User user = userService.getUser(usernamePassword.getUsername());
        //System.out.println(user);
        if(user==null){
            return null;//抛出异常
        }else {

            //密码认证，shiro做
            //可以加密md5
            //第一个参数不能直接传对象，回报不能序列化错误
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
        }

    }
}
