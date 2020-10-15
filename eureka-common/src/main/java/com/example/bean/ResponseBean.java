package com.example.bean;


import lombok.Data;

@Data
public class ResponseBean {
    private int code;
    private String message;
    private Object data;
    private int count;
    private String msg="ok";

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ResponseBean() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseBean success(Object data,int count){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(200);
        responseBean.setMessage("查询成功");
        responseBean.setData(data);
        responseBean.setCount(count);
        return responseBean;
    }
    public ResponseBean(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    //发生异常
    public static ResponseBean error(String message){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(-1);
        responseBean.setMessage(message);
        return responseBean;
    }
    //发生异常
    public static ResponseBean error(int code,String message){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(code);
        responseBean.setMessage(message);
        return responseBean;
    }
    //操作成功
    public static ResponseBean success(Object data){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(200);
        responseBean.setMessage("成功");
        responseBean.setData(data);
        return responseBean;
    }
    //操作成功
    public static ResponseBean success(String message){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(200);
        responseBean.setMessage(message);
        responseBean.setData(null);
        return responseBean;
    }
    //操作成功
    public static ResponseBean success(){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(200);
        responseBean.setMessage("success");
        responseBean.setData(null);
        return responseBean;
    }
}
