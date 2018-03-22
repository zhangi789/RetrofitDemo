package com.bai.shanutec.cn.bean;

/**
 * @author 张海洋
 * @Date on 2018/03/08.
 * @org 上海相舆科技有限公司
 * @describe
 */

public class SDLogin {
    /**
     * status : 200
     * data : {"token":"dde324d2d728b2c47ad59958d5e524be","expires":"1525397683"}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : dde324d2d728b2c47ad59958d5e524be
         * expires : 1525397683
         */

        private String token;
        private String expires;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExpires() {
            return expires;
        }

        public void setExpires(String expires) {
            this.expires = expires;
        }
    }
}
