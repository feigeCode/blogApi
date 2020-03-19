package com.feige.pojo;

public class Comment {
    private Integer id;
    private String content;
    private Integer parentId;
    private Integer good;
    private String createTime;
    private String username;
    private String replier;
    private String headPhoto;

    public Comment(Integer id, String content, Integer parentId, Integer good, String createTime, String username, String replier, String headPhoto) {
        this.id = id;
        this.content = content;
        this.parentId = parentId;
        this.good = good;
        this.createTime = createTime;
        this.username = username;
        this.replier = replier;
        this.headPhoto = headPhoto;
    }

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReplier() {
        return replier;
    }

    public void setReplier(String replier) {
        this.replier = replier;
    }

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", parentId=" + parentId +
                ", good=" + good +
                ", createTime='" + createTime + '\'' +
                ", username='" + username + '\'' +
                ", replier='" + replier + '\'' +
                ", headPhoto='" + headPhoto + '\'' +
                '}';
    }
}
