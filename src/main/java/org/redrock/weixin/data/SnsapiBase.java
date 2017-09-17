package org.redrock.weixin.data;

import com.alibaba.fastjson.JSONObject;

public class SnsapiBase {
    private String accessToken;
    private String refreshToken;
    private String openid;
    private String scope;
    private String expireIn;

    public SnsapiBase() {}

    public SnsapiBase(JSONObject data) {
        this.accessToken = data.getString("access_token");
        this.refreshToken = data.getString("refresh_token");
        this.openid = data.getString("openid");
        this.scope = data.getString("scope");
        this.expireIn = data.getString("expires_in");
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(String expireIn) {
        this.expireIn = expireIn;
    }
}
