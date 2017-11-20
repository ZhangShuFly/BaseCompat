package com.ilyzs.basecompat.gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zhangshu on 2017/11/18.
 */

public class ParameterizedTypeImpl implements ParameterizedType {

    private final Class clazz;
    private final Type[] args;

    public  ParameterizedTypeImpl(Class clazz, Type... args){
        this.clazz = clazz;
        this.args = args;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return  args;
    }

    @Override
    public Type getRawType() {
        return clazz;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
