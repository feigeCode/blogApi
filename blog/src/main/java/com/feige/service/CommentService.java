package com.feige.service;

import com.feige.pojo.Comment;
import com.feige.pojo.CommentAndReplies;

import java.util.List;
import java.util.Map;

public interface CommentService {
    List<CommentAndReplies> getCommentAndReplies(Map map);
    void getAllReply(Comment comment, Map map);
}
