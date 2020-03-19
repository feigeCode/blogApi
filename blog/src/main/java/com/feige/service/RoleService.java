package com.feige.service;

import com.feige.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    //增删改查
    int addRole(Role role);
    int deleteRole(String roleName);
    int updateRole(Role role);
    List<Role> getRoles(Map map);
    Role getRole(String roleName);
    int getCount(Map map);
}
