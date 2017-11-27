package com.ilyzs.basecompat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import com.ilyzs.basecompat.R;
import com.ilyzs.basecompat.base.CompatBaseActivity;
import com.ilyzs.basecompat.util.LeakCanaryTest;
import com.ilyzs.libnetwork.AppBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends CompatBaseActivity {

    //Just for test, it does not help;
    @BindView(R.id.btn_main_glide)
    Button glideBtn;
    @BindView(R.id.btn_main_LeakCanary)
    Button leakCanaryBtn;
    @BindView(R.id.btn_main_BlockCanary)
    Button blockCanaryBtn;
    @BindView(R.id.btn_main_json)
    Button jsonBtn;
    @BindView(R.id.btn_main_user_profile)
    Button accBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initPara() {

    }

    @Override
    public void loadView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @Override
    public void loadData() {

    }

    @OnClick(R.id.btn_main_glide)
    void glideBtnClick() {
        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_main_LeakCanary)
    void leakCanaryBtnClick() {
        Intent intent = new Intent(MainActivity.this, LeakCanaryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_main_BlockCanary)
    void blockCanaryBtnClick() {
        Intent intent = new Intent(MainActivity.this, BlockCanaryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_main_json)
    void jsonBtnClick() {
        Intent intent = new Intent(MainActivity.this, JsonActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_main_user_profile)
    void accBtnClick() {
        Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
