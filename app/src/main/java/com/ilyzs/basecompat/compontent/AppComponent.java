package com.ilyzs.basecompat.compontent;

import com.ilyzs.basecompat.module.AppModule;

import dagger.Component;

/**
 * Created by zhangshu on 2017/11/23.
 */

@Component(modules = {AppModule.class})
public interface AppComponent {

    UserComponent sub();

}
