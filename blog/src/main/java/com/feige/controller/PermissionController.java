package com.feige.controller;

import com.feige.common.constants.Constants;
import com.feige.common.utils.ResultAjax;
import com.feige.common.utils.SelectParam;
import com.feige.common.utils.StringUtils;
import com.feige.pojo.Permission;
import com.feige.pojo.Role;
import com.feige.service.PermissionService;
import com.feige.service.RoleService;
import com.feige.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "权限的增删查接口")
@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     *
     * @param page
     * @param limit
     * @param id
     * @return
     */
    @ApiOperation(value = "查询指定用户的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true),
            @ApiImplicitParam(name = "limit",value = "每页条数",required = true),
            @ApiImplicitParam(name = "id",value = "用户ID",required = true)
    })
    @GetMapping("/getPermissions/{id}")
    public ResultAjax getPermissions(@RequestParam(value = "page",defaultValue = "1") Integer page, @RequestParam(value = "limit",defaultValue = "10") Integer limit, @PathVariable("id") Integer id){
        List<Role> permissions = permissionService.getPermissions(new SelectParam(page,limit,id));
        int permissionsCount = permissionService.getPermissionsCount(id);
        return ResultAjax.success(permissions,permissionsCount);
    }

    /**
     *
     * @param uId
     * @param rId
     * @return
     */
    @ApiOperation(value = "给指定用户授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uId",value = "用户ID",required = true),
            @ApiImplicitParam(name = "rId",value = "角色ID",required = true)
    })
    @PostMapping("/addPermission")
    public ResultAjax addPermission(Integer uId,Integer rId){
        if (StringUtils.isNull(userService.getUserById(uId))){
            return ResultAjax.error(Constants.USERNAME_NO_EXIST);
        }
        if (StringUtils.isNull(roleService.getRoleById(rId))){
            return ResultAjax.error(Constants.ROLE_NO_EXIST);
        }
        Permission permission = new Permission(uId, rId);
        if (StringUtils.isNull(permissionService.getPermission(permission))){
            int add = permissionService.addPermission(permission);
            if (add == 1){
                return ResultAjax.success();
            }else {
                return ResultAjax.error();
            }
        }else {
            return ResultAjax.error(Constants.EXIST);
        }

    }

    /**
     *
     * @param uId
     * @param rId
     * @return
     */
    @ApiOperation(value = "删除指定用户的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uId",value = "用户ID",required = true),
            @ApiImplicitParam(name = "rId",value = "角色ID",required = true)
    })
    @DeleteMapping("/deletePermission/{uId}/{rId}")
    public ResultAjax deletePermission(@PathVariable("uId") Integer uId,@PathVariable("rId") Integer rId){
        int delete = permissionService.deletePermission(new Permission(uId,rId));
        if (delete == 1){
            return ResultAjax.success();
        }else {
            return ResultAjax.error();
        }
    }
    @ApiOperation(value = "未被授权返回的页面")
    @GetMapping("/noPermission")
    public String noPermission(){
        return "您没有被授权访问该页面";
    }
}
