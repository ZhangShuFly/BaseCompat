package com.ilyzs.basecompat.bean;

/**
 * Created by zhangshu on 2017/11/18.
 */

public class CommonJsonBean<T> {

    /**
     * 返回结果的code
     */
    public String code;

    /**
     * 消息
     */
    public String message;

    public T data;
}
