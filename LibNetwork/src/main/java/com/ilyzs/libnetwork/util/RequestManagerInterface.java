package com.ilyzs.libnetwork.util;

/**
 * 管理请求的接口，用于取消请求
 * Created by zs.
 */

public interface RequestManagerInterface {

    void  addRequest(Object tag);
    void  cancelRequest(Object tag);
    void  cancelAllRequest();
}
