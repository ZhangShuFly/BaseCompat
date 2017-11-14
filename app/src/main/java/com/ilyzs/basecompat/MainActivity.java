package com.ilyzs.basecompat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ilyzs.libnetwork.AppBaseActivity;

public class MainActivity extends AppBaseActivity {

    private Button glideBtn;

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
        glideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void loadData() {

    }
}
