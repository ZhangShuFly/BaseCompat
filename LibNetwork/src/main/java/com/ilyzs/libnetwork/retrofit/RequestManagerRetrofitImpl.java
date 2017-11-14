package com.ilyzs.libnetwork.retrofit;

import com.ilyzs.libnetwork.util.RequestManagerInterface;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

/**
 * 管理retrofit请求
 * Created by zs .
 */

public class RequestManagerRetrofitImpl implements RequestManagerInterface {

    private ArrayList requestList = null;

    public RequestManagerRetrofitImpl() {
        this.requestList = new ArrayList();
    }


    public void addRequestQuneue(Disposable disposable) {
        addRequest(disposable);
    }

    @Override
    public void addRequest(Object tag) {
        requestList.add(tag);
    }

    @Override
    public void cancelRequest(Object tag) {
        ((Disposable) tag).dispose();
        requestList.remove(tag);
    }

    @Override
    public void cancelAllRequest() {
        for (Object object: requestList) {
            ((Disposable) object).dispose();
        }
        requestList.clear();
    }
}
