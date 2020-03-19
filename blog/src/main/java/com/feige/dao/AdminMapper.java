package com.feige.dao;

import com.feige.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AdminMapper {
    //增删改查
    int addAdmin(Admin admin);
    int deleteAdmin(String username);
    int updateAdmin(Admin admin);
    List<Admin> getAdmins(Map map);
    Admin getAdmin(String adminName);
    int getCount(Map map);
}
