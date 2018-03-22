package com.bai.shanutec.cn.uitls;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author 张海洋
 * @Date on 2018/03/08.
 * @org 上海相舆科技有限公司
 * @describe
 */

public class BaseUtils {


    /**
     * RequestBody    post 请求参数  json
     * 把 Map集合 转化成   Json数据  字符串
     * Map->Json字符串->RequestBody
     *
     * @param data
     * @return
     */
    public static RequestBody getMapConvertsionJsonRequestBody(Map<String, Object> data) {
        JSONObject jsonObject = new JSONObject(data);
        String route = jsonObject.toString();
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);


    }


    /**
     *     用于图片上传 携带参数封装
     * @param param
     * @return
     */


    public static RequestBody convertToRequestBody(String param) {

        return
                RequestBody.create(MediaType.parse("text/plain"),
                        param);


    }

}
