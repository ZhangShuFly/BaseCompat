package com.ilyzs.libnetwork.retrofit;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by zs .
 */

public interface RetrofitUrlApi {

    /**
     * 参数不固定的get请求方法
     * @param methodPath  不包含baseurl的请求路径
     * @param map 参数
     * @return
     */
    @GET("{pathUrl}")
    Call<ResponseBody> getCommon(@QueryMap Map<String, Object> map, @Path("methodPath") String methodPath);

    /**
     * 参数不固定的post请求方法
     * @param methodPath  不包含baseurl的请求路径
     * @param map 参数
     * @return
     */
    @FormUrlEncoded
    @POST("{pathUrl}")
    Call<ResponseBody> postCommon(@FieldMap Map<String,Object> map, @Path("methodPath") String methodPath);

    /**
     * 参数不固定的get请求方法 : 使用全路径复写baseUrl，适用于非统一baseUrl的场景。
     * @param url 全路径
     * @param map
     * @return
     */
    @GET
    Observable<Object> getCommonByUrl(@Url String url, @QueryMap Map<String, Object> map);

    /**
     * 参数不固定的post请求方法 ：使用全路径复写baseUrl，适用于非统一baseUrl的场景。
     * @param url 全路径
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Object> postCommonByUrl( @Url String url,@FieldMap Map<String,Object> map);

    /**
     * 上传单张图片
     * @param img
     * @return
     */
    @Multipart
    @POST
    Call<ResponseBody> uploadFile(@Url String url,@Part("file\";filename=\"iamgeName.png") RequestBody img);

    /**
     * 单张图片和参数
     * @param maps
     * @param avatar
     * @return
     */
    @Multipart
    @POST
    Call<ResponseBody> uploadFileAndPara(@Url String url, @FieldMap Map<String , Object> maps,@Part("avatar\"; filename=\"avatar.jpg") RequestBody avatar);

    /**
     * @param url
     * @param params
     * @param maps
     * @return
     */
    @Multipart
    @POST
    Call<ResponseBody> registerUser(@Url String url,  @FieldMap Map<String , Object> maps , @PartMap Map<String, RequestBody> params);

    /**
     * 通过 List<MultipartBody.Part> 传入多个part实现多文件上传
     * @param parts 每个part代表一个
     * @return 状态信息
     */
    @Multipart
    @POST
    Call<ResponseBody> uploadFilesWithParts(@Url String url,@FieldMap Map<String , Object> maps ,@Part() List<MultipartBody.Part> parts);

    /**
     * 通过 MultipartBody和@body作为参数来上传
     * @param multipartBody MultipartBody包含多个Part
     * @return 状态信息
     */
    @POST
    Call<ResponseBody> uploadFileWithRequestBody(@Url String url,@FieldMap Map<String , Object> maps ,@Body MultipartBody multipartBody);
}
