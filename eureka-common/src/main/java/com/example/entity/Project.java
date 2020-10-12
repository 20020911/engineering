package com.example.entity;

public class Project implements java.io.Serializable{
    private int id;
    private String p_name;
    private String p_id;
    private int p_parent;
    private String p_address;
    private int p_manager;
    private String p_company;
    private int p_state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public int getP_parent() {
        return p_parent;
    }

    public void setP_parent(int p_parent) {
        this.p_parent = p_parent;
    }

    public String getP_address() {
        return p_address;
    }

    public void setP_address(String p_address) {
        this.p_address = p_address;
    }

    public int getP_manager() {
        return p_manager;
    }

    public void setP_manager(int p_manager) {
        this.p_manager = p_manager;
    }

    public String getP_company() {
        return p_company;
    }

    public void setP_company(String p_company) {
        this.p_company = p_company;
    }

    public int getP_state() {
        return p_state;
    }

    public void setP_state(int p_state) {
        this.p_state = p_state;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", p_name='" + p_name + '\'' +
                ", p_id='" + p_id + '\'' +
                ", p_parent=" + p_parent +
                ", p_address='" + p_address + '\'' +
                ", p_manager=" + p_manager +
                ", p_company='" + p_company + '\'' +
                ", p_state=" + p_state +
                '}';
    }
}
