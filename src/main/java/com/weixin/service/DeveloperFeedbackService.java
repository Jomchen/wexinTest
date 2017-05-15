package com.weixin.service;

import com.weixin.entity.DeveloperFeedback;

/**
 * Created by zpc on 2017/5/15.
 */
public interface DeveloperFeedbackService {

    DeveloperFeedback getOnlyDeveloperFeedback(Integer wid);

    int insert(String signature, String timesTamp, String nonce, String echostr);

}
