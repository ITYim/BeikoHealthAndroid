package com.yim.http;

/**
 * Created by Administrator on 2017/3/16.
 */

public class RestfulData<T> {
    /**
     * 响应代码, 默认为OK, 发生错误可填入错误代码
     */
    private String errcode;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 业务数据
     */
    private T data;

    /**
     * 分页行数
     */
    private int pageRows;

    /**
     * 分页页码
     */
    private int pageNum;

    /**
     * 数据总数
     */
    private int total;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPageRows() {
        return pageRows;
    }

    public void setPageRows(int pageRows) {
        this.pageRows = pageRows;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
