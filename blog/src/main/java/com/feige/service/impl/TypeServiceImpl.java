package com.feige.service.impl;

import com.feige.common.utils.SelectParam;
import com.feige.dao.TypeMapper;
import com.feige.pojo.Type;
import com.feige.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public List<Type> getTypes(SelectParam selectParam) {
        return typeMapper.getTypes(selectParam);
    }

    @Override
    public Type getType(String typeName) {
        return typeMapper.getType(typeName);
    }

    @Override
    public Type getTypeById(Integer id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public int getCount(String searchContent) {
        return typeMapper.getCount(searchContent);
    }

}
