package com.ilyzs.basecompat.compontent;

import com.ilyzs.basecompat.module.UserModule;
import com.ilyzs.basecompat.viewmodel.UserProfileViewModel;

import dagger.Subcomponent;

/**
 * Created by zhangshu on 2017/11/23.
 */

@Subcomponent(modules = {UserModule.class})
public interface UserComponent {

    void inject(UserProfileViewModel viewModel);
}
