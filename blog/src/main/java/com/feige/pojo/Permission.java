package com.feige.pojo;


public class Permission {
    private Integer id;
    private String adminName;
    private String password;
    private String email;
    private StringBuilder permissionNames;

    public Permission(Integer id, String adminName, String password, String email, StringBuilder permissionNames) {
        this.id = id;
        this.adminName = adminName;
        this.password = password;
        this.email = email;
        this.permissionNames = permissionNames;
    }

    public Permission() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StringBuilder getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(StringBuilder permissionNames) {
        this.permissionNames = permissionNames;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", permissionNames='" + permissionNames + '\'' +
                '}';
    }
}
