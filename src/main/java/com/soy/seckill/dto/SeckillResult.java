package com.soy.seckill.dto;

/**
 * Created by Soy on 2016/12/13.
 */
public class SeckillResult<T> {
    //请求是否成功
    private boolean success;

    //携带的数据
    private T data;

    //失败时的错误信息
    private String error;

    /**
     * 成功时需要的构造函数
     * @param success
     * @param data
     */
    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    /**
     * 失败时需要的构造函数
     * @param success
     * @param error
     */
    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
