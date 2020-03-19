package com.feige.controller;


import com.feige.common.constants.Constants;
import com.feige.common.utils.ResultAjax;
import com.feige.common.utils.SelectParam;
import com.feige.common.utils.StringUtils;
import com.feige.common.utils.redis.TimeFormatter;
import com.feige.pojo.User;
import com.feige.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户的增删改查接口")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 按条数查询用户
     * @param page
     * @param limit
     * @param searchContent
     * @return
     */
    @ApiOperation(value = "按条数查询用户")
    @GetMapping("/getUsers")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true),
            @ApiImplicitParam(name = "limit",value = "每页条数",required = true),
            @ApiImplicitParam(name = "searchContent",value = "搜索内容")
    })
    public ResultAjax getUsers(Integer page,Integer limit,String searchContent){
        List<User> list = userService.getUsers(new SelectParam(page,limit,searchContent));
        int userCount = userService.getCount(searchContent);
        return ResultAjax.success(list,userCount);
    }
    @ApiOperation(value = "增加一个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "headPhoto",value = "头像",required = true),
            @ApiImplicitParam(name = "username",value = "用户名",required = true),
            @ApiImplicitParam(name = "email",value = "邮箱",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true),
            @ApiImplicitParam(name = "sex",value = "性别",required = true),
            @ApiImplicitParam(name = "hobby",value = "爱好",required = true),
            @ApiImplicitParam(name = "selfIntroduce",value = "自我介绍",required = true),
            @ApiImplicitParam(name = "other",value = "其他",required = true),
    })
    @PostMapping("/addUser")
    public ResultAjax addUser(String  headPhoto,String username,String email,String password,Integer sex,String hobby,String selfIntroduce,String other){

        User user = userService.getUser(username);
        if(StringUtils.isNull(user)){
            int  add = userService.addUser(new User(0,headPhoto,username,email,password,sex, TimeFormatter.timeFormatter(),hobby,selfIntroduce,other));
            if(add == 1){
                return ResultAjax.success();
            } else {
                return ResultAjax.error();
            }
        }else {
            return ResultAjax.error(Constants.EXIST);
        }
    }

    /**
     * 删除一个用户
     * @param username
     * @return
     */
    @ApiOperation(value = "删除一个用户")
    @ApiImplicitParam(name = "username",value = "用户名",required = true)
    @DeleteMapping("/deleteUser/{username}")
    public ResultAjax deleteUser(@PathVariable("username") String username){
        int delete = userService.deleteUser(username);
        if(delete == 1){
            return ResultAjax.success();
        } else {
            return ResultAjax.error();
        }

    }

    /**
     * 更新一个用户
     * @param id
     * @param headPhoto
     * @param username
     * @param email
     * @param password
     * @param sex
     * @param hobby
     * @param selfIntroduce
     * @param other
     * @param time
     * @return
     */
    @ApiOperation(value = "更新一个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "headPhoto",value = "头像",required = true),
            @ApiImplicitParam(name = "username",value = "用户名",required = true),
            @ApiImplicitParam(name = "email",value = "邮箱",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true),
            @ApiImplicitParam(name = "sex",value = "性别",required = true),
            @ApiImplicitParam(name = "hobby",value = "爱好",required = true),
            @ApiImplicitParam(name = "selfIntroduce",value = "自我介绍",required = true),
            @ApiImplicitParam(name = "other",value = "其他",required = true),
            @ApiImplicitParam(name = "id",value = "ID",required = true),
            @ApiImplicitParam(name = "time",value = "时间",required = true),
    })
    @PutMapping("/updateUser/{id}")
    public ResultAjax updateUser(@PathVariable("id") Integer id,String  headPhoto,String username,String email,String password,Integer sex,String hobby,String selfIntroduce,String other,String time){
        /**
         * 用户名是唯一的
         * true false 用户没有改变原来的用户名
         * true true    用户没有改变原来的用户名
         * false true   用户修改了用户名，但是该用户名没有和其他用户的相同
         * false false  用户修改了用户名，但是该用户名和其他用户的相同
         */
        if (userService.getUserById(id).getUsername().equals(username) || StringUtils.isNull(userService.getUser(username))){
            User user = new User(id,headPhoto,username,email,password,sex,time,hobby,selfIntroduce,other);
            int update = userService.updateUser(user);
            if (update == 1){
                return ResultAjax.success();
            }else {
                return ResultAjax.error();
            }
        }else {
            return ResultAjax.error(Constants.EXIST);
        }
    }
}
