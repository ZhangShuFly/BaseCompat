package com.ilyzs.basecompat.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.ilyzs.basecompat.R;
import com.ilyzs.basecompat.adapter.ImageAdapter;
import com.ilyzs.basecompat.base.CompatBaseActivity;
import com.ilyzs.basecompat.util.ImageLoadUtil;
import com.ilyzs.libnetwork.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends CompatBaseActivity {

    private ImageView imageIv;
    private List<String> list = new ArrayList<>();
    private RecyclerView imageRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initPara() {
        list.add("http://pic69.nipic.com/file/20150609/9252150_174125118561_2.jpg");
        list.add("http://pic23.photophoto.cn/20120417/0017030514645955_b.jpg");
        list.add("http://pic26.photophoto.cn/20130312/0037037526572944_b.jpg");
        list.add("http://pic69.nipic.com/file/20150609/9252150_174125118561_2.jpg");
        list.add("http://pic23.photophoto.cn/20120417/0017030514645955_b.jpg");
        list.add("http://pic26.photophoto.cn/20130312/0037037526572944_b.jpg");
        list.add("http://pic69.nipic.com/file/20150609/9252150_174125118561_2.jpg");
        list.add("http://pic23.photophoto.cn/20120417/0017030514645955_b.jpg");
        list.add("http://pic26.photophoto.cn/20130312/0037037526572944_b.jpg");
        list.add("http://pic69.nipic.com/file/20150609/9252150_174125118561_2.jpg");
        list.add("http://pic23.photophoto.cn/20120417/0017030514645955_b.jpg");
        list.add("http://pic26.photophoto.cn/20130312/0037037526572944_b.jpg");
        list.add("http://pic69.nipic.com/file/20150609/9252150_174125118561_2.jpg");
        list.add("http://pic23.photophoto.cn/20120417/0017030514645955_b.jpg");
        list.add("http://pic26.photophoto.cn/20130312/0037037526572944_b.jpg");

    }

    @Override
    public void loadView() {
        setContentView(R.layout.activity_image);
        imageIv = (ImageView)findViewById(R.id.iv_image);
        imageRv = (RecyclerView)findViewById(R.id.rv_image);
        imageRv.setLayoutManager(new LinearLayoutManager(this));
        imageRv.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        imageRv.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.HORIZONTAL));

        ImageLoadUtil.getInstance().loadImage(this,list.get(0),imageIv);

        ImageAdapter adapter = new ImageAdapter(list,this);
        imageRv.setAdapter(adapter);
    }

    @Override
    public void loadData() {

    }

}
