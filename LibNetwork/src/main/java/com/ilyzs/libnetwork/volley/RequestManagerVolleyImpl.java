package com.ilyzs.libnetwork.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ilyzs.libnetwork.util.RequestManagerInterface;

import java.util.ArrayList;

/**
 *管理volley请求的实现，取消请求
 * Created by  zs
 */

public class RequestManagerVolleyImpl implements RequestManagerInterface {
    private ArrayList requestList = null;
    private RequestQueue requestQueue = null;
    public RequestManagerVolleyImpl(Context context){
        this.requestList = new ArrayList();
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public void addRequestQuneue(Request request){
        requestQueue.add(request);
        addRequest(request.getUrl());
    }

    public void  addRequest(Object tag){
        requestList.add(tag);
    }

    public void cancelRequest(Object tag){
        requestQueue.cancelAll(tag);
        requestList.remove(tag);
    }

    public void cancelAllRequest(){
        for (Object tag:requestList) {
            requestQueue.cancelAll(tag);
        }
        requestList.clear();
    }
}
