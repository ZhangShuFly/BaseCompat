package com.ilyzs.basecompat.util;

import com.ilyzs.basecompat.gson.GsonUtil;
import com.ilyzs.basecompat.bean.CommonJsonBean;

/**
 * Created by zhangshu on 2017/11/18.
 */

public class JsonUtil {

    private static JsonUtil instance;

    private JsonUtil (){

    }

    public static JsonUtil getInstance() {
       if(null == instance){
           synchronized (JsonUtil.class){
               instance = new JsonUtil();
           }
       }
        return instance;
    }

    public  <T> CommonJsonBean<T> jsonToCommonBean(String jsongString, Class<T> clazz){
        return GsonUtil.getInstance().jsonToBean(jsongString,clazz);
    }

    public String commonBeanToJson(CommonJsonBean bean,Class clazz){
        return GsonUtil.getInstance().beanToJson(bean,clazz);
    }
}
