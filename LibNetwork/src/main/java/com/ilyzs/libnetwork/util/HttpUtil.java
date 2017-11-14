package com.ilyzs.libnetwork.util;

import android.content.Context;

import com.ilyzs.libnetwork.okHttp.OkHttpHelper;
import com.ilyzs.libnetwork.retrofit.RetrofitHelper;
import com.ilyzs.libnetwork.volley.VolleyHelper;

import java.util.List;

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

    public static void doHttp(RequestManagerInterface rmi,Context context, String urlKey, List<RequestParameter> parameter, RequestCallback callback){
        if("OKHttp".equals(ConfigUtil.netType)){
            getInstance().doOkHttpHttp(rmi,context,urlKey,parameter,callback);
        }else if("Retrofit".equals(ConfigUtil.netType)){
            getInstance().doRetrofitHttp(rmi,context,urlKey,parameter,callback);
        }  else{
            getInstance().doVolleyHttp(rmi,context,urlKey,parameter,callback);
        }
    }

    private void doRetrofitHttp(RequestManagerInterface rmi,Context context, String urlKey, List<RequestParameter> parameter, RequestCallback callback){
        URLData urlData = URLDataManager.findURL(context,urlKey);
        if("post".equals(urlData.getNetType())){
            RetrofitHelper.doHttpPost(rmi,urlData,parameter,callback);
        }else{
            RetrofitHelper.doHttpGet(rmi,urlData,parameter,callback);
        }
    }

    private void doVolleyHttp(RequestManagerInterface rmi,Context context, String urlKey, List<RequestParameter> parameter, RequestCallback callback){
        URLData urlData = URLDataManager.findURL(context,urlKey);
        if("post".equals(urlData.getNetType())){
            new VolleyHelper(context).doHttpPostJsonObject(rmi,urlData,parameter,callback);
        }else{
            new VolleyHelper(context).doHttpGetJsonObject(rmi,urlData,parameter,callback);
        }
    }

    private void doOkHttpHttp(RequestManagerInterface rmi,Context context, String urlKey, List<RequestParameter> parameter, RequestCallback callback){
        URLData urlData = URLDataManager.findURL(context,urlKey);
        if("post".equals(urlData.getNetType())){
            OkHttpHelper.doHttpPost(rmi,urlData,parameter,callback);
        }else{
            OkHttpHelper.doHttpGet(rmi,urlData,parameter,callback);
        }
    }

}
