package com.feige.controller;


import com.feige.common.utils.DateFormatter;
import com.feige.common.utils.ResultAjax;
import com.feige.common.utils.SelectParam;
import com.feige.dao.CommentMapper;
import com.feige.pojo.Comment;
import com.feige.pojo.CommentAndReplies;
import com.feige.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(tags = "评论增删查接口")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentMapper commentMapper;

    @ApiOperation(value = "查询某一篇博客的所有评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "blogId",value = "博客ID",required = true),
            @ApiImplicitParam(name = "page",value = "页码",required = true),
            @ApiImplicitParam(name = "count",value = "页数",required = true),
    })
    @GetMapping("/get_comments")
    public ResultAjax getComments(Integer blogId,Integer page,Integer count) {
        Map<Object, Object> map = new HashMap<>();
        map.put("blogId",blogId);
        map.put("page",(page-1)*count);
        map.put("count",count);
        int total = commentMapper.getCount1(blogId);
        List<CommentAndReplies> commentAndReplies = commentService.getCommentAndReplies(map);
        return ResultAjax.success(commentAndReplies,total);
    }
    @ApiOperation(value = "查询所有评论和回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true),
            @ApiImplicitParam(name = "limit",value = "每页条数",required = true),
            @ApiImplicitParam(name = "searchContent",value = "搜索内容")
    })
    @GetMapping("/get_all_comment")
    public ResultAjax getAllComment(Integer page, Integer limit, String searchContent){
        List<Comment> allComment = commentMapper.getAllComment(new SelectParam(page,limit,searchContent));
        int count2 = commentMapper.getCount2(searchContent);
        return ResultAjax.success(allComment,count2);
    }
    @ApiOperation(value = "删除一条评论")
    @ApiImplicitParam(name = "id",value = "ID",required = true)
    @DeleteMapping("/delete_comment/{id}")
    public ResultAjax deleteBlog(@PathVariable("id") Integer id){
        int delete = commentMapper.deleteComment(id);
        if (delete == 1){
            return ResultAjax.success();
        }else {
            return ResultAjax.error();
        }
    }
    //#{content},#{parentId},#{good},#{createTime},#{replier},#{blogId},#{userId}
    @ApiOperation(value = "增加一条评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content",value = "内容",required = true),
            @ApiImplicitParam(name = "parentId",value = "父评论的ID"),
            @ApiImplicitParam(name = "good",value = "点赞量",required = true),
            @ApiImplicitParam(name = "replier",value = "被回复者"),
            @ApiImplicitParam(name = "blogId",value = "博客ID",required = true),
            @ApiImplicitParam(name = "userId",value = "用户ID",required = true),
    })
    @PostMapping("/add_comment")
    public ResultAjax addComment(String content, Integer parentId, Integer good, String replier, Integer blogId, Integer userId){
        Map<Object, Object> map = new HashMap<>();
        map.put("content",content);
        map.put("parentId",parentId);
        map.put("good",good);
        map.put("createTime", DateFormatter.timeFormatter());
        map.put("replier",replier);
        map.put("blogId",blogId);
        map.put("userId",userId);
        //System.out.println(map);
        int i = commentMapper.addComment(map);
        if(i == 1){
            return ResultAjax.success();
        }else {
            return ResultAjax.error();
        }

    }
}
