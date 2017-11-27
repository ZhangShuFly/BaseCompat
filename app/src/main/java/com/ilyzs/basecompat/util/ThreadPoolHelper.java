package com.ilyzs.basecompat.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangshu on 2017/11/25.
 */

public class ThreadPoolHelper {
    private  static ExecutorService executorService;
    private void init(){
        if(null == executorService){
            executorService = Executors.newCachedThreadPool();
        }
    }

    public ThreadPoolHelper(){
        init();
    }

    public ExecutorService getExcutor(){
        return executorService;
    }
}
