package com.weixin.entity;

import com.weixin.common.AbstractParent;

public class DeveloperFeedback implements AbstractParent {

    private Integer wid;
    private String signature;                               // 微信加密字签名
    private String timesTamp;                               // 微信反馈时间戳
    private String nonce;                                   // 微信反馈随机字符串
    private String echostr;                                 // 微信随机字符串

    public DeveloperFeedback() {
    }

    public DeveloperFeedback(Integer wid, String signature, String timesTamp, String nonce, String echostr) {
        this.wid = wid;
        this.signature = signature;
        this.timesTamp = timesTamp;
        this.nonce = nonce;
        this.echostr = echostr;
    }

    public Integer getWid() {
        return wid;
    }

    public String getSignature() {
        return signature;
    }

    public String getTimesTamp() {
        return timesTamp;
    }

    public String getNonce() {
        return nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setTimesTamp(String timesTamp) {
        this.timesTamp = timesTamp;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

}