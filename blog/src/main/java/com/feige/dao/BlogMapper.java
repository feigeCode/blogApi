package com.feige.dao;

import com.feige.common.utils.SelectParam;
import com.feige.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface BlogMapper {
    List<Blog> getBlogs(SelectParam selectParam);
    int getCount(String searchContent);
    int updateBlog(Blog blog);
    int addBlog(Blog blog);
    int deleteBlog(String title);
    Blog getBlogById(Integer id);
    Blog getBlog(String searchContent);
}
