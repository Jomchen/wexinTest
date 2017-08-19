package com.weixin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ZPC on 2017/8/18.
 */
@Controller
public class AllTestController {

    private Logger logger = LoggerFactory.getLogger(AllTestController.class);


    @RequestMapping(value = "/allPage")
    public String allPage() {
        return "/test/allTest.jsp";
    }


}
