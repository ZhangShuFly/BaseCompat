package com.ilyzs.libnetwork.util;

/**
 * 请求结果回调接口
 * Created by zs
 */

public interface RequestCallback  {
    void onSuccess(String content);

    void onFail(String failMsg);
}
