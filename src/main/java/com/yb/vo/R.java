package com.yb.vo;

import java.io.Serializable;

public class R implements Serializable {

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应内容
     */
    private Object data;


    public String getCode() {
        return code;
    }

    public R setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public R setMessage(String message) {
        this.message = message;
        return this;
    }


    public Object getData() {
        return data;
    }

    public R setData(Object data) {
        this.data = data;
        return this;
    }
    public static R successData(Object data){
        return new R().setCode("200").setData(data);
    }
    public static R success(String message){
        return new R().setCode("200").setMessage(message);
    }
    public static R success(String message,Object data){
        return new R().setCode("200").setMessage(message).setData(data);
    }
    public static R success(String code ,String message,Object data){
        return new R().setCode(code).setMessage(message).setData(data);
    }
    public static R success(String code,String message){
        return new R().setCode(code).setMessage(message);
    }

    public static R fail(String code,String message){
        return new R().setCode(code).setMessage(message);
    }
    public static R fail(String code,String message,Object data){
        return new R().setCode(code).setMessage(message).setData(data);
    }
    public static R fail(String message){
        return new R().setCode("400").setMessage(message);
    }





}
