package com.zx.shark.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;

/**
 * 日志记录
 */
public class Log implements Serializable {
    private Date date;
    private String ip;   //请求的ip
    private String principal;  //用户的凭证
    private String url;  //请求的url
    private String method;   //请求的方法(get/post)
    private String class_method;  //请求的类方法
    private Object[] args;   //请求的参数
    private String response;  //返回的内容

    public Log(Date date , String ip,String principal, String url, String method, String class_method, Object[] args, String response) {
        this.ip = ip;
        this.principal = principal;
        this.url = url;
        this.method = method;
        this.class_method = class_method;
        this.args = args;
        this.response = response;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Log() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClass_method() {
        return class_method;
    }

    public void setClass_method(String class_method) {
        this.class_method = class_method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
