package com.sun.demo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by ZhouBo on 2017/2/10.
 * 用于Dao 和 Service层之间数据状态交换
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultMessage implements Serializable {

    private int code;
    private String message="";
    private Object data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
//        String msg = "";
//        try{
//            msg = new String(message.getBytes(), "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return msg;
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

    protected ResultMessage(int code){
        this.code=code;
    }

    protected ResultMessage(int code,String message){
        this.code=code;
        this.message=message;
    }

    protected ResultMessage(int code,String message,Object data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public boolean isOK(){
        if(this.getCode()==1){
            return true;
        }else{
            return false;
        }
    }

    public static ResultMessage buildOK(){
        return new ResultMessage(1);
    }

    public static ResultMessage buildOK(Object data){
        return new ResultMessage(1,"",data);
    }

    public static ResultMessage buildError(String msg){
        return new ResultMessage(0,msg);
    }

    public static ResultMessage buildError(int code,String msg){
        return new ResultMessage(code,msg);
    }
    public static ResultMessage buildError(int code,String msg,Object data){
        return new ResultMessage(code,msg,data);
    }
}
