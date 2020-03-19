package com.feige.service.impl;

import com.feige.dao.RoleMapper;
import com.feige.pojo.Role;
import com.feige.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;


    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public int deleteRole(String roleName) {
        return roleMapper.deleteRole(roleName);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public List<Role> getRoles(Map map) {
        return roleMapper.getRoles(map);
    }

    @Override
    public Role getRole(String roleName) {
        return roleMapper.getRole(roleName);
    }

    @Override
    public int getCount(Map map) {
        return roleMapper.getCount(map);
    }
}
