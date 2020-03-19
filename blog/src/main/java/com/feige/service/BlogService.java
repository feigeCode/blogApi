package com.feige.service;

import com.feige.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    List<Blog> getBlogs(Map map);
    int getCount(Map map);
    int updateBlog(Blog blog);
    int addBlog(Blog blog);
    int deleteBlog(String title);
    Blog getBlog(Integer id);
}
