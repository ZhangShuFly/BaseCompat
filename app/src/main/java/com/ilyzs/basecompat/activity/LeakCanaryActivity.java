package com.ilyzs.basecompat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ilyzs.basecompat.R;
import com.ilyzs.basecompat.base.CompatBaseActivity;
import com.ilyzs.basecompat.util.LeakCanaryTest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeakCanaryActivity extends CompatBaseActivity {

    @BindView(R.id.tv_leak_test)
    TextView leakTestTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initPara() {

    }

    @Override
    public void loadView() {
        setContentView(R.layout.activity_leak_canary);
        ButterKnife.bind(this);

        boolean hasTwoPanes = getResources().getBoolean(R.bool.has_two_panes);
        leakTestTv.setText(getString(R.string.leak_tip)+"\nhasTwoPanes:"+hasTwoPanes);

        LeakCanaryTest.getInstance(this);
    }

    @Override
    public void loadData() {

    }
}
