package com.weixin.service.impl;

import com.weixin.service.TestService;
import com.weixin.utils.HttpClientUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zpc on 2017/5/15.
 */
@Service("testService")
public class TestServiceImpl implements TestService {


    @Override
    public Map<String, String> getMapApi(String jing, String wei) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("ip", "111.10.118.26");
        paramMap.put("ak", "vbpBKznEa5Ldgmh1bOEzFt1pSNEO5YGi");
        paramMap.put("coor", "gcj02");

        Map<String, String> resultMap = HttpClientUtils.doPost("http://api.map.baidu.com/location/ip", paramMap);
        return resultMap;
    }

}
