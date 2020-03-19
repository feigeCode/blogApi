package com.feige.service.impl;

import com.feige.common.utils.MarkdownUtil;
import com.feige.dao.BlogMapper;
import com.feige.pojo.Blog;
import com.feige.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Blog> getBlogs(Map map) {
        return blogMapper.getBlogs(map);
    }

    @Override
    public int getCount(Map map) {
        return blogMapper.getCount(map);
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }

    @Override
    public int addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
    }

    @Override
    public int deleteBlog(String title) {
        return blogMapper.deleteBlog(title);
    }

    @Override
    public Blog getBlog(Integer id) {
        Blog blog = blogMapper.getBlog(id);
        if (blog != null) {
            String markdown = MarkdownUtil.markdownToHtmlExtensions(blog.getContent());
            //System.out.println(markdown);
            blog.setContent(markdown);
        }
        return blog;
    }
}
