package com.feige.service;

import com.feige.pojo.Comment;
import com.feige.pojo.CommentAndReplies;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentService {
    List<CommentAndReplies> getComments1(String blogId);
    List<CommentAndReplies> getCommentAndReplies(@Param("map") Map map);
    void getAllReply(Comment comment, @Param("map") Map map);
}
