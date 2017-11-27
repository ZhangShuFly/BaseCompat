package com.ilyzs.basecompat.module;

import com.ilyzs.basecompat.gson.GsonUtil;
import com.ilyzs.basecompat.util.JsonHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangshu on 2017/11/25.
 */

@Module
public class JsonHelperModule {

    @Provides
    JsonHelper provideJsonHelper(){
            return new GsonUtil();
    }
}
