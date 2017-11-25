package com.ilyzs.basecompat.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ilyzs.basecompat.R;
import com.ilyzs.basecompat.bean.User;
import com.ilyzs.basecompat.viewmodel.UserProfileViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {


    private static final java.lang.String UID_KEY = "uid";
    private UserProfileViewModel viewModel;
    private TextView infoTv;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String userId = getArguments().getString(UID_KEY);
        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.setUserId(userId);

        viewModel.getUser().observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if(null!=user){
                    infoTv.setText("id:"+user.getId()+",name:"+user.getName()+",age:"+user.getAge());
                }
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        infoTv = view.findViewById(R.id.tv_user_profile_info);

        return view;
    }



}
