package com.feige.config;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {


    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        /*
        添加shiro的内置过滤器
        anon:无需认证就可以访问
        authc:必须认证才可以访问
        user:记住我功能才能用
        perms：拥有对某个资源的权限才可以访问perms[用户:权限]
        role：拥有某个角色权限才可以访问

         */
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        //添加权限
        linkedHashMap.put("/api/user/get_users","perms[user:select]");
        linkedHashMap.put("/api/user/delete_user/*","perms[user:delete]");
        linkedHashMap.put("/api/user/update_user/*","perms[user:update]");
        linkedHashMap.put("/api/user/add_user","perms[user:insert]");
        //博客表的权限
        //linkedHashMap.put("/api/blog/get_blogs","perms[blog:select]");
        linkedHashMap.put("/api/blog/delete_blog/*","perms[blog:delete]");
        linkedHashMap.put("/api/blog/update_blog/*","perms[blog:update]");
        linkedHashMap.put("/api/blog/add_blog","perms[blog:insert]");
        //类别表的权限
        //linkedHashMap.put("/api/type/get_types","perms[type:select]");
        linkedHashMap.put("/api/type/delete_type/*","perms[type:delete]");
        linkedHashMap.put("/api/type/update_type/*","perms[type:update]");
        linkedHashMap.put("/api/type/add_type","perms[type:insert]");
        //角色表的权限
        linkedHashMap.put("/api/role/get_roles","perms[role:select]");
        linkedHashMap.put("/api/role/delete_role/*","perms[role:delete]");
        linkedHashMap.put("/api/role/update_role/*","perms[role:update]");
        linkedHashMap.put("/api/role/add_role","perms[role:insert]");
        //评论表的权限
        //linkedHashMap.put("/api/comment/get_comments","perms[comment:select]");
        //linkedHashMap.put("/api/comment/get_all_comment","perms[comment:select]");
        //linkedHashMap.put("/api/comment/add_comment","perms[comment:insert]");
        linkedHashMap.put("/api/comment/delete_comment/*","perms[comment:delete]");
        //权限的授予和收回
        linkedHashMap.put("/api/permission/get_permissions/*","perms[permission:select]");
        linkedHashMap.put("/api/permission/add_permission","perms[permission:insert]");
        linkedHashMap.put("/api/permission/delete_permission/*/*","perms[permission:delete]");
        //支持通配符linkedHashMap.put("/*","authc");
        bean.setFilterChainDefinitionMap(linkedHashMap);
        //设置登录请求
        bean.setLoginUrl("/login");
        //未授权请求
        bean.setUnauthorizedUrl("/no_permission");
        //bean.setSuccessUrl("/index");
        return bean;
    }
    //DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    //创建realm对象，需要自定义
    @Bean(name = "userRealm")//被spring托管
    public UserRealm userRealm(){
        return new UserRealm();
    }
    /**
     * cookie对象
     * @return
     */
    private SimpleCookie rememberMeCookie() {
        // 设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberMe"/>
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 设置cookie的过期时间，单位为秒，这里为一天
        cookie.setMaxAge(86400);
        return cookie;
    }

    /**
     * cookie管理对象
     * @return
     */
    private CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe cookie加密的密钥
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }
}
