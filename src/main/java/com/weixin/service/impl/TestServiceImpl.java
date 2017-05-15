package com.weixin.service.impl;

import com.weixin.service.TestService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zpc on 2017/5/15.
 */
@Service("testService")
public class TestServiceImpl implements TestService {


    @Override
    public Map<String, String> getMapApi(String jing, String wei) {
        return null;
    }

}
