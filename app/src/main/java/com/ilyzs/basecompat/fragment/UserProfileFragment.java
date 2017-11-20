package com.ilyzs.basecompat.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilyzs.basecompat.R;
import com.ilyzs.basecompat.bean.CommonJsonBean;
import com.ilyzs.basecompat.bean.User;
import com.ilyzs.basecompat.viewmodel.UserProfileViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {


    private static final java.lang.String UID_KEY = "uid";
    private UserProfileViewModel viewModel;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String userId = getArguments().getString(UID_KEY);
        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.setUserId(userId);

        viewModel.getUser().observe(this, new Observer<CommonJsonBean<User>>() {
            @Override
            public void onChanged(@Nullable CommonJsonBean<User> commonJsonBean) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

}
