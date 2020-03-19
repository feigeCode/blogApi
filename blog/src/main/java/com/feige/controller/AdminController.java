package com.feige.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feige.pojo.Admin;
import com.feige.pojo.Permission;
import com.feige.pojo.Role;
import com.feige.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;


    @RequestMapping("/getAdmins")
    public String getAdmins(Integer page,Integer limit,String searchContent){
        ArrayList<Permission> permissions = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",(page-1)*limit);
        map.put("count",limit);
        map.put("searchContent",searchContent);
        List<Admin> list = adminService.getAdmins(map);
        StringBuilder roleString = new StringBuilder();
        for (Admin admin : list) {
            for (Role role : admin.getRoles()) {
                roleString.append(role.getRoleName()).append(",");
            }
            Permission permission = new Permission(admin.getId(), admin.getAdminName(), admin.getEmail(), admin.getPassword(), roleString);
            permissions.add(permission);
        }
        int adminCount = adminService.getCount(map);
        //创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
        //将我们的对象解析成为json格式
        try {
            //由于@RestController注解，这里会将str转成json格式返回；十分方便
            return "{\"code\":0,\"msg\":\"\",\"count\":" + adminCount + ",\"data\":" + mapper.writeValueAsString(permissions) + "}";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
