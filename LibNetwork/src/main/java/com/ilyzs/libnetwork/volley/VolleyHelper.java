package com.ilyzs.libnetwork.volley;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ilyzs.libnetwork.AppBaseActivity;
import com.ilyzs.libnetwork.util.RequestCallback;
import com.ilyzs.libnetwork.util.RequestManagerInterface;
import com.ilyzs.libnetwork.util.RequestParameter;
import com.ilyzs.libnetwork.util.URLData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.http.Url;

/**
 * volley帮助类
 * Created by zs .
 */

public class VolleyHelper {
    private Context context;

    public VolleyHelper(Context context) {
        this.context = context;
    }

    public void doHttpGetJsonObject(RequestManagerInterface rmi,URLData urlData, List<RequestParameter> rpList, final RequestCallback callback) {
        String url = urlData.getUrl();
        Response.Listener successLister = getSuccessListener(callback);
        Response.ErrorListener errorListener = getErrorListener(callback);
        JsonObjectRequestVolley jorv = new JsonObjectRequestVolley(url, parseParameter(rpList), successLister, errorListener);

        RequestManagerVolleyImpl rmvi = (RequestManagerVolleyImpl) rmi;
        rmvi.addRequestQuneue(jorv);
    }

    public void doHttpPostJsonObject(RequestManagerInterface rmi, URLData urlData, List<RequestParameter> rpList, final RequestCallback callback) {
        String url = urlData.getUrl();
        Response.Listener successLister = getSuccessListener(callback);
        Response.ErrorListener errorListener = getErrorListener(callback);
        JsonObjectRequestVolley jorv = new JsonObjectRequestVolley(Request.Method.POST,url, parseParameter(rpList), successLister, errorListener);

        RequestManagerVolleyImpl rmvi = (RequestManagerVolleyImpl) rmi;
        rmvi.addRequestQuneue(jorv);
    }

    @NonNull
    private Response.ErrorListener getErrorListener(final RequestCallback callback) {
        return new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(null!=callback)
                    callback.onFail(error.getMessage());
                }
            };
    }

    @NonNull
    private Response.Listener getSuccessListener(final RequestCallback callback) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(null!=callback)
                callback.onSuccess(response.toString());
            }
        };
    }
    private JSONObject parseParameter(List<RequestParameter> rpList) {
        JSONObject jo = new JSONObject();
        if(null!=rpList && !rpList.isEmpty()){
            for (RequestParameter rp : rpList) {
                if(null!=rp.getValue() && null!=rp.getName()){
                    try {
                        jo.put(rp.getName(),rp.getValue());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return jo;
    }
}
