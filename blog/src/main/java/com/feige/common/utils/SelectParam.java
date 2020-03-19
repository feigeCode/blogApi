package com.feige.common.utils;

public class SelectParam {
    private int page;
    private int limit;
    private String searchContent;

    public SelectParam(int page, int limit, String searchContent) {
        this.page = (page-1)*limit;
        this.limit = limit;
        this.searchContent = searchContent!=null?"%"+searchContent+"%":null;
    }
    public SelectParam(String searchContent) {
        this.page = 0;
        this.limit = 1;
        this.searchContent = searchContent;
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
