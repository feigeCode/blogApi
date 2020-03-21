package com.feige.controller;


import com.feige.common.constants.Constants;
import com.feige.common.utils.DateFormatter;
import com.feige.common.utils.ResultAjax;
import com.feige.common.utils.SelectParam;
import com.feige.common.utils.StringUtils;
import com.feige.pojo.Blog;
import com.feige.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "博客的增删改查接口")
@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    /**
     * 按条数查询博客
     * @param page
     * @param limit
     * @param searchContent
     * @return
     */
    @ApiOperation(value = "按条数查询博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true),
            @ApiImplicitParam(name = "limit",value = "每页条数",required = true),
            @ApiImplicitParam(name = "searchContent",value = "搜索内容")
    })
    @GetMapping("/get_blogs")
    public ResultAjax getBlogs(Integer page, Integer limit, String searchContent){

        List<Blog> blogs = blogService.getBlogs(new SelectParam(page,limit,searchContent));
        int blogCount = blogService.getCount(searchContent);
        return ResultAjax.success(blogs,blogCount);
    }

    /**
     *
     * @param typeName
     * @param title
     * @param content
     * @param view
     * @param author
     * @return
     */
    @ApiOperation(value = "增加一篇博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeName", value = "所属类型", required = true),
            @ApiImplicitParam(name = "title", value = "标题", required = true),
            @ApiImplicitParam(name = "content", value = "内容", required = true),
            @ApiImplicitParam(name = "view", value = "浏览量", required = true),
            @ApiImplicitParam(name = "author", value = "作者", required = true),
    })
    @PostMapping("/add_blog")
    public ResultAjax addBlog(String  typeName,String title,String content,Integer view,String author){
        Blog blog = blogService.getBlog(title);
        if (StringUtils.isNull(blog)){
            int add = blogService.addBlog(new Blog(0, typeName, title, content, DateFormatter.timeFormatter(), DateFormatter.timeFormatter(), author, view));
            if (add == 1){
                return ResultAjax.success();
            }else {
                return ResultAjax.error();
            }
        }else {
            return ResultAjax.error(Constants.EXIST);
        }
    }

    /**
     *
     * @param title
     * @return
     */
    @ApiOperation(value = "删除一篇博客")
    @ApiImplicitParam(name = "title",value = "标题",required = true)
    @DeleteMapping("/delete_blog/{title}")
    public ResultAjax deleteBlog(@PathVariable("title") String title){
        int delete = blogService.deleteBlog(title);
        if (delete == 1){
            return ResultAjax.success();
        }else {
            return ResultAjax.error();
        }
    }

    /**
     *
     * @param id
     * @param typeName
     * @param title
     * @param content
     * @param createTime
     * @param view
     * @param author
     * @return
     */
    @ApiOperation(value = "修改一篇博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeName", value = "所属类型", required = true),
            @ApiImplicitParam(name = "title", value = "标题", required = true),
            @ApiImplicitParam(name = "content", value = "内容", required = true),
            @ApiImplicitParam(name = "view", value = "浏览量", required = true),
            @ApiImplicitParam(name = "author", value = "作者", required = true),
            @ApiImplicitParam(name = "id", value = "ID", required = true),
            @ApiImplicitParam(name = "createTime", value = "创建日期", required = true),
    })
    @PutMapping("/update_blog/{id}")
    public ResultAjax updateBlog(@PathVariable("id") Integer id,String  typeName,String title,String content,String createTime,Integer view,String author){
        if (blogService.getBlogById(id).getTitle().equals(title) || StringUtils.isNull(blogService.getBlog(title))){
            int update = blogService.updateBlog(new Blog(id, typeName, title, content, createTime, DateFormatter.timeFormatter(), author, view));
            if (update == 1){
                return ResultAjax.success();
            }else {
                return ResultAjax.error();
            }
        }else {
            return ResultAjax.error(Constants.EXIST);
        }

    }
    @ApiOperation(value = "获取对应ID的博客")
    @ApiImplicitParam(name = "id",value = "博客ID",required = true)
    @GetMapping("/get_blog/{id}")
    public ResultAjax getBlog(@PathVariable("id") Integer id){
        Blog blogById = blogService.getBlogById(id);
        if (StringUtils.isNull(blogById)){
            return ResultAjax.error();
        }else {
            return ResultAjax.success(blogById);
        }

    }
}
