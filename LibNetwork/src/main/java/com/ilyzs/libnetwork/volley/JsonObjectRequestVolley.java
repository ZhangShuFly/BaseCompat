package com.ilyzs.libnetwork.volley;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * 封装volley的jsonObjectRequest
 * Created by zs
 */

public class JsonObjectRequestVolley extends JsonObjectRequest {

    public JsonObjectRequestVolley(int method, @NonNull String url, JSONObject jsonRequest,@NonNull Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        this.setTag(url);
    }

    public JsonObjectRequestVolley(@NonNull String url, JSONObject jsonRequest, @NonNull Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, jsonRequest, listener, errorListener);
        this.setTag(url);
    }
}
