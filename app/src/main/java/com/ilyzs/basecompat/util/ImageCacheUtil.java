package com.ilyzs.basecompat.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.LruCache;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhangshu on 2017/11/13.
 */

public class ImageCacheUtil {

    private static LruCache<String, Bitmap> mLruCache;
    private static DiskLruCache mDiskLruCache;

    private Context getContext(Context context) {
        return context.getApplicationContext();
    }

    public ImageCacheUtil(Context context) {
       Context mContext = context.getApplicationContext();
        /*ActivityManager am = (ActivityManager) getContext(context).getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);*/

        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

        String appName = mContext.getResources().getString(mContext.getApplicationInfo().labelRes);
        File cacheDir = getDisLruCacheDir(context,appName);
        if(!cacheDir.exists()){
            cacheDir.mkdirs();
        }
        try {
            mDiskLruCache = DiskLruCache.open(cacheDir,getAppVersionCode(mContext),1,10*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  putCache(String url, Bitmap bitmap){
        String key = getKeyByUrl(url);
        mLruCache.put(key,bitmap);

        try {
            if(null==mDiskLruCache.get("key")){
                DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                if(null!=editor){
                    if(bitmap.compress(Bitmap.CompressFormat.JPEG,100,editor.newOutputStream(0))){
                        editor.commit();
                    }else {
                        editor.abort();
                    }
                }
                mDiskLruCache.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Bitmap getCache(String url) {
        String key = getKeyByUrl(url);

        if (null != mLruCache.get(key)) {
            return mLruCache.get(key);
        } else {
            try {
                if (null != mDiskLruCache.get(key)) {
                    Bitmap bitmap = BitmapFactory.decodeStream(mDiskLruCache.get(key).getInputStream(0));
                    mLruCache.put(key, bitmap);
                    return bitmap;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private File getDisLruCacheDir(Context context, String dirName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = getContext(context).getExternalCacheDir().getPath();
        } else {
            cachePath = getContext(context).getCacheDir().getPath();
        }

        return new File(cachePath + File.separator + dirName);
    }

    private int getAppVersionCode(Context context){
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public String getKeyByUrl(String url) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            cacheKey = bytesToString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
