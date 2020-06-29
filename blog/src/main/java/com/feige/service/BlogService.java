package com.feige.service;

import com.feige.common.utils.SelectParam;
import com.feige.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    List<Blog> getBlogs(SelectParam selectParam);
    int getCount(String searchContent);
    int updateBlog(Blog blog);
    int addBlog(Blog blog);
    int deleteBlog(String title);
    Blog getBlogById(Integer id);
    Blog getBlog(String searchContent);
    List<Blog> getBlogByTypeName(Map map);
    int getCountByTypeName(String typeName);
    List<Blog> getAll();
}
