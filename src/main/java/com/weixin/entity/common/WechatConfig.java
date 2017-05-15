package com.weixin.entity.common;

import com.weixin.common.AbstractParent;

/**
 * Created by zpc on 2017/3/19.
 */
public class WechatConfig implements AbstractParent {

    private String token;
    private String appId;
    private String appsecret;
    private String url;

    public WechatConfig() {
    }

    public WechatConfig(String token, String appId, String appsecret, String url) {
        this.token = token;
        this.appId = appId;
        this.appsecret = appsecret;
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public String getUrl() {
        return url;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
