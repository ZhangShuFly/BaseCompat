package com.ilyzs.basecompat.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.ilyzs.basecompat.bean.CommonJsonBean;
import com.ilyzs.basecompat.bean.User;
import com.ilyzs.basecompat.util.CacheUtil;
import com.ilyzs.basecompat.util.ConfigUtil;
import com.ilyzs.basecompat.util.JsonUtil;
import com.ilyzs.libnetwork.util.HttpUtil;
import com.ilyzs.libnetwork.util.RequestCallback;
import com.ilyzs.libnetwork.util.RequestParameter;
import com.ilyzs.libnetwork.util.URLData;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;

/**
 * Created by zhangshu on 2017/11/19.
 */

public class UserRepository {

    public LiveData<CommonJsonBean<User>> getUser(final String userId){
        final MutableLiveData<CommonJsonBean<User>> liveData = new MutableLiveData<>();

        final URLData urlData = new URLData();
        urlData.setNetType("get");
        urlData.setUrl("");

        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setName("userId");
        requestParameter.setValue(userId);

        final CacheUtil cacheUtil = new CacheUtil("",1);
        String cache = cacheUtil.getJsonCache(urlData.getUrl()+"?userId="+userId);
        if(null!=cache){
            CommonJsonBean cjb = JsonUtil.getInstance().jsonToBean(cache,User.class);
            MutableLiveData<CommonJsonBean<User>> liveDataCache = new MutableLiveData<>();
            liveDataCache.setValue(cjb);
            return liveDataCache;
        }

        List<RequestParameter> rpList = new ArrayList<>();
        rpList.add(requestParameter);

        HttpUtil.doHttp(null, urlData, rpList, new RequestCallback() {
            @Override
            public void onSuccess(String content) {
                CommonJsonBean cjb = JsonUtil.getInstance().jsonToBean(content,User.class);
                liveData.setValue(cjb);

                cacheUtil.putJsonCache(urlData.getUrl()+"?userId="+userId,content);
            }

            @Override
            public void onFail(String failMsg) {

            }
        });

        return liveData;
    }

}
