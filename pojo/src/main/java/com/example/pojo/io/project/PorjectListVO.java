package com.example.pojo.io.project;

public class PorjectListVO implements java.io.Serializable{
    private String p_name;//项目名称
    private int id;//编号
    private String p_id;//项目编号
    private String p_address;//地址
    private String manager;//项目经理
    private String p_company;//项目集团
    private String dictionaryType;//项目状态
    private int p_parent;
    private int p_manager;
    private int p_state;

    @Override
    public String toString() {
        return "PorjectListVO{" +
                "p_name='" + p_name + '\'' +
                ", id=" + id +
                ", p_id='" + p_id + '\'' +
                ", p_address='" + p_address + '\'' +
                ", manager='" + manager + '\'' +
                ", p_company='" + p_company + '\'' +
                ", dictionaryType='" + dictionaryType + '\'' +
                ", p_parent=" + p_parent +
                ", p_manager=" + p_manager +
                ", p_state=" + p_state +
                '}';
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_address() {
        return p_address;
    }

    public void setP_address(String p_address) {
        this.p_address = p_address;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getP_company() {
        return p_company;
    }

    public void setP_company(String p_company) {
        this.p_company = p_company;
    }

    public String getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(String dictionaryType) {
        this.dictionaryType = dictionaryType;
    }

    public int getP_parent() {
        return p_parent;
    }

    public void setP_parent(int p_parent) {
        this.p_parent = p_parent;
    }

    public int getP_manager() {
        return p_manager;
    }

    public void setP_manager(int p_manager) {
        this.p_manager = p_manager;
    }

    public int getP_state() {
        return p_state;
    }

    public void setP_state(int p_state) {
        this.p_state = p_state;
    }
}
