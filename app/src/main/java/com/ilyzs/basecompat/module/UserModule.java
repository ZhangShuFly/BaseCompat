package com.ilyzs.basecompat.module;


import android.content.Context;

import com.ilyzs.basecompat.repository.UserRepository;
import com.ilyzs.basecompat.util.CompatDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangshu on 2017/11/23.
 */
@Module
public class UserModule {

    @Provides
   public UserRepository providerUserRepository(Context context){
       return new UserRepository(context) ;
   }
}
