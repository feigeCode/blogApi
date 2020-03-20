package com.feige.pojo;

public class Permission {
    Integer uId;
    Integer rId;

    public Permission(Integer uId, Integer rId) {
        this.uId = uId;
        this.rId = rId;
    }

    public Permission() {
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "uId=" + uId +
                ", rId=" + rId +
                '}';
    }
}
