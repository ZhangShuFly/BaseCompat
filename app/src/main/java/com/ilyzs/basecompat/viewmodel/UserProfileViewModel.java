package com.ilyzs.basecompat.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ilyzs.basecompat.base.CompatBaseApplication;
import com.ilyzs.basecompat.bean.User;
import com.ilyzs.basecompat.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created by zhangshu on 2017/11/19.
 */

public class UserProfileViewModel extends ViewModel {

    private LiveData<User> user;

    @Inject
    public UserRepository repository;

    public UserProfileViewModel() {
        CompatBaseApplication.get().getAppCompontent().sub().inject(this);
    }

    public void setUserId(String userId) {
        if (null != user) {
            return;
        }
        user = repository.getUser(userId);
    }

    public LiveData<User> getUser() {
        return user;
    }
}
