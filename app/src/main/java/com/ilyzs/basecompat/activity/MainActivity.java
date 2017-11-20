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

public class MainActivity extends CompatBaseActivity {

    private Button glideBtn, leakCanaryBtn, blockCanaryBtn, jsonBtn;

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

        glideBtn = (Button) findViewById(R.id.btn_main_glide);
        leakCanaryBtn = (Button) findViewById(R.id.btn_main_LeakCanary);
        blockCanaryBtn = (Button) findViewById(R.id.btn_main_BlockCanary);
        jsonBtn = (Button) findViewById(R.id.btn_main_json);

        glideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });


        leakCanaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LeakCanaryActivity.class);
                startActivity(intent);
            }
        });

        blockCanaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BlockCanaryActivity.class);
                startActivity(intent);
            }
        });

        jsonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JsonActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
