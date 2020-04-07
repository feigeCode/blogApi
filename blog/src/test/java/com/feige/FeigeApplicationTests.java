package com.feige;


import com.feige.common.utils.MarkdownUtil;
import com.feige.common.utils.redis.RedisCache;
import com.feige.dao.BlogMapper;
import com.feige.dao.CommentMapper;
import com.feige.pojo.Blog;
import com.feige.pojo.Comment;
import com.feige.pojo.CommentAndReplies;
import com.feige.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;


@SpringBootTest
public class FeigeApplicationTests {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    RedisCache redisCache;

    @Autowired
    DataSource dataSource;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CommentService commentService;
    
    @Test
    public void contextLoads() {
        //Object feige = redisCache.getCacheObject(Constants.LOGIN_USER_KEY);
        //System.out.println(feige);
        //DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        //System.out.println(druidDataSource.getActiveCount());
        Blog blogById = blogMapper.getBlogById(13);
        System.out.println(blogById.getContent());
        String markdown = MarkdownUtil.markdownToHtmlExtensions(blogById.getContent());
        System.out.println(markdown);
    }
    @Test
    public void test2() {
        String markdown = "| 飞哥 | 飞哥 | 飞哥 |\n" +
                "| ---- | ---- | ---- |\n" +
                "| 飞哥 | 飞哥 | 飞哥 |\n" +
                "| 飞哥 | 飞哥 | 飞哥 |\n" +
                "| 飞哥 | 飞哥 | 飞哥 |\n" +
                "\n" +
                "\n" +
                "\n" +
                "~~~java\n" +
                "static class CustomAttributeProvider implements AttributeProvider {\n" +
                "\n" +
                "        @Override\n" +
                "        public void setAttributes(Node node, String s, Map<String, String> map) {\n" +
                "            //改变a标签的target属性为_blank\n" +
                "            if(node instanceof Link) {\n" +
                "                map.put(\"target\",\"_blank\");\n" +
                "            }\n" +
                "            if (node instanceof TableBlock) {\n" +
                "                map.put(\"class\",\"el-table__body-wrapper is-scrolling-none\");\n" +
                "            }\n" +
                "            if (node instanceof TableBody) {\n" +
                "                map.put(\"class\",\"el-table__body\");\n" +
                "            }\n" +
                "            if (node instanceof TableRow) {\n" +
                "                map.put(\"class\",\"el-table__row\");\n" +
                "            }\n" +
                "            if (node instanceof TableCell) {\n" +
                "                map.put(\"class\",\"el-table_13_column_55\");\n" +
                "            }\n" +
                "        }\n" +
                "~~~\n" +
                "\n" +
                "# feige\n" +
                "\n" +
                "## feige\n" +
                "\n" +
                "## feige\n" +
                "\n";

        String htmlExtensions = MarkdownUtil.markdownToHtmlExtensions(markdown);
        System.out.println(htmlExtensions);
    }
    @Test
    void test3(){
        List<Comment> comments1 = commentMapper.getComments1(1);
        System.out.println(comments1);
    }

    @Test
    void test4(){
        List<CommentAndReplies> comments1 = commentService.getComments1(1);
        for (CommentAndReplies commentAndReplies : comments1) {
            System.out.println(commentAndReplies);
        }
    }



}
