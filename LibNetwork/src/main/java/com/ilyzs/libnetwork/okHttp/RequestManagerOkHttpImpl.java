package com.ilyzs.libnetwork.okHttp;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.android.volley.Request;
import com.ilyzs.libnetwork.util.RequestManagerInterface;
import com.ilyzs.libnetwork.volley.VolleyHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * 管理okHttp请求
 * Created by zs .
 */

public class RequestManagerOkHttpImpl implements RequestManagerInterface {

    private ArrayList requestList = null;
    private ArrayMap<String,Call> map = new ArrayMap<>();
    public RequestManagerOkHttpImpl(){
        this.requestList = new ArrayList();
    }

    public void addRequestQuneue(Call call){
        String url =call.request().url().toString();
        map.put(url,call);
        addRequest(url);
    }

    @Override
    public void addRequest(Object tag) {
        requestList.add(tag);
    }

    @Override
    public void cancelRequest(Object tag) {
            map.get(tag).cancel();
            map.remove(tag);
    }

    @Override
    public void cancelAllRequest() {
        for (Map.Entry<String,Call> entry : map.entrySet()){
            entry.getValue().cancel();
        }
        map.clear();
    }
}
