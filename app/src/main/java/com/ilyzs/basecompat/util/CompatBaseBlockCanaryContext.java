package com.ilyzs.basecompat.util;


import com.github.moduth.blockcanary.BlockCanaryContext;

/**
 * Created by zhangshu on 2017/11/18.
 */

public class CompatBaseBlockCanaryContext extends BlockCanaryContext {

    public String provideUid() {
        return "CompatBaseUid";
    }

    @Override
    public String providePath() {
        return "/";
    }

    @Override
    public boolean displayNotification() {
        return true;
    }


}
