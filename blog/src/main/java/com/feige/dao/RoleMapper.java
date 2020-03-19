package com.feige.dao;

import com.feige.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RoleMapper {
    //增删改查
    int addRole(Role role);
    int deleteRole(String roleName);
    int updateRole(Role role);
    List<Role> getRoles(Map map);
    Role getRole(String roleName);
    int getCount(Map map);
}



