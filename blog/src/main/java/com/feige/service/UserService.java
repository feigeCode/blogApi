package com.feige.service;

import com.feige.common.utils.SelectParam;
import com.feige.pojo.Role;
import com.feige.pojo.User;

import java.util.List;


public interface UserService {
    //增删改查
    int addUser(User user);
    int deleteUser(String username);
    int updateUser(User user);
    List<User> getUsers(SelectParam selectParam);
    User getUserById(Integer id);
    int getCount(String username);
    User getUser(String username);
    List<Role> getPermissions(SelectParam selectParam);
}
