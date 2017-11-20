package com.ilyzs.basecompat.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ilyzs.basecompat.bean.CommonJsonBean;
import com.ilyzs.basecompat.bean.User;
import com.ilyzs.basecompat.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created by zhangshu on 2017/11/19.
 */

public class UserProfileViewModel extends ViewModel {

    private LiveData<CommonJsonBean<User>> user;
    private UserRepository repository;
    private String userId;

    @Inject
    public UserProfileViewModel(UserRepository repository){
        this.repository = repository;
    }

    public void setUserId(String userId) {
        if(null!=user){
            return;
        }
        user = repository.getUser(userId);
    }

    public LiveData<CommonJsonBean<User>> getUser() {
        return user;
    }
}
