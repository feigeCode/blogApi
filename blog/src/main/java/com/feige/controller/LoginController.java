package com.feige.controller;

import com.feige.common.constants.Constants;
import com.feige.common.utils.ResultAjax;
import com.feige.common.utils.SelectParam;
import com.feige.common.utils.StringUtils;
import com.feige.common.utils.redis.RedisCache;
import com.feige.pojo.LoginUser;
import com.feige.pojo.Role;
import com.feige.service.PermissionService;
import com.feige.service.TokenService;
import com.feige.service.UserService;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Api(tags = "登录接口")
@RestController
public class LoginController {
    @Autowired
    RedisCache redisCache;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserService userService;

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true),
            @ApiImplicitParam(name = "rememberMe",value = "记住我"),
            @ApiImplicitParam(name = "permission",value = "该空不填"),
            @ApiImplicitParam(name = "code",value = "验证码",required = true),
            @ApiImplicitParam(name = "uuid",value = "UUID",required = true),
    })
    @PostMapping("/login")
    public ResultAjax login(String username, String password, @RequestParam(value="rememberMe", defaultValue="0") Integer rememberMe,String permission,String uuid,String code){
        if (!StringUtils.isEmpty(permission)){
            return ResultAjax.error(Constants.NO_PERMISSION);
        }
        //验证码的key值
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        //把验证码从redis中取出
        String captcha = redisCache.getCacheObject(verifyKey);
        //删除redis中的验证码
        redisCache.deleteObject(verifyKey);
        if (captcha == null){
            return ResultAjax.error(Constants.CAPTCHA_EXPIRED);
        } else if(!code.equalsIgnoreCase(captcha)){
            return ResultAjax.error(Constants.CAPTCHA_ERROR);
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
            Set<String> set = new HashSet<>();
            List<Role> permissions = permissionService.getPermissions(new SelectParam(username));
            for (Role role : permissions) {
                set.add(role.getRoleName());
            }
            LoginUser loginUser = new LoginUser(userService.getUser(username), set);
            tokenService.createToken(loginUser);
            //Session session = subject.getSession();
            //session.setAttribute("loginUser",username);
            ResultAjax ajax = ResultAjax.success();
            ajax.put(Constants.TOKEN,loginUser);
            return ajax;
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
