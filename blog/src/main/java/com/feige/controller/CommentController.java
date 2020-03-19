package com.feige.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feige.dao.CommentMapper;
import com.feige.pojo.Comment;
import com.feige.pojo.CommentAndReplies;
import com.feige.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentMapper commentMapper;

    @RequestMapping("getComments")
    public String getComments(Integer blogId,Integer page,Integer count) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("blogId",blogId);
        map.put("page",(page-1)*count);
        map.put("count",count);
        int total = commentMapper.getCount1(map);
        List<CommentAndReplies> commentAndReplies = commentService.getCommentAndReplies(map);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String str = "{\"total\":" + total + ",\"commentAndReplies\":" + mapper.writeValueAsString(commentAndReplies) + "}";
            return str;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/getAllComment")
    public String getAllComment(Integer page,Integer limit,String searchContent){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("page",(page-1)*limit);
        map.put("count",limit);
        map.put("searchContent",searchContent);
        List<Comment> allComment = commentMapper.getAllComment(map);
        int count2 = commentMapper.getCount2(map);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return "{\"code\":0,\"msg\":\"\",\"count\":" + count2 + ",\"data\":" + mapper.writeValueAsString(allComment) + "}";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/deleteComment/{id}")
    public String deleteBlog(@PathVariable("id") Integer id){
        String status = null;
        int delete = commentMapper.deleteComment(id);
        if(delete == 1){
            status = "SUCCESS";
        }
        return status;
    }
    //#{content},#{parentId},#{good},#{createTime},#{replier},#{blogId},#{userId}
    @RequestMapping("/addComment")
    public String addComment(String content, Integer parentId, Integer good, String replier, Integer blogId, Integer userId){
        String status = null;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(date);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("content",content);
        map.put("parentId",parentId);
        map.put("good",good);
        map.put("createTime",createTime);
        map.put("replier",replier);
        map.put("blogId",blogId);
        map.put("userId",userId);
        //System.out.println(map);
        int i = commentMapper.addComment(map);
        if(i == 1){
            status = "SUCCESS";
        }
        return status;
    }
}
