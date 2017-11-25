package com.ilyzs.basecompat.base;

import android.app.Application;

import com.github.moduth.blockcanary.BlockCanary;
import com.ilyzs.basecompat.compontent.AppComponent;
import com.ilyzs.basecompat.compontent.DaggerAppComponent;
import com.ilyzs.basecompat.module.AppModule;
import com.ilyzs.basecompat.util.CompatBaseBlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by zhangshu on 2017/11/16.
 */

public class CompatBaseApplication extends Application {
    private static AppComponent appComponent;
    private static CompatBaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        BlockCanary.install(this, new CompatBaseBlockCanaryContext()).start();
        if(null == instance){
            instance = this;
        }
    }

    public static CompatBaseApplication get(){
        return instance;
    }

    private void setupAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppCompontent() {
        if (null == appComponent) {
            setupAppComponent();
        }
        return appComponent;
    }
}
