package com.feige.service.impl;

import com.feige.common.utils.SelectParam;
import com.feige.dao.PermissionMapper;
import com.feige.pojo.Permission;
import com.feige.pojo.Role;
import com.feige.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    private PermissionMapper permissionMapper;

    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<Role> getPermissions(SelectParam selectParam) {
        return permissionMapper.getPermissions(selectParam);
    }

    @Override
    public int addPermission(Permission permission) {
        return permissionMapper.addPermission(permission);
    }

    @Override
    public int deletePermission(Permission permission) {
        return permissionMapper.deletePermission(permission);
    }

    @Override
    public Permission getPermission(Permission permission) {
        return permissionMapper.getPermission(permission);
    }

    @Override
    public int getPermissionsCount(Integer id) {
        return permissionMapper.getPermissionsCount(id);
    }
}
