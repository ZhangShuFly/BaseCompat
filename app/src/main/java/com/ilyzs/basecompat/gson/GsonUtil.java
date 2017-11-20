package com.ilyzs.basecompat.gson;

import com.google.gson.Gson;
import com.ilyzs.basecompat.bean.CommonJsonBean;

import java.lang.reflect.Type;

/**
 * Created by zhangshu on 2017/11/18.
 */

public class GsonUtil {

    private static GsonUtil instance;

    private Gson gson;
    private GsonUtil(){
        gson = new Gson();
    }

    public static GsonUtil getInstance(){
        if (instance == null) {
            synchronized (GsonUtil.class){
                instance = new GsonUtil();
            }
        }
        return instance;
    }

    public  <T> CommonJsonBean<T> jsonToBean(String jsonString, Class clazz){
        Type type = new ParameterizedTypeImpl(CommonJsonBean.class,clazz);
        return gson.fromJson(jsonString,type);
    }

    public String beanToJson(CommonJsonBean bean, Class clazz){
        Type type = new ParameterizedTypeImpl(CommonJsonBean.class,clazz);
        return gson.toJson(bean,type);
    }
}
