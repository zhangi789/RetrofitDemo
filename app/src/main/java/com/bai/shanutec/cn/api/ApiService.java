package com.bai.shanutec.cn.api;


import com.bai.shanutec.cn.bean.BaseEntity;
import com.bai.shanutec.cn.bean.HomeBean;
import com.bai.shanutec.cn.bean.LoginBean;
import com.bai.shanutec.cn.bean.SDLogin;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author 张海洋
 * @Date on 2018/03/08.
 * @org 上海相舆科技有限公司
 * @describe
 */

public interface ApiService {
    /**
     * 文件下载
     * @param url 下载网址
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downLoadFile(@NonNull @Url String url);


    /**
     * post  提交json数据字符串
     * @param route 转化后的json 字符串
     * @return      实体类
     */
    //登陆 json请求
    @POST("oauth/access_token")
    Observable<LoginBean> getLoginInfo(@Body RequestBody route);

    /**
     * post  提交json数据字符串
     *
     * @param authorization 头部信息  动态添加头部信息
     * @param route         Json字符串
     * @return
     */
    @POST("api/room/devices")
    Observable<BaseEntity<HomeBean>> getHomeLeftData(@Header("Authorization") String authorization, @Body RequestBody route);

    /**
     * @param map
     * @return
     * @Field，@FieldMap:Post方式传递简单的键值对,需要添加@FormUrlEncoded表示表单提交 登陆        post   多参数
     * 表单请求    传递Map集合
     * 返回字符串信息
     */
    //登陆 表单请求
    @FormUrlEncoded
    @POST("oauth/access_token")
    Observable<SDLogin> getLoginInfo(@FieldMap Map<String, Object> map);

    //用户和图像上传携带参数

    /**
     *    带参数   Map<String, RequestBody> map 要  否则不要
     * @param map
     * @param part
     * @return
     */
    @Multipart
    @POST("user/uploadavatar")
    Observable<Object> fileUploadImage(@PartMap Map<String, RequestBody> map, @Part MultipartBody.Part part);





}
