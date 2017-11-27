package com.ilyzs.basecompat.util;

import android.content.Context;

/**
 * Created by zhangshu on 2017/11/16.
 */

public class LeakCanaryTest {

    private static LeakCanaryTest instance;
    private Context context;

    private LeakCanaryTest(Context context){
        this.context = context;
    }

    public  static LeakCanaryTest getInstance(Context context){
            if(null == instance){
                synchronized (LeakCanaryTest.class){
                    instance = new LeakCanaryTest(context);
                }
            }

            return  instance;
    }
}
