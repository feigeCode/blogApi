package com.feige.dao;

import com.feige.common.utils.SelectParam;
import com.feige.pojo.Permission;
import com.feige.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionMapper {
    //权限的增删查
    List<Role> getPermissions(SelectParam selectParam);
    int addPermission(Permission permission);
    int deletePermission(Permission permission);
    Permission getPermission(Permission permission);
    int getPermissionsCount(Integer id);
}
