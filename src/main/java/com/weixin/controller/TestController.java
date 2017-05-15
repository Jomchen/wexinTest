package com.weixin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zpc on 2017/5/15.
 */

@RequestMapping(value = "/test")
@Controller
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping(value = "/getMapApi", method = RequestMethod.GET)
    public ModelAndView getMapApi() {
        return new ModelAndView("/test/getMapApi.jsp");
    }

}