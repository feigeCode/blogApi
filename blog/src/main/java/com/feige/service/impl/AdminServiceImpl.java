package com.feige.service.impl;

import com.feige.dao.AdminMapper;
import com.feige.pojo.Admin;
import com.feige.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;


    @Override
    public List<Admin> getAdmins(Map map) {
        return adminMapper.getAdmins(map);
    }

    @Override
    public Admin getAdmin(String adminName) {
        return adminMapper.getAdmin(adminName);
    }

    @Override
    public int getCount(Map map) {
        return adminMapper.getCount(map);
    }
}
