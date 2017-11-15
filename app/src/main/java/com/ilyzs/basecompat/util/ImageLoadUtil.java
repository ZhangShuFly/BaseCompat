package com.ilyzs.basecompat.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ilyzs.basecompat.R;
import com.ilyzs.basecompat.listener.ResultListener;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by zhangshu on 2017/11/15.
 */

public class ImageLoadUtil {

    private static volatile ImageLoadUtil mInstance;

    public static  ImageLoadUtil getInstance(){
        if(null == mInstance){
            synchronized(ImageLoadUtil.class){
                mInstance = new ImageLoadUtil();
            }
        }
        return mInstance;
    }

    private RequestManager getGlideRequest(Context context){
        return Glide.with(context);
    }

    private Picasso getPicassoRequest(Context context){
        return Picasso.with(context);
    }

    public  void loadImage(Context context, String url, ImageView imageView){
        loadImageByPicasso(context, url, imageView);
    }

    public void loadImageHasListener(Context context, String url, ResultListener resultListener){
        loadImageHasListenerByGlide(context, url, resultListener);
    }

    private void loadImageByGlide(Context context, String url, ImageView imageView){
        RequestOptions options = new RequestOptions().placeholder(R.drawable.loading).error(R.drawable.loadfail).priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL);
        getGlideRequest(context).load(url).apply(options).transition(new DrawableTransitionOptions().dontTransition()).into(imageView);
    }

    private void loadImageHasListenerByGlide(Context context, String url, final ResultListener resultListener){
        getGlideRequest(context).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                resultListener.onSuccess(((BitmapDrawable)resource).getBitmap());
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                resultListener.onFail(errorDrawable);
            }
        });
    }

    /**
     * memoryPolicy:内存管理，NO_CACHE-跳过从内存读取图片；NO_STORE-不缓存，用于只加载一次的情况
     * networkPolicy: 存储管理，NO_CACHE-跳过从本地读取图片；NO_STORE-不缓存本地；OFFLINE-只从本地读取图片，除非本地没有并且联网。
     * @param context
     * @param url
     * @param imageView
     */
    private void loadImageByPicasso(Context context, String url, ImageView imageView){
        getPicassoRequest(context).load(url).memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE). into(imageView);
    }


    private void loadImageHasListenerByPicasso(Context context, String url, final ResultListener resultListener){
        getPicassoRequest(context).load(url).memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                resultListener.onSuccess(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                resultListener.onFail(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
}
