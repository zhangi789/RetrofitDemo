package com.bai.shanutec.cn.http.config;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * @author 张海洋
 * @Date on 2018/03/08.
 * @org 上海相舆科技有限公司
 * @describe    日志拦截器
 */

public class InterceptorUtil {
    public static String TAG = "----";

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w(TAG, "log: 日志拦截器 " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }
    public static Interceptor HeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request mRequest = chain.request();
                //在这里你可以做一些想做的事,比如token失效时,重新获取token
                //或者添加header等等,PS我会在下一篇文章总写拦截token方法
                //添加下载进度条操作
                return chain.proceed(mRequest);
            }
        };

    }



}
