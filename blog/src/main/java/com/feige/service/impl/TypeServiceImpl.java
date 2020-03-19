package com.feige.service.impl;

import com.feige.dao.TypeMapper;
import com.feige.pojo.Type;
import com.feige.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    @Override
    public int addType(Type type) {
        return typeMapper.addType(type);
    }

    @Override
    public int deleteType(String typeName) {
        return typeMapper.deleteType(typeName);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Override
    public List<Type> getTypes(Map map) {
        return typeMapper.getTypes(map);
    }

    @Override
    public Type getType(String typeName) {
        return typeMapper.getType(typeName);
    }

    @Override
    public int getCount(Map map) {
        return typeMapper.getCount(map);
    }
}
