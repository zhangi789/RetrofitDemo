package com.bai.shanutec.cn.bean;

/**
 * @author 张海洋
 * @Date on 2018/03/08.
 * @org 上海相舆科技有限公司
 * @describe
 */

public class LoginBean {


    /**
     * errCode : 0
     * errDesc : ok
     * userid : 278
     * username : 13965685480
     * kuid :
     * kuname :
     * kupwd :
     * access_token : s1KrLfpxdPVJT9KZq1Iw68JLlXigSuO6FVQcJX4f
     * token_type : Bearer
     * expires_in : 28800
     */
    private int errCode;
    private String errDesc;
    private int userid;
    private String username;
    private String kuid;
    private String kuname;
    private String kupwd;
    private String access_token;
    private String token_type;
    private int expires_in;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKuid() {
        return kuid;
    }

    public void setKuid(String kuid) {
        this.kuid = kuid;
    }

    public String getKuname() {
        return kuname;
    }

    public void setKuname(String kuname) {
        this.kuname = kuname;
    }

    public String getKupwd() {
        return kupwd;
    }

    public void setKupwd(String kupwd) {
        this.kupwd = kupwd;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

}
