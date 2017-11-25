package com.ilyzs.basecompat.module;

import android.content.Context;

import com.ilyzs.basecompat.base.CompatBaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangshu on 2017/11/23.
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(CompatBaseApplication application){
        this.context = application;
    }

    @Provides
    public Context providerApplication() {
        return context;
    }
}
