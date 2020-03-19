package com.feige.common.utils;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.*;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

public class MarkdownUtil {
    /**
     * markdown==>html
     *
     */
    public static String markdownToHtml(String markdown){
        Parser build = Parser.builder().build();
        Node parse = build.parse(markdown);
        HtmlRenderer build1 = HtmlRenderer.builder().build();
        return build1.render(parse);
    }

    public static String markdownToHtmlExtensions(String markdown){

        //h标签生成id
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.builder().idPrefix("feige").idSuffix("feige").build());
        //转换table的html标签
        List<Extension> tableExtensions = Arrays.asList(TablesExtension.create());
        Parser builder = Parser.builder()
                .extensions(tableExtensions)
                .build();
        Node parse = builder.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtensions)
                .extensions(tableExtensions)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
                        return new CustomAttributeProvider();
                    }
                })
                .build();
        return renderer.render(parse);
    }
    //el-table__body el-table__row el-table_12_column_52
    static class CustomAttributeProvider implements AttributeProvider {

        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            int count = 1;
            //改变a标签的target属性为_blank
            if(node instanceof Link) {
                map.put("target","_blank");
            }
            if (node instanceof TableBlock) {
                map.put("class","el-table el-table--fit el-table--enable-row-hover el-table--enable-row-transition font-size");
            }
            if (node instanceof TableRow) {
                map.put("class","el-table__row");
            }
            if (node instanceof TableCell) {
                map.put("class","el-table_13_column_55 center");
            }
            if (node instanceof TableHead) {
                map.put("class","el-table__header-wrapper");
            }
        }

        public static void main(String[] args) {
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
            String markdownToHtml = markdownToHtml(markdown);
            String htmlExtensions = markdownToHtmlExtensions(markdown);
            System.out.println(htmlExtensions);
        }
    }
}
