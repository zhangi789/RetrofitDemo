package com.bai.shanutec.cn.http;

import com.bai.shanutec.cn.api.ApiService;
import com.bai.shanutec.cn.http.config.Constances;
import com.bai.shanutec.cn.http.config.OkHttp3Config;
import com.bai.shanutec.cn.http.download.FileDownLoadObserver;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author 张海洋
 * @Date on 2018/03/08.
 * @org 上海相舆科技有限公司
 * @describe Rxtrofit 使用指南
 */

public class RxHelper {
    private static ApiService mApiService = null;

    /**
     * 单例模式
     *
     * @return
     */
    public static ApiService getInstance() {
        if (mApiService == null) {
            synchronized (RxHelper.class) {
                Gson gson = new GsonBuilder().setLenient().create();/////这
                mApiService = new Retrofit.Builder()
                        .baseUrl(Constances.BASE_API)
                        //添加gson转换器
                        //  .addConverterFactory(StringConverterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        //添加String转换器
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        //添加rxjava转换器
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        // OKhttp3的配置
                        .client(OkHttp3Config.getInstance())
                        .build()
                        .create(ApiService.class);
            }
        }
        return mApiService;
    }

    /**
     * 下载单文件，可以是大文件，该方法不支持断点下载
     *
     * @param url                  下载网址
     * @param destDir              存储文件夹
     * @param fileName             存储文件名
     * @param fileDownLoadObserver 监听回调
     */
    public static Disposable downloadFile(@NonNull String url, final String destDir, final String fileName, final FileDownLoadObserver<File> fileDownLoadObserver) {
        return RxHelper.getInstance()
                .downLoadFile(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(@NonNull ResponseBody responseBody) throws Exception {
                        return fileDownLoadObserver.saveFile(responseBody, destDir, fileName);
                    }

                })

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<File>() {

                    @Override

                    public void accept(@NonNull File file) throws Exception {

                        fileDownLoadObserver.onSuccess(file);

                    }

                }, new Consumer<Throwable>() {

                    @Override

                    public void accept(@NonNull Throwable throwable) throws Exception {

                        fileDownLoadObserver.onFail(throwable);

                    }

                }, new Action() {

                    @Override

                    public void run() throws Exception {

                        fileDownLoadObserver.onComplete();

                    }

                });

    }


}
