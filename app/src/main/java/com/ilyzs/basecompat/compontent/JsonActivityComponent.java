package com.ilyzs.basecompat.compontent;

import com.ilyzs.basecompat.activity.JsonActivity;
import com.ilyzs.basecompat.module.JsonHelperModule;
import com.ilyzs.basecompat.util.JsonHelper;

import dagger.Component;

/**
 * Created by zhangshu on 2017/11/25.
 */

@Component(modules = {JsonHelperModule.class})
public interface JsonActivityComponent{
    void inject(JsonActivity jsonActivity);
}
