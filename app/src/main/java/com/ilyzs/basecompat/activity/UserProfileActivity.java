package com.ilyzs.basecompat.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.ilyzs.basecompat.R;
import com.ilyzs.basecompat.fragment.UserProfileFragment;
import com.ilyzs.libbase.BaseActivity;

public class UserProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initPara() {

    }

    @Override
    public void loadView() {
        setContentView(R.layout.activity_user_profile);
    }

    @Override
    public void loadData() {
        FragmentManager fm = getSupportFragmentManager();
        UserProfileFragment upf = new UserProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("uid", "1");
        upf.setArguments(bundle);
        fm.beginTransaction().replace(R.id.fragment_user_profile, upf).commit();
    }
}
