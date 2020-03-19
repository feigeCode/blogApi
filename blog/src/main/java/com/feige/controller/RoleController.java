package com.feige.controller;


import com.feige.common.constants.Constants;
import com.feige.common.utils.ResultAjax;
import com.feige.common.utils.SelectParam;
import com.feige.common.utils.StringUtils;
import com.feige.pojo.Role;
import com.feige.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色的增删改查接口")
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 按条数查询博客
     * @param page
     * @param limit
     * @param searchContent
     * @return
     */
    @ApiOperation(value = "按条数查询角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true),
            @ApiImplicitParam(name = "limit",value = "每页条数",required = true),
            @ApiImplicitParam(name = "searchContent",value = "搜索内容")
    })
    @GetMapping("/getRoles")
    public ResultAjax getRoles(Integer page, Integer limit, String searchContent){
        List<Role> list = roleService.getRoles(new SelectParam(page,limit,searchContent));
        int roleCount = roleService.getCount(searchContent);
        return ResultAjax.success(list,roleCount);
    }

    /**
     *
     * @param roleName
     * @param permission
     * @return
     */
    @ApiOperation(value = "增加一篇博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName",value = "角色名",required = true),
            @ApiImplicitParam(name = "permission",value = "权限",required = true),
    })
    @PostMapping("/addRole")
    public ResultAjax addRole(String roleName,String permission){
        Role role = roleService.getRole(roleName);
        if (StringUtils.isNull(role)){
            int add = roleService.addRole(new Role(0, roleName, permission));
            if (add == 1){
                return ResultAjax.success();
            }else {
                return ResultAjax.error();
            }
        }else {
            return ResultAjax.error(Constants.EXIST);
        }
    }
    @ApiOperation(value = "删除一个用户")
    @ApiImplicitParam(name = "roleName",value = "角色名",required = true)
    @DeleteMapping("/deleteRole/{roleName}")
    public ResultAjax deleteUser(@PathVariable("roleName") String roleName){
        int delete = roleService.deleteRole(roleName);
        if (delete == 1) {
            return ResultAjax.success();
        }else {
            return ResultAjax.error();
        }
    }

    /**
     *
     * @param id
     * @param roleName
     * @param permission
     * @return
     */
    @ApiOperation(value = "修改一个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "ID",required = true),
            @ApiImplicitParam(name = "roleName",value = "角色名",required = true),
            @ApiImplicitParam(name = "permission",value = "权限",required = true)
    })
    @PutMapping("/updateRole/{id}")
    public ResultAjax updateUser(@PathVariable("id") Integer id,String  roleName,String permission){
       if (roleService.getRoleById(id).getRoleName().equals(roleName) || StringUtils.isNull(roleService.getRole(roleName))){
           int update = roleService.updateRole(new Role(id, roleName, permission));
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
