package com.ilyzs.basecompat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ilyzs.basecompat.R;
import com.ilyzs.basecompat.util.LeakCanaryTest;

public class LeakCanaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);

        LeakCanaryTest.getInstance(this);
    }
}
