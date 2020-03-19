package com.feige.pojo;

public class Blog {
    private Integer id;
    private String typeName;
    private String title;
    private String content;
    private String createTime;
    private String changeTime;
    private String author;
    private Integer view;

    public Blog() {
    }

    public Blog(Integer id, String typeName, String title, String content, String createTime, String changeTime, String author, Integer view) {
        this.id = id;
        this.typeName = typeName;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.changeTime = changeTime;
        this.author = author;
        this.view = view;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", changeTime='" + changeTime + '\'' +
                ", author='" + author + '\'' +
                ", view=" + view +
                '}';
    }
}
