package com.feige.dao;

import com.feige.common.utils.SelectParam;
import com.feige.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {
    //增删改查
    int addRole(Role role);
    int deleteRole(String roleName);
    int updateRole(Role role);
    List<Role> getRoles(SelectParam selectParam);
    Role getRole(String roleName);
    Role getRoleById(Integer id);
    int getCount(String searchContent);
}



