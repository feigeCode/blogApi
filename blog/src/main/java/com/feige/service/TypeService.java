package com.feige.service;

import com.feige.pojo.Type;

import java.util.List;
import java.util.Map;

public interface TypeService {
    //增删改查
    int addType(Type type);
    int deleteType(String typeName);
    int updateType(Type type);
    List<Type> getTypes(Map map);
    Type getType(String typeName);
    int getCount(Map map);
}
