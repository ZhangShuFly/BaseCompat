package com.ilyzs.libnetwork.util;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 网络请求的参数
 * Created by zs .
 */

public class RequestParameter implements Serializable ,Comparable<Object>{

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        RequestParameter rp = (RequestParameter)o;
        if(0 == name.compareTo(rp.name) && 0 == value.compareTo(rp.value)){
            return 0;
        }
        return 1;
    }

    public boolean equals(Object o){
        if(o instanceof RequestParameter){
            RequestParameter rp = (RequestParameter)o;
            return  name.equals(rp.getName()) && value.equals(rp.getValue());
        }
        return false;
    }
}
