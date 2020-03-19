package com.feige.dao;

import com.feige.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface CommentMapper {
    List<Comment> getComments(Map map);
    List<Comment> getReplys(Map map);
    int addComment(Map map);
    int deleteComment(Integer id);
    List<Comment> getAllComment(Map map);
    int getCount1(Map map);
    int getCount2(Map map);
}
