package com.example.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class MenuNodeVo {
    private int id;
    private int parentId;
    private String menuName;
    private String url;
    private int orderNum; //排序
    private int open;  //展开或关闭
    private boolean disabled; //禁用或启用
    private String perms; //权限标志
    private int type; //菜单还是按钮
    private String link;
    private String href;

    public String getHref() {
        return href;
    }


    public String getLink() {
        return link;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
        this.title = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        this.link  = url;
        this.href = url;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<MenuNodeVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuNodeVo> children) {
        this.children = children;
    }
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle() {
        this.title = this.menuName;
    }
    private List<MenuNodeVo> children = new ArrayList<>(); //子结构与节点


    /**
     * 排序，根基order排序
     */
    public static Comparator<MenuNodeVo> order(){
        Comparator<MenuNodeVo> comparator =(o1, o2) -> {
            if(o1.getOrderNum() != o2.getOrderNum()){
                return (int) (o1.getOrderNum() - o2.getOrderNum());
            }
            return 0;
        };
        return comparator;
    }
}
