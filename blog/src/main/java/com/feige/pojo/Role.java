package com.feige.pojo;

public class Role {
    private Integer id;
    private String roleName;
    private String permission;

    public Role() {
    }

    public Role(Integer id, String roleName, String permission) {
        this.id = id;
        this.roleName = roleName;
        this.permission = permission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
