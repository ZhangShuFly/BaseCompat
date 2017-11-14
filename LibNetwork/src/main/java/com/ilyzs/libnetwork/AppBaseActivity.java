package com.ilyzs.libnetwork;

import android.os.Bundle;

import com.ilyzs.libbase.BaseActivity;
import com.ilyzs.libnetwork.okHttp.RequestManagerOkHttpImpl;
import com.ilyzs.libnetwork.retrofit.RequestManagerRetrofitImpl;
import com.ilyzs.libnetwork.util.ConfigUtil;
import com.ilyzs.libnetwork.util.RequestManagerInterface;
import com.ilyzs.libnetwork.volley.RequestManagerVolleyImpl;

/**
 * Created by zs .
 */

public abstract class AppBaseActivity extends BaseActivity {

    public RequestManagerInterface rmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeNetType(ConfigUtil.netType);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(null!=rmi){
            rmi.cancelAllRequest();
        }
    }

    protected void changeNetType(String netType){
        ConfigUtil.netType = netType;
        if(null!=rmi){
            rmi.cancelAllRequest();
        }
        if("OKHttp".equals(ConfigUtil.netType)){
            rmi = new RequestManagerOkHttpImpl();
        }else if("Retrofit".equals(ConfigUtil.netType)){
            rmi = new RequestManagerRetrofitImpl();
        }else{
            rmi = new RequestManagerVolleyImpl(this);
        }
    }
}
