package com.feige.service.impl;

import com.feige.dao.CommentMapper;
import com.feige.pojo.Comment;
import com.feige.pojo.CommentAndReplies;
import com.feige.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class CommentServiceIpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<CommentAndReplies> getCommentAndReplies(Map map) {
        List<Comment> comments = commentMapper.getComments(map);
        ArrayList<CommentAndReplies> commentAndReplies1 = new ArrayList<>();
        for (Comment comment : comments) {
            CommentAndReplies commentAndReplies = new CommentAndReplies();
            commentAndReplies.setRootComment(comment);
            getAllReply(comment,map);
            commentAndReplies.setReplies(commentList);
            commentList = new ArrayList<>();
            commentAndReplies1.add(commentAndReplies);
        }
        return commentAndReplies1;
    }
    private List<Comment> commentList = new ArrayList<>();
    //递归找出所有的回复，把它放在list中，输出到最顶级评论的下边
    public void getAllReply(Comment comment, Map map){
        map.put("parentId",comment.getId());
        List<Comment> replys = commentMapper.getReplys(map);
        if(replys.size()>0){
            for (Comment reply : replys) {
                commentList.add(reply);
                if(commentMapper.getReplys(map).size()>0){
                    map.remove("parentId");
                    getAllReply(reply,map);
                }
            }
        }
    }
}
