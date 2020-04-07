package com.feige.dao;

import com.feige.common.utils.SelectParam;
import com.feige.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface CommentMapper {
    List<Comment> getComments(Map map);
    List<Comment> getComments1(Integer blogId);
    List<Comment> getReplies(Map map);
    int addComment(Map map);
    int deleteComment(Integer id);
    List<Comment> getAllComment(SelectParam selectParam);
    int getCount1(Integer blogId);
    int getCount2(String searchContent);
}
