package com.weixin.service.impl;

import com.weixin.entity.common.WechatConfig;
import com.weixin.service.WeChatSystemService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zpc on 2017/4/10.
 */
public class WeChatSystemServiceImpl implements WeChatSystemService {

    @Autowired
    WechatConfig wechatConfig;

    /* 获取access_token */
    String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
            + "&appid=" + wechatConfig.getAppId()
            + "&secret=" + wechatConfig.getAppsecret();

}
