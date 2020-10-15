package com.example.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleMenuListVo {
    private String roleName;
    private String remark;
    private int status;
    private List<String> menuId;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getMenuId() {
        return menuId;
    }

    public void setMenuId(List<String> menuId) {
        this.menuId = menuId;
    }
}
