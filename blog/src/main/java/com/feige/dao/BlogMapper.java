package com.feige.dao;

import com.feige.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface BlogMapper {
    List<Blog> getBlogs(Map map);
    int getCount(Map map);
    int updateBlog(Blog blog);
    int addBlog(Blog blog);
    int deleteBlog(String title);
    Blog getBlog(Integer id);
}
