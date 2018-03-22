package com.bai.shanutec.cn.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bai.shanutec.cn.R;
import com.bai.shanutec.cn.http.RxApiManager;
import com.bai.shanutec.cn.http.RxHelper;
import com.bai.shanutec.cn.http.config.Constances;
import com.bai.shanutec.cn.http.download.FileDownLoadObserver;

import java.io.File;

import io.reactivex.disposables.Disposable;

/**
 * @author 张海洋
 * @Date on 2018/03/08.
 * @org 上海相舆科技有限公司
 * @describe
 */

public class DownLoadActivity extends AppCompatActivity implements View.OnClickListener {


    private Button tv_download;
    private TextView tv_show_progress;
    Disposable disposable;
    RxApiManager rxCacheManger;

    private Button btn_file_upload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downfile);
        rxCacheManger = RxApiManager.getInstance();
        initviews();
    }

    private void initviews() {
        tv_download = (Button) findViewById(R.id.tv_download);
        tv_download.setOnClickListener(this);
        tv_show_progress = (TextView) findViewById(R.id.tv_show_progresss);
        tv_show_progress.setText("展示");
        btn_file_upload = (Button) findViewById(R.id.btn_file_upload);
        btn_file_upload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_download:
                disposable = RxHelper.downloadFile(Constances.Web_DOWnLOAD_URL,
                        Constances.FILE_PATH,
                        Constances.FILE_NAME,
                        new FileDownLoadObserver<File>() {
                            @Override
                            public void onSuccess(File file) {
                                Log.i("GGG", "onSuccess");


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_show_progress.setText("onSuccess");
                                    }
                                });

                            }

                            @Override
                            public void onFail(Throwable throwable) {
                                Log.i("GGG", "onFail  " + throwable.getMessage());
                            }

                            @Override
                            public void onProgress(final int progress, long total) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_show_progress.setText("进度 " + progress + "%");
                                    }
                                });
                                Log.i("GGG", "onProgress " + progress + " total " + total);
                            }
                        });
                rxCacheManger.add("filedownload", disposable);
                break;
            case R.id.btn_file_upload:


                break;
        }
    }


    @Override
    protected void onDestroy() {
        rxCacheManger.remove("filedownload");
        super.onDestroy();
    }
}
