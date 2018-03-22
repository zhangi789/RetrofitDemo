package com.bai.shanutec.cn.bean;

import java.io.Serializable;


/**
 * @author 张海洋
 * @Date on 2018/03/08.
 * @org 上海相舆科技有限公司
 * @describe
 */

public class HomeBean implements Serializable {
    private String kid;
    private int is_common;
    private int device_id;
    private String device_mac;
    private String version_number;
    private String device_name;
    private int device_series;
    private String device_type;
    private int is_status;
    private int online;

    public int getIs_common() {
        return is_common;
    }

    public void setIs_common(int is_common) {
        this.is_common = is_common;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getDevice_mac() {
        return device_mac;
    }

    public void setDevice_mac(String device_mac) {
        this.device_mac = device_mac;
    }

    public String getVersion_number() {
        return version_number;
    }

    public void setVersion_number(String version_number) {
        this.version_number = version_number;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public int getDevice_series() {
        return device_series;
    }

    public void setDevice_series(int device_series) {
        this.device_series = device_series;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public int getIs_status() {
        return is_status;
    }

    public void setIs_status(int is_status) {
        this.is_status = is_status;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }
}
