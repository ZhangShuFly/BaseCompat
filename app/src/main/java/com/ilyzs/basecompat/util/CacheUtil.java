package com.ilyzs.basecompat.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.LruCache;

import com.ilyzs.basecompat.base.CompatBaseApplication;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhangshu on 2017/11/13.
 */

public class CacheUtil {

    private LruCache<String, Bitmap> bitmapLruCache;
    private LruCache<String, String> jsonLruCache;
    private DiskLruCache mDiskLruCache;

    public CacheUtil(String cachePath, int versionCode) {
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        bitmapLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
        jsonLruCache = new LruCache<String,String>(maxSize/2){
            @Override
            protected int sizeOf(String key, String value) {
                return value.getBytes().length;
            }
        };

        File cacheDir = getDisLruCacheDir(cachePath);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        try {
            mDiskLruCache = DiskLruCache.open(cacheDir, versionCode, 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putImageCache(String url, Bitmap bitmap) {
        String key = getKeyByUrl(url);
        bitmapLruCache.put(key, bitmap);

        try {
                DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                if (null != editor) {
                    if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, editor.newOutputStream(0))) {
                        editor.commit();
                    } else {
                        editor.abort();
                    }
                }
                mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getImageCache(String url) {
        String key = getKeyByUrl(url);

        if (null != bitmapLruCache.get(key)) {
            return bitmapLruCache.get(key);
        } else {
            try {
                DiskLruCache.Snapshot snapshot =  mDiskLruCache.get(key);
                if (null != snapshot) {
                    Bitmap bitmap = BitmapFactory.decodeStream(snapshot.getInputStream(0));
                    bitmapLruCache.put(key, bitmap);
                    return bitmap;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void putJsonCache(String url,String json){
        String key = getKeyByUrl(url);
        jsonLruCache.put(key,json);

        DiskLruCache.Editor editor = null;
        BufferedWriter bw = null;
        try {
            editor = mDiskLruCache.edit(key);
            if(null == editor){
              return;
            }
            OutputStream os = editor.newOutputStream(0);
            bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(url);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if(null!=editor){
                    editor.abort();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally {
            if(null!=bw){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getJsonCache(String url){
        String key = getKeyByUrl(url);
        if(null!=jsonLruCache.get(key)){
            return jsonLruCache.get(key);
        }else{
            DiskLruCache.Snapshot snapshot = null;
            InputStream is = null;
            try {
                snapshot = mDiskLruCache.get(key);
                is = snapshot.getInputStream(0);
                byte[] buf = new byte[256];
                int len = 0;
                StringBuilder sb = new StringBuilder();
                while ((len = is.read(buf))!=-1){
                        sb.append(new String(buf,0,len));
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
                if(null!=snapshot){
                    snapshot.close();
                }
            }finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    private File getDisLruCacheDir(String cachePath) {
        return new File(cachePath);
    }

    private String getKeyByUrl(String url) {
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

    @Deprecated
    private int getAppVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
