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
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<CommentAndReplies> getComments1(Integer blogId) {
        //查询出所有的评论
        List<Comment> comments1 = commentMapper.getComments1(blogId);
        //新建一个存储根评论和回复的列表
        List<CommentAndReplies> commentAndReplies = new ArrayList<>();
        //遍历从数据库中查询出来的数据
        for (Comment comment : comments1) {
            //父ID为空时，说明是根评论，接下来查找他的所有回复
            if (comment.getParentId() == null){
                //移除这条评论，减少遍历的次数
                //comments1.remove(comment);
                //新建一个评论和回复的对象
                CommentAndReplies commentAndReplies1 = new CommentAndReplies();
                commentAndReplies1.setRootComment(comment);
                //找出他的所有回复
                searchReplies(comment,comments1);
                commentAndReplies1.setReplies(list);
                commentAndReplies.add(commentAndReplies1);
                //清空列表中的数据，以便于用于保存下一次的回复数据
                list = new ArrayList<>();
            }

        }
        return commentAndReplies;
    }
    public static List<Comment> list = new ArrayList<>();
    private void searchReplies(Comment comment,List<Comment> comments1){
        for (Comment comment1 : comments1) {
            if (comment1.getParentId() != null){
                //判断当前评论的父ID等不等于根评论的ID，如果等于就说明是根评论的回复，把他加入到list列表中
                if (comment1.getParentId().equals(comment.getId())){
                    list.add(comment1);
                    //移除这条回复，减少遍历的次数
                    //comments1.remove(comment1);
                    //递归找出这条回复的回复
                    searchReplies(comment1,comments1);
                }
            }
        }
    }








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
        List<Comment> replies = commentMapper.getReplies(map);
        if(replies.size()>0){
            for (Comment reply : replies) {
                commentList.add(reply);
                if(commentMapper.getReplies(map).size()>0){
                    map.remove("parentId");
                    getAllReply(reply,map);
                }
            }
        }
    }
}
