package com.feige.controller;

import com.feige.common.utils.ResultAjax;
import com.feige.dao.CommentMapper;
import com.feige.service.BlogService;
import com.feige.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "获取数量的接口")
@RestController
@RequestMapping("/api")
public class CountController {

    @Resource
    private BlogService blogService;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private TypeService typeService;

    @ApiOperation(value = "获取博客、评论、类别的数量")
    @GetMapping("/count")
    public ResultAjax getCount(){
        int blogsCount = blogService.getCount(null);
        int typesCount = typeService.getCount(null);
        int commentsCount = commentMapper.getCount2(null);
        ResultAjax resultAjax = new ResultAjax();
        resultAjax.put("blogsCount",blogsCount);
        resultAjax.put("typesCount",typesCount);
        resultAjax.put("commentsCount",commentsCount);
        return resultAjax;
    }
}
