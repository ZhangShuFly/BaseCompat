package com.ilyzs.basecompat.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ilyzs.basecompat.bean.CommonJsonBean;
import com.ilyzs.basecompat.gson.ParameterizedTypeImpl;
import com.ilyzs.basecompat.util.JsonHelper;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zhangshu on 2017/11/18.
 */

public class GsonUtil implements JsonHelper{

    private static Gson gson;

    public GsonUtil(){
        if(null==gson){
            gson = new Gson();
        }
    }

    public  <T> CommonJsonBean<T> jsonToCommonBean(String jsonString, Class clazz){
        Type type = new ParameterizedTypeImpl(CommonJsonBean.class,clazz);
        return gson.fromJson(jsonString,type);
    }

    public <T> CommonJsonBean<T> jsonArrayToCommonBean(String jsonString, Class clazz){
        Type listType = new ParameterizedTypeImpl(List.class,new Class[]{clazz});
        Type type = new ParameterizedTypeImpl(CommonJsonBean.class,new Type[]{listType});
        return gson.fromJson(jsonString,type);
    }

    public String commonBeanToJson(CommonJsonBean bean, Class clazz){
        Type type = new ParameterizedTypeImpl(CommonJsonBean.class,clazz);
        return gson.toJson(bean,type);
    }

    public String commonBeanToJsonArray(CommonJsonBean bean, Class clazz){
        Type listType = new ParameterizedTypeImpl(List.class,new Class[]{clazz});
        Type type = new ParameterizedTypeImpl(CommonJsonBean.class,new Type[]{listType});
        return gson.toJson(bean,type);
    }

    public  String beanToJson(Object object,Class clazz){
        return gson.toJson(object,clazz);
    }

    public  <T> T jsonToBean(String jsonString, Class<T> clazz){
        return gson.fromJson(jsonString,clazz);
    }

    public <T> List<T> jsonToList(String jsonString, Class<T> clazz){
        Type type = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        return gson.fromJson(jsonString,type);
    }


}
