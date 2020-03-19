package com.feige.service;

import com.feige.common.utils.SelectParam;
import com.feige.pojo.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getBlogs(SelectParam selectParam);
    int getCount(String searchContent);
    int updateBlog(Blog blog);
    int addBlog(Blog blog);
    int deleteBlog(String title);
    Blog getBlogById(Integer id);
    Blog getBlog(String searchContent);
}
