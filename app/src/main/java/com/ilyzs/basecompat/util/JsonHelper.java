package com.ilyzs.basecompat.util;

import com.ilyzs.basecompat.bean.CommonJsonBean;
import com.ilyzs.basecompat.gson.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zhangshu on 2017/11/25.
 */

public interface JsonHelper {

    public  <T> CommonJsonBean<T> jsonToCommonBean(String jsonString, Class clazz);

    public <T> CommonJsonBean<T> jsonArrayToCommonBean(String jsonString, Class clazz);

    public String commonBeanToJson(CommonJsonBean bean, Class clazz);

    public String commonBeanToJsonArray(CommonJsonBean bean, Class clazz);

    public  String beanToJson(Object object,Class clazz);

    public  <T> T jsonToBean(String jsonString, Class<T> clazz);

    public <T> List<T> jsonToList(String jsonString, Class<T> clazz);

}
