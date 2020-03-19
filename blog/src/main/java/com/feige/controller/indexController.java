package com.feige.controller;

import com.feige.dao.BlogMapper;
import com.feige.pojo.Blog;
import com.feige.service.BlogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class indexController {

    @Autowired
    BlogService blogService;

    @Autowired
    private BlogMapper blogMapper;

    @GetMapping({"/","/index"})
    public String index(){
        return "views/index";
    }
    @GetMapping("/toLogin")
    public String toLogin(){
        return "views/user/login";
    }
    @RequestMapping("/login")
    @ResponseBody
    public String login(String username, String password, @RequestParam(value="rememberMe", defaultValue="0") Integer rememberMe){
        String msg;
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        if(rememberMe==1){
            token.setRememberMe(true);
        }
        try {
            subject.login(token);//执行登录方法
            Session session = subject.getSession();
            session.setAttribute("loginUser",username);
            msg = "SUCCESS";
            return msg;
        }catch (UnknownAccountException e){
            //model.addAttribute("msg","用户名不存在");
            //System.out.println("用户名不存在");
            msg = "用户名不存在";
            return msg;
        }catch (IncorrectCredentialsException e){
            //model.addAttribute("msg","密码错误");
            //System.out.println("密码错误");
            msg = "密码错误";
            return msg;
        }
    }
    @GetMapping("/noPermission")
    @ResponseBody
    public String noPermission(){
        return "你没有权限访问该页面";
    }
    @GetMapping("/users")
    public String users(){
        return "views/list/users";
    }
    @GetMapping("/roles")
    public String roles(){
        return "views/list/roles";
    }
    @GetMapping("/admins")
    public String admins(){
        return "views/list/admins";
    }
    @GetMapping("/blogs")
    public String blogs(){
        return "views/list/blogs";
    }
    @GetMapping("/types")
    public String types(){
        return "views/list/types";
    }
    @GetMapping("/comments")
    public String comments(){
        return "views/list/comments";
    }
    @GetMapping("/addBlog")
    public String addBlog(){
        return "views/form/addBlog";
    }
    @RequestMapping("/updateBlog")
    public String updateBlog(Integer id, Model model){
        Blog blog = blogMapper.getBlog(id);
        model.addAttribute("content",blog.getContent());
        return "views/form/updateBlog";
    }
}
