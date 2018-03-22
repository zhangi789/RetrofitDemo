package com.bai.shanutec.cn.http.config;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
/**
 * @author 张海洋
 * @Date on 2018/03/08.
 * @org 上海相舆科技有限公司
 * @describe   OkHttpClient的配置
 */
public class OkHttp3Config {
    private static OkHttpClient okHttpClient = null;
    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    //下面2行网络日志
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    okHttpClient = new OkHttpClient().newBuilder()
                            .connectTimeout(8, TimeUnit.SECONDS)//8S连接超时
                            .readTimeout(20, TimeUnit.SECONDS)//20s读取超时
                            .writeTimeout(20, TimeUnit.SECONDS)//20s写入超时
                            .addInterceptor(InterceptorUtil.HeaderInterceptor())//添加的进度条拦截器
                            .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                            .build();
                }
            }
        }
        return okHttpClient;
    }

}
