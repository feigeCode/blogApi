package com.feige.service;

import com.feige.common.utils.SelectParam;
import com.feige.pojo.Role;

import java.util.List;

public interface RoleService {
    //增删改查
    int addRole(Role role);
    int deleteRole(String roleName);
    int updateRole(Role role);
    List<Role> getRoles(SelectParam selectParam);
    Role getRole(String roleName);
    Role getRoleById(Integer id);
    int getCount(String searchContent);
}
