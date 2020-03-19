package com.feige.pojo;

public class User {
    private Integer id;
    private String headPhoto;
    private String username;
    private String email;
    private String password;
    private Integer sex;
    private String time;
    private String hobby;
    private String selfIntroduce;
    private String other;

    public User() {
    }

    public User(Integer id, String headPhoto, String username, String email, String password, Integer sex, String time, String hobby, String selfIntroduce, String other) {
        this.id = id;
        this.headPhoto = headPhoto;
        this.username = username;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.time = time;
        this.hobby = hobby;
        this.selfIntroduce = selfIntroduce;
        this.other = other;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSelfIntroduce() {
        return selfIntroduce;
    }

    public void setSelfIntroduce(String selfIntroduce) {
        this.selfIntroduce = selfIntroduce;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", headPhoto='" + headPhoto + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", time='" + time + '\'' +
                ", hobby='" + hobby + '\'' +
                ", selfIntroduce='" + selfIntroduce + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
