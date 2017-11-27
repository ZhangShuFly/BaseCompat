package com.ilyzs.basecompat.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ilyzs.basecompat.R;
import com.ilyzs.basecompat.base.CompatBaseActivity;
import com.ilyzs.basecompat.bean.AddressBean;
import com.ilyzs.basecompat.bean.CommonJsonBean;
import com.ilyzs.basecompat.compontent.DaggerJsonActivityComponent;
import com.ilyzs.basecompat.util.JsonHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JsonActivity extends CompatBaseActivity {


    @BindView(R.id.tv_json_input)
    TextView inputTv;
    @BindView(R.id.tv_json_output)
    TextView outputTv;

    @Inject
    JsonHelper jsonHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initPara() {

    }

    @Override
    public void loadView() {
        setContentView(R.layout.activity_json);

        DaggerJsonActivityComponent.builder().build().inject(this);

        ButterKnife.bind(this);


    }

    @Override
    public void loadData() {

    }

    @OnClick(R.id.btn_json_btoj)
    void bTojBtnClick() {
        CommonJsonBean<AddressBean> cjb = getTestBean();
        StringBuffer result = getTestString((CommonJsonBean<AddressBean>) cjb);

        inputTv.setText(result);

        String json = jsonHelper.commonBeanToJson(cjb, AddressBean.class);
        outputTv.setText("to:\n\n" + json);
    }

    @OnClick(R.id.btn_json_jtob)
    void jTobbBtnClick() {
        String jsonText = "{\"code\":\"1\",\"message\":\"success\",\"data\":{\"street\":\"科技园路.\",\"city\":\"北京\",\"country\":\"中国\"}}";

        inputTv.setText("from:\n\n" + jsonText);

        CommonJsonBean<AddressBean> cjb = jsonHelper.jsonToCommonBean(jsonText, AddressBean.class);

        StringBuffer result = getTestString(cjb);

        outputTv.setText("to:\n\n" + result);
    }


    @NonNull
    private StringBuffer getTestString(CommonJsonBean<AddressBean> cjb) {
        StringBuffer result = new StringBuffer();
        result.append("code:" + cjb.code + "\n");
        result.append("message:" + cjb.message + "\n");
        result.append("country:" + cjb.data.country + "\n");
        result.append("city:" + cjb.data.city + "\n");
        result.append("street:" + cjb.data.street);
        return result;
    }

    @NonNull
    private CommonJsonBean<AddressBean> getTestBean() {
        CommonJsonBean<AddressBean> cjb = new CommonJsonBean<>();
        cjb.code = "0";
        cjb.message = "fail";
        AddressBean ab = new AddressBean();
        ab.country = "中国";
        ab.city = "西安";
        ab.street = "子午大道";
        cjb.data = ab;
        return cjb;
    }


}
