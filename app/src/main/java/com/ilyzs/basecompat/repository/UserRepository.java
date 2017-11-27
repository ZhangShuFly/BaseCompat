package com.ilyzs.basecompat.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ilyzs.basecompat.bean.CommonJsonBean;
import com.ilyzs.basecompat.bean.User;
import com.ilyzs.basecompat.compontent.DaggerUserRepositoryComponent;
import com.ilyzs.basecompat.util.CompatDatabase;
import com.ilyzs.basecompat.util.DBHelper;
import com.ilyzs.basecompat.util.JsonHelper;
import com.ilyzs.basecompat.util.ThreadPoolHelper;
import com.ilyzs.libnetwork.util.HttpUtil;
import com.ilyzs.libnetwork.util.RequestCallback;
import com.ilyzs.libnetwork.util.RequestParameter;
import com.ilyzs.libnetwork.util.URLData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhangshu on 2017/11/19.
 */

public class UserRepository {

    private CompatDatabase db;

    @Inject
    public ThreadPoolHelper threadPoolHelper;

    @Inject
    public JsonHelper jsonHelper;

    private UserRepository() {

    }

    public UserRepository(Context context) {
        db = DBHelper.getInstance(context).getDb();
        DaggerUserRepositoryComponent.builder(). build().inject(this);
    }

    public LiveData<User> getUser(final String userId) {
        LiveData<User> data = db.userDao().load(userId);
        if(null == data.getValue()){
            refreshUser(userId);
        }
        return data;
    }

    private void refreshUser(final String userId) {
        String url = "";
        URLData urlData = getUrlData(url);

        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setName("userId");
        requestParameter.setValue(userId);

        List<RequestParameter> rpList = new ArrayList<>();
        rpList.add(requestParameter);

        HttpUtil.doHttp(urlData, rpList, new RequestCallback() {
            @Override
            public void onSuccess(String content) {
                //CommonJsonBean<User> cjb = jsonHelper.jsonToBean(content, User.class);
                final User user = new User();
                user.setAge(22);
                user.setName("ZhangShu");
                user.setId(1);
                threadPoolHelper.getExcutor().execute(new Runnable() {
                    @Override
                    public void run() {
                         db.userDao().save(user);
                    }
                });


            }

            @Override
            public void onFail(String failMsg) {
                CommonJsonBean<User> cjb = new CommonJsonBean<>();
                cjb.code = "1";
                cjb.message = failMsg;
            }
        });

    }

    @NonNull
    private URLData getUrlData(String url) {
        final URLData urlData = new URLData();
        urlData.setNetType("get");
        urlData.setUrl("http://www.weather.com.cn/data/cityinfo/101010100.html");
        return urlData;
    }

}
