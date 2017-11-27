package com.ilyzs.basecompat.compontent;

import com.ilyzs.basecompat.module.JsonHelperModule;
import com.ilyzs.basecompat.module.UserRepositoryModule;
import com.ilyzs.basecompat.repository.UserRepository;

import dagger.Component;

/**
 * Created by zhangshu on 2017/11/25.
 */

@Component(modules = {UserRepositoryModule.class, JsonHelperModule.class})
public interface UserRepositoryComponent {
        void inject(UserRepository userRepository);
}
