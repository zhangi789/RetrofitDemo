package com.bai.shanutec.cn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bai.shanutec.cn.bean.LoginBean;
import com.bai.shanutec.cn.bean.SDLogin;
import com.bai.shanutec.cn.http.RxHelper;
import com.bai.shanutec.cn.ui.DownLoadActivity;
import com.bai.shanutec.cn.uitls.BaseUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = "GGG";

    private Button btn_download, btn_json,btn_form;

    private Button  btn_imag_upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_download = (Button) findViewById(R.id.btn_download);
        btn_download.setOnClickListener(this);
        btn_json = (Button) findViewById(R.id.btn_json);
        btn_json.setOnClickListener(this);
        btn_form = (Button) findViewById(R.id.btn_form);
        btn_form.setOnClickListener(this);

        btn_imag_upload = (Button) findViewById(R.id.btn_imag_upload);
        btn_imag_upload.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_download:

                startActivity(new Intent(MainActivity.this, DownLoadActivity.class));
                break;

        //  post  json 提交
            case R.id.btn_json:
                setJsonLogin();
                break;
            //  post  表单 提交
            case R.id.btn_form:

                setFromLogin();
                break;

           //  表单上传  图片携带参数
            case R.id.btn_imag_upload:
                setUploadImge();
                break;
        }

    }

    private void setUploadImge() {
        Log.i(TAG,"路径 "+ Environment.getExternalStorageDirectory().getPath());
        Map<String, RequestBody> params = new HashMap<>();
        params.put("app_token",BaseUtils.convertToRequestBody(""));
        params.put("token", BaseUtils.convertToRequestBody(""));
        File file = new File("/storage/emulated/0/image/1519718676333.jpg");
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("files",file.getName(), requestBody);
        Log.i(TAG,"路径图片 "+file.getName());
        RxHelper.getInstance().fileUploadImage(params,part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "log: d " + d.isDisposed());
                    }

                    @Override
                    public void onNext(Object longin) {
                        Log.i(TAG, "log: token " + longin);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "log: e " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "log: 完成 ");
                    }
                });




    }


    public void setFromLogin(){
        Map<String, Object> mapsLogin = new HashMap<>();
        mapsLogin.put("grant_type", "password");
        mapsLogin.put("client_id", "246fe08022daf1a1");
        mapsLogin.put("client_secret", "968e6517a9a6fa3a");
        mapsLogin.put("username", "18321881519");
        mapsLogin.put("password", "Xpower2018");
        RxHelper.getInstance().getLoginInfo(mapsLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SDLogin>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "log: d " + d.isDisposed());
                    }

                    @Override
                    public void onNext(SDLogin longin) {
                        Log.i(TAG, "log: token " + longin.getStatus());
                        //  Log.i(TAG, "log: token " + longin.getAccess_token());
                        //String fs= (String) result;
                        //tv_show.setText(longin.getAccess_token());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "log: e " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "log: 完成 ");
                    }
                });

    }
    private void setJsonLogin() {
        Map<String, Object> mapsLogin = new HashMap<>();
        mapsLogin.put("grant_type", "password");
        mapsLogin.put("client_id", "246fe08022daf1a1");
        mapsLogin.put("client_secret", "968e6517a9a6fa3a");
        mapsLogin.put("username", "18321881519");
        mapsLogin.put("password", "Xpower2018");
        RequestBody body = BaseUtils.getMapConvertsionJsonRequestBody(mapsLogin);
        RxHelper.getInstance().getLoginInfo(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "log: start----------登陆---------------- ");
                    }

                    @Override
                    public void onNext(LoginBean longin) {
                        Log.i(TAG, "log: errcode " + longin.getErrCode() + " token " + longin.getAccess_token());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "log: e " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "log: 完成");
                    }
                });
    }



}
