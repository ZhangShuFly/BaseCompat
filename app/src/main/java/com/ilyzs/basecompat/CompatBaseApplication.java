package com.ilyzs.basecompat;

import android.app.Application;

import com.github.moduth.blockcanary.BlockCanary;
import com.ilyzs.basecompat.util.CompatBaseBlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by zhangshu on 2017/11/16.
 */

public class CompatBaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        BlockCanary.install(this,new CompatBaseBlockCanaryContext()).start();


    }
}
