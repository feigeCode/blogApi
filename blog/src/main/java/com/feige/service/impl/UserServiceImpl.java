package com.feige.service.impl;

import com.feige.common.utils.SelectParam;
import com.feige.dao.UserMapper;
import com.feige.pojo.User;
import com.feige.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    //调用dao层的操作，设置一个set接口，方便Spring管理
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public int deleteUser(String username) {
        return userMapper.deleteUser(username);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public List<User> getUsers(SelectParam selectParam){
        return userMapper.getUsers(selectParam);
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public int getCount(String username) {
        return userMapper.getCount(username);
    }

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }


}
