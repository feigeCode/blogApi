package com.feige.common.utils;

/**
 * 该类是用来接收前端传来的参数，再传给dao层
 */
public class SelectParam {
    private int page;
    private int limit;
    private int id;
    private String searchContent;

    public SelectParam(int page, int limit, String searchContent) {
        this.page = (page-1)*limit;
        this.limit = limit;
        this.searchContent = searchContent!=null?"%"+searchContent+"%":null;
    }
    public SelectParam(int page, int limit,int id) {
        this.page = (page-1)*limit;
        this.limit = limit;
        this.id = id;
    }
    public SelectParam(String searchContent) {
        this.searchContent = searchContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    @Override
    public String toString() {
        return "SelectParam{" +
                "page=" + (page-1)*limit +
                ", limit=" + limit +
                ", searchContent='" + searchContent + '\'' +
                '}';
    }
}
