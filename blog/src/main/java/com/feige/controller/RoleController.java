package com.feige.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feige.pojo.Role;
import com.feige.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/getRoles")
    public String getRoles(Integer page,Integer limit,String searchContent){
        HashMap<String,Object> map = new HashMap<>();
        map.put("page",(page-1)*limit);
        map.put("count",limit);
        map.put("searchContent",searchContent);
        List<Role> list = roleService.getRoles(map);
        int roleCount = roleService.getCount(map);
        //创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
        //将我们的对象解析成为json格式
        try {
            //由于@ResponseBody注解，这里会将str转成json格式返回；十分方便
            return "{\"code\":0,\"msg\":\"\",\"count\":" + roleCount + ",\"data\":" + mapper.writeValueAsString(list) + "}";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }
    @RequestMapping("/addRole")
    public String addRole(String roleName,String permission){
        String status = null;

        Role role1 = new Role(0,roleName,permission);
        Role role2 = roleService.getRole(roleName);
        if(role2 == null){
            int  add = roleService.addRole(role1);
            if(add == 1){
                status = "SUCCESS";
            }
        }else {
            status = "exist";
        }
        return status;
    }
    @RequestMapping("/deleteRole/{roleName}")
    public String deleteUser(@PathVariable("roleName") String roleName){
        String status = null;
        int delete = roleService.deleteRole(roleName);
        if(delete == 1){
            status = "SUCCESS";
        }
        return status;
    }
    @RequestMapping("/updateRole/{id}/{oldRoleName}")
    public String updateUser(@PathVariable("oldRoleName") String oldRoleName,@PathVariable("id") Integer id,String  roleName,String permission){
        String status = null;
        Role role1 = new Role(id,roleName,permission);
        if(roleName.equals(oldRoleName)){
            int update = roleService.updateRole(role1);
            if(update == 1){
                status = "SUCCESS";
            }
        }else {
            Role role2 = roleService.getRole(roleName);
            if(role2 == null){
                int update = roleService.updateRole(role1);
                if(update == 1){
                    status = "SUCCESS";
                }
            }else {
                status = "exist";
            }
        }
        return status;
    }
    @RequestMapping("/getRole")
    public String getUser(String roleName){
        ObjectMapper mapper = new ObjectMapper();
        Role role = roleService.getRole(roleName);
        String str = null;
        try {
            str = mapper.writeValueAsString(role);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;

    }
}
