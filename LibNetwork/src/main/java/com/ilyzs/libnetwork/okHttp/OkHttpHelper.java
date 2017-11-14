package com.ilyzs.libnetwork.okHttp;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.ilyzs.libnetwork.util.RequestCallback;
import com.ilyzs.libnetwork.util.RequestManagerInterface;
import com.ilyzs.libnetwork.util.RequestParameter;
import com.ilyzs.libnetwork.util.URLData;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zs.
 */

public class OkHttpHelper {

    private OkHttpClient okHttpClient;
    private Handler handler;
    private static OkHttpHelper okHttpHelper;

    private OkHttpHelper() {
        okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS);
        handler = new Handler(Looper.getMainLooper());
   }

    private static OkHttpHelper getInstance(){
        synchronized (OkHttpHelper.class){
            if(null == okHttpHelper){
                okHttpHelper = new OkHttpHelper();
            }
        }
        return okHttpHelper;
    }

    /**
     * 异步请求get
     */
    public static void doHttpGet(RequestManagerInterface rmi, URLData urlData, List<RequestParameter> rpList, RequestCallback callback) {
        String url = urlData.getUrl();
        getInstance().inner_doHttpGet(rmi,url,rpList,callback);
    }

    /**
     * 异步请求post
     */
    public static void doHttpPost(RequestManagerInterface rmi,URLData urlData, List<RequestParameter> rpList, RequestCallback callback) {
        String url = urlData.getUrl();
        getInstance().inner_doHttpPost(rmi,url,rpList,callback);
    }

    private void inner_doHttpGet(RequestManagerInterface rmi,String url, List<RequestParameter> rpList, final RequestCallback callback) {
        url = urlJoinParams(url,rpList);
        final Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue( getResponseCallback(callback));

        RequestManagerOkHttpImpl rmoi = (RequestManagerOkHttpImpl)rmi;
        rmoi.addRequestQuneue(call);
    }

    private void inner_doHttpPost(RequestManagerInterface rmi,String url, List<RequestParameter> rpList, final RequestCallback callback) {
        RequestBody requestBody = getRequestBody(rpList);
        final  Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(getResponseCallback(callback));

        RequestManagerOkHttpImpl rmoi = (RequestManagerOkHttpImpl)rmi;
        rmoi.addRequestQuneue(call);
    }

    @NonNull
    private Callback getResponseCallback(final RequestCallback callback) {
        return new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(null!=callback)
                            callback.onFail(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (null != callback) {
                            try {
                                if (response.isSuccessful()) {
                                    callback.onSuccess(response.body().string());
                                } else {
                                    callback.onFail(response.body().string());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
    }


    private String urlJoinParams(String url,List<RequestParameter> rpList){
       StringBuilder urlSb = new StringBuilder(url);
        boolean isFirst = true;
        if(null!=rpList){
            for (RequestParameter rp : rpList) {
                if(null!=rp.getValue() && null!=rp.getName()){
                    if(isFirst && !url.endsWith("?")){
                        urlSb.append("?");
                    }else{
                        urlSb.append("&");
                    }
                    urlSb.append(rp.getName());
                    urlSb.append("=");
                    urlSb.append(rp.getValue());
                }
            }
        }
        return  urlSb.toString();
    }

    @NonNull
    private RequestBody getRequestBody(List<RequestParameter> rpList) {
        FormBody.Builder builder = new FormBody.Builder();
        if(null!=rpList && !rpList.isEmpty()){
            for (RequestParameter rp : rpList) {
                if(null!=rp.getName()){
                    builder.add(rp.getName(),null!=rp.getValue()?rp.getValue():"");
                }
            }
        }
        return builder.build();
    }

}
