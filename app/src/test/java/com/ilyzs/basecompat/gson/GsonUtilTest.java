package com.ilyzs.basecompat.gson;

import com.ilyzs.basecompat.bean.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangshu on 2017/11/25.
 */
public class GsonUtilTest {
    GsonUtil gsonUtil;
    @Before
    public void setUp() throws Exception {
         gsonUtil = new GsonUtil();
    }

    @Test
    public void beanToJson() throws Exception {
        User user = new User();
        user.setId(222);
        user.setName("aaa");
        user.setAge(33);
        String json =  gsonUtil.beanToJson(user,user.getClass());
        System.out.print(json);
        gsonUtil.jsonToBean(json,User.class);
    }

}