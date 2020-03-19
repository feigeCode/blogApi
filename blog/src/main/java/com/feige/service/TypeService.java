package com.feige.service;

import com.feige.common.utils.SelectParam;
import com.feige.pojo.Type;

import java.util.List;

public interface TypeService {
    //增删改查
    int addType(Type type);
    int deleteType(String typeName);
    int updateType(Type type);
    List<Type> getTypes(SelectParam selectParam);
    Type getType(String typeName);
    Type getTypeById(Integer id);
    int getCount(String searchContent);
}
