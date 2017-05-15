package com.weixin.service.impl;

import com.weixin.entity.DeveloperFeedback;
import com.weixin.mapper.DeveloperFeedbackMapper;
import com.weixin.service.DeveloperFeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zpc on 2017/5/15.
 */

@Service("developerFeedbackService")
public class DeveloperFeedbackServiceImpl implements DeveloperFeedbackService {

    @Autowired
    private DeveloperFeedbackMapper developerFeedbackMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public DeveloperFeedback getOnlyDeveloperFeedback(Integer wid) {
        return developerFeedbackMapper.selectById(wid);
    }

    @Override
    public int insert(String signature, String timesTamp, String nonce, String echostr) {
        DeveloperFeedback developerFeedback = new DeveloperFeedback();
        developerFeedback.setSignature(signature);
        developerFeedback.setTimesTamp(timesTamp);
        developerFeedback.setNonce(nonce);
        developerFeedback.setEchostr(echostr);
        return developerFeedbackMapper.insert(developerFeedback);
    }


}
