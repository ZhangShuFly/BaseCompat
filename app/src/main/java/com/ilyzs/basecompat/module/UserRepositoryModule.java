package com.ilyzs.basecompat.module;

import com.ilyzs.basecompat.util.ThreadPoolHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangshu on 2017/11/25.
 */

@Module
public class UserRepositoryModule {

    @Provides
    public ThreadPoolHelper providerThreadPoolHelper(){
        return new ThreadPoolHelper();
    }
}
