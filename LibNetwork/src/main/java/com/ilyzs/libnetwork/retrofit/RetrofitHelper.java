package com.ilyzs.libnetwork.retrofit;

import android.os.Environment;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ilyzs.libnetwork.util.RequestCallback;
import com.ilyzs.libnetwork.util.RequestManagerInterface;
import com.ilyzs.libnetwork.util.RequestParameter;
import com.ilyzs.libnetwork.util.URLData;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zs.
 */

public class RetrofitHelper {

    private static RetrofitHelper retrofitHelper;
    private Retrofit retrofit;
    private static String baseUrl = "http://wthrcdn.etouch.cn/";
    private RetrofitHelper(URLData urlData){
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    private static RetrofitHelper getInstance(final  URLData urlData){
        synchronized (RetrofitHelper.class){
            if(null==retrofitHelper){
                retrofitHelper = new RetrofitHelper(urlData);
            }
        }
        return retrofitHelper;
    }

    public static void doHttpGet(RequestManagerInterface rmi, URLData urlData, List<RequestParameter> rpList, RequestCallback callback) {
        RetrofitHelper instance =  getInstance(urlData);
        if(null!=instance)
            instance.inner_doHttpGet(rmi,urlData,rpList,callback);
    }

    public static void doHttpPost(RequestManagerInterface rmi, URLData urlData, List<RequestParameter> rpList, RequestCallback callback) {
        RetrofitHelper instance =  getInstance(urlData);
        if(null!=instance)
            instance.inner_doHttpPost(rmi,urlData,rpList,callback);
    }

    public  void uploadFile(RequestManagerInterface rmi, URLData urlData,List<RequestParameter> rpList,RequestCallback callback){
        RetrofitUrlApi urlApi = retrofit.create(RetrofitUrlApi.class);
        String url = urlData.getUrl();
        File file = new File(Environment.getExternalStorageDirectory(), "ic_launcher.png");
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
        Call<ResponseBody> call = urlApi.uploadFile(url, photoRequestBody);
    }

    private void inner_doHttpGet(RequestManagerInterface rmi, URLData urlData, List<RequestParameter> rpList, final RequestCallback callback) {
        RetrofitUrlApi urlApi = retrofit.create(RetrofitUrlApi.class);
        String url = urlData.getUrl();
        Observable observable =  urlApi.getCommonByUrl(url,parseParameter(rpList));

        RObserver observer = new RObserver(callback);
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
        RequestManagerRetrofitImpl rmoi = (RequestManagerRetrofitImpl)rmi;
        rmoi.addRequestQuneue(observer.disposable);
    }


    private void inner_doHttpPost(RequestManagerInterface rmi, URLData urlData, List<RequestParameter> rpList, final RequestCallback callback) {
        RetrofitUrlApi urlApi = retrofit.create(RetrofitUrlApi.class);
        String url = urlData.getUrl();
        Observable observable =  urlApi.postCommonByUrl(url,parseParameter(rpList));

        RObserver observer = new RObserver(callback);
        observable.subscribeOn(io.reactivex.schedulers.Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
        RequestManagerRetrofitImpl rmoi = (RequestManagerRetrofitImpl)rmi;
        rmoi.addRequestQuneue(observer.disposable);
    }

    @NonNull
    private Callback<ResponseBody> getReponseCallback(final RequestCallback callback) {
        return new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(null!=callback)
                    try {
                        callback.onSuccess(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(null!=callback)
                    callback.onFail(t.getMessage());
            }
        };
    }

    private Map<String,Object> parseParameter(List<RequestParameter> rpList) {
        Map map = new HashMap();
        if(null!=rpList && !rpList.isEmpty()){
            for (RequestParameter rp : rpList) {
                if(null!=rp.getValue() && null!=rp.getName()){
                    map.put(rp.getName(),rp.getValue());
                }
            }
        }
        return map;
    }

    private class RObserver implements Observer {
        public Disposable disposable;
        private RequestCallback callback;

        public   RObserver (RequestCallback callback){
            this.callback = callback;
        }

        @Override
        public void onSubscribe(Disposable d) {
            disposable = d;
        }

        @Override
        public void onNext(Object o) {
            callback.onSuccess(o.toString());
        }

        @Override
        public void onError(Throwable e) {
            callback.onFail(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }

}
