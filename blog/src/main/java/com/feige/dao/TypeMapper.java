package com.feige.dao;


import com.feige.common.utils.SelectParam;
import com.feige.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {
    //增删改查
    int addType(Type type);
    int deleteType(String typeName);
    int updateType(Type type);
    List<Type> getTypes(SelectParam selectParam);
    Type getType(String typeName);
    Type getTypeById(Integer id);
    int getCount(String searchContent);
}
