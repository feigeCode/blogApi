package com.feige.dao;


import com.feige.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TypeMapper {
    //增删改查
    int addType(Type type);
    int deleteType(String typeName);
    int updateType(Type type);
    List<Type> getTypes(Map map);
    Type getType(String typeName);
    int getCount(Map map);
}
