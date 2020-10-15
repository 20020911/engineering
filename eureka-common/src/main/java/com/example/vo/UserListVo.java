package com.example.vo;

import java.sql.Date;

public class UserListVo implements java.io.Serializable{
    private int rid;
    private int uid;
    private int urid;
    private String name;
    private int status;
    private String roleName;
    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "UserListVo{" +
                "rid=" + rid +
                ", uid=" + uid +
                ", urid=" + urid +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", roleName='" + roleName + '\'' +
                ", statusName='" + statusName + '\'' +
                '}';
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUrid() {
        return urid;
    }

    public void setUrid(int urid) {
        this.urid = urid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
