package com.weixin.controller;

import com.weixin.common.ResultObj;
import com.weixin.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by zpc on 2017/5/15.
 */

@RequestMapping(value = "/test")
@Controller
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestService testService;


    @RequestMapping(value = "/getMapApi", method = RequestMethod.GET)
    public ModelAndView getMapApi() {
        return new ModelAndView("/test/getMapApi.jsp");
    }


    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    @ResponseBody
    public String getCity() {
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(ResultObj.RESULT_FAIL);

        try {
            Map<String, String> resultMap = testService.getMapApi("", "");
            resultObj.setCode(ResultObj.RESULT_SUCCESS);
        } catch (Exception e) {
            logger.warn("系统错误");
            resultObj.setMsg(e.getMessage());
        }

        return resultObj.toJson();
    }

}
