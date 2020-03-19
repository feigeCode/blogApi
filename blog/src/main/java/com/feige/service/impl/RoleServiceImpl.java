package com.feige.service.impl;

import com.feige.common.utils.SelectParam;
import com.feige.dao.RoleMapper;
import com.feige.pojo.Role;
import com.feige.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Role> getRoles(SelectParam selectParam) {
        return roleMapper.getRoles(selectParam);
    }
    @Override
    public Role getRole(String roleName) {
        return roleMapper.getRole(roleName);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public int getCount(String searchContent) {
        return roleMapper.getCount(searchContent);
    }

}
