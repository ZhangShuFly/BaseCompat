package com.ilyzs.basecompat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ilyzs.basecompat.R;
import com.ilyzs.basecompat.bean.AddressBean;
import com.ilyzs.basecompat.bean.CommonJsonBean;
import com.ilyzs.basecompat.util.JsonUtil;
import com.ilyzs.libnetwork.AppBaseActivity;

public class JsonActivity extends AppBaseActivity {

    private Button bTojBtn,jTobBtn;
    private TextView inputTv, outputTv;

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
        bTojBtn = (Button) findViewById(R.id.btn_json_btoj);
        jTobBtn = (Button) findViewById(R.id.btn_json_jtob);
        inputTv = (TextView) findViewById(R.id.tv_json_input);
        outputTv = (TextView) findViewById(R.id.tv_json_output);

        bTojBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonJsonBean<AddressBean> cjb = new CommonJsonBean<>();
                cjb.code = "0";
                cjb.message = "fail";
                AddressBean ab = new AddressBean();
                ab.country = "中国";
                ab.city = "西安";
                ab.street = "子午大道";
                cjb.data = ab;

                StringBuffer result  = new StringBuffer("from:\n\n");
                result.append("code:"+cjb.code+"\n");
                result.append("message:"+cjb.message+"\n");
                result.append("country:"+cjb.data.country+"\n");
                result.append("city:"+cjb.data.city+"\n");
                result.append("street:"+cjb.data.street);

                inputTv.setText(result);

                String json =  JsonUtil.getInstance().beanToJson(cjb,AddressBean.class);
                outputTv.setText("to:\n\n"+json);
            }
        });

        jTobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonText = "{\"code\":\"1\",\"message\":\"success\",\"data\":{\"street\":\"科技园路.\",\"city\":\"北京\",\"country\":\"中国\"}}";
                inputTv.setText("from:\n\n"+jsonText);
                CommonJsonBean<AddressBean> cjb = JsonUtil.getInstance().jsonToBean(jsonText, AddressBean.class);

                StringBuffer result  = new StringBuffer();
                result.append("code:"+cjb.code+"\n");
                result.append("message:"+cjb.message+"\n");
                result.append("country:"+cjb.data.country+"\n");
                result.append("city:"+cjb.data.city+"\n");
                result.append("street:"+cjb.data.street);

                outputTv.setText("to:\n\n"+result);

            }
        });
    }

    @Override
    public void loadData() {

    }
}
