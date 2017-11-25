package com.ilyzs.libnetwork.util;

import android.content.Context;

import com.ilyzs.libnetwork.okHttp.OkHttpHelper;
import com.ilyzs.libnetwork.retrofit.RetrofitHelper;
import com.ilyzs.libnetwork.volley.VolleyHelper;

import java.util.List;

import retrofit2.http.Url;

/**
 * 网络请求工具类
 * Created by zs
 */

public class HttpUtil {

    private static volatile HttpUtil httpUtil;

    private static HttpUtil getInstance(){
        synchronized (HttpUtil.class) {
            if (null == httpUtil) {
                httpUtil = new HttpUtil();
            }
        }
        return httpUtil;
    }

    public static void doHttp(RequestManagerInterface rmi,URLData urlData, List<RequestParameter> parameter, RequestCallback callback){
        if("OKHttp".equals(ConfigUtil.netType)){
            getInstance().doOkHttpHttp(rmi,urlData,parameter,callback);
        }else if("Retrofit".equals(ConfigUtil.netType)){
            getInstance().doRetrofitHttp(rmi,urlData,parameter,callback);
        }  else{
            getInstance().doVolleyHttp(rmi,urlData,parameter,callback);
        }
    }

    public static void doHttp(URLData urlData, List<RequestParameter> parameter, RequestCallback callback){
        if("OKHttp".equals(ConfigUtil.netType)){
            getInstance().doOkHttpHttp(null,urlData,parameter,callback);
        }else if("Retrofit".equals(ConfigUtil.netType)){
            getInstance().doRetrofitHttp(null,urlData,parameter,callback);
        }  else{
            getInstance().doVolleyHttp(null,urlData,parameter,callback);
        }
    }


    private void doRetrofitHttp(RequestManagerInterface rmi,URLData urlData, List<RequestParameter> parameter, RequestCallback callback){
        if("post".equals(urlData.getNetType())){
            RetrofitHelper.doHttpPost(rmi,urlData,parameter,callback);
        }else{
            RetrofitHelper.doHttpGet(rmi,urlData,parameter,callback);
        }
    }

    private void doVolleyHttp(RequestManagerInterface rmi,URLData urlData, List<RequestParameter> parameter, RequestCallback callback){
        if("post".equals(urlData.getNetType())){
            new VolleyHelper().doHttpPostJsonObject(rmi,urlData,parameter,callback);
        }else{
            new VolleyHelper().doHttpGetJsonObject(rmi,urlData,parameter,callback);
        }
    }

    private void doOkHttpHttp(RequestManagerInterface rmi, URLData urlData, List<RequestParameter> parameter, RequestCallback callback){
        if("post".equals(urlData.getNetType())){
            OkHttpHelper.doHttpPost(rmi,urlData,parameter,callback);
        }else{
            OkHttpHelper.doHttpGet(rmi,urlData,parameter,callback);
        }
    }

}
