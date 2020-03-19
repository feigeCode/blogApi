package com.feige.service;

import com.feige.pojo.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    List<Admin> getAdmins(Map map);
    Admin getAdmin(String adminName);
    int getCount(Map map);
}
