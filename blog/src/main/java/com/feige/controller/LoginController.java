package com.feige.controller;

import com.feige.common.constants.Constants;
import com.feige.common.utils.ResultAjax;
import com.feige.common.utils.StringUtils;
import com.feige.common.utils.redis.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Api(tags = "登录接口")
@RestController
public class LoginController {
    @Autowired
    RedisCache redisCache;


    @ApiOperation(value = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true),
            @ApiImplicitParam(name = "rememberMe",value = "记住我"),
            @ApiImplicitParam(name = "permission",value = "该空不填")
    })
    @PostMapping("/login")
    public ResultAjax login(String username, String password, @RequestParam(value="rememberMe", defaultValue="0") Integer rememberMe,String permission){
        if (!StringUtils.isEmpty(permission)){
            return ResultAjax.error(Constants.NO_PERMISSION);
        }
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        if(rememberMe==1){
            token.setRememberMe(true);
        }
        try {
            subject.login(token);//执行登录方法
            redisCache.setCacheObject(Constants.LOGIN_USER_KEY,token,30, TimeUnit.MINUTES);
            //Session session = subject.getSession();
            //session.setAttribute("loginUser",username);
            return ResultAjax.success();
        }catch (UnknownAccountException e){
            //model.addAttribute("msg","用户名不存在");
            //System.out.println("用户名不存在");
            return ResultAjax.error(Constants.USERNAME_NO_EXIST);
        }catch (IncorrectCredentialsException e){
            //model.addAttribute("msg","密码错误");
            //System.out.println("密码错误");
            return ResultAjax.error(Constants.PASSWORD_ERROR);
        }
    }
}
