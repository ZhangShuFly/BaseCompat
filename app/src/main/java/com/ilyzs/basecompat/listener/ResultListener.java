package com.ilyzs.basecompat.listener;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by zhangshu on 2017/11/15.
 */

public interface ResultListener {

    void  onSuccess(Bitmap bitmap);

    void onFail(Drawable drawable);
}
