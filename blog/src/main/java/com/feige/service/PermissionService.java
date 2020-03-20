package com.feige.service;

import com.feige.common.utils.SelectParam;
import com.feige.pojo.Permission;
import com.feige.pojo.Role;

import java.util.List;


public interface PermissionService {
    List<Role> getPermissions(SelectParam selectParam);
    int addPermission(Permission permission);
    int deletePermission(Permission permission);
    Permission getPermission(Permission permission);
    int getPermissionsCount(Integer id);
}
