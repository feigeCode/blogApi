package com.feige.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feige.pojo.Blog;
import com.feige.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;




    @RequestMapping("/getBlogs")
    public String getBlogs(Integer page,Integer limit,String searchContent){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("page",(page-1)*limit);
        map.put("count",limit);
        map.put("searchContent",searchContent);
        List<Blog> blogs = blogService.getBlogs(map);
        int blogCount = blogService.getCount(map);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return "{\"code\":0,\"msg\":\"\",\"count\":" + blogCount + ",\"data\":" + mapper.writeValueAsString(blogs) + "}";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/addBlog")
    public String addBlog(Integer id,String  typeName,String title,String content,Integer view,String author){
        String status = null;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
        Blog blog1 = new Blog(id,typeName,title,content,createTime,createTime,author,view);
        Blog blog2 = blogService.getBlog(id);
        if(blog2 == null){
            int  add = blogService.addBlog(blog1);
            if(add == 1){
                status = "SUCCESS";
            }
        }else {
            status = "exist";
        }
        return status;
    }
    @RequestMapping("/deleteBlog/{title}")
    public String deleteBlog(@PathVariable("title") String title){
        String status = null;
        int delete = blogService.deleteBlog(title);
        if(delete == 1){
            status = "SUCCESS";
        }
        return status;
    }
    @RequestMapping("/updateBlog/{id}/{oldBlogName}")
    public String updateBlog(@PathVariable("oldBlogName") String oldBlogName,@PathVariable("id") Integer id,String  typeName,String title,String content,String createTime,Integer view,String author){
        String status = null;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String changeTime = format.format(date);
        Blog blog1 = new Blog(id,typeName,title,content,createTime,changeTime,author,view);
        if(title.equals(oldBlogName)){
            int update = blogService.updateBlog(blog1);
            if(update == 1){
                status = "SUCCESS";
            }
        }else {
            Blog blog2 = blogService.getBlog(id);
            if(!(blog2.getTitle().equals(title))){
                int update = blogService.updateBlog(blog1);
                if(update == 1){
                    status = "SUCCESS";
                }
            }else {
                status = "exist";
            }
        }
        return status;
    }
    @RequestMapping("/getBlog")
    public String getBlog(Integer id){
        ObjectMapper mapper = new ObjectMapper();
        Blog blog = blogService.getBlog(id);
        //System.out.println(blog.getContent());
        String str = null;
        try {
            str = mapper.writeValueAsString(blog);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;

    }
}
