package com.ilyzs.basecompat;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.ilyzs.basecompat.adapter.ImageAdapter;
import com.ilyzs.libnetwork.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppBaseActivity {

    private ImageView glideIv;
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
        glideIv = (ImageView)findViewById(R.id.iv_image_glide);
        imageRv = (RecyclerView) findViewById(R.id.rv_image);
        imageRv.setHasFixedSize(true);
        imageRv.setLayoutManager(new LinearLayoutManager(this));
        imageRv.setAdapter(new ImageAdapter(list,this));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void loadData() {

    }

}
