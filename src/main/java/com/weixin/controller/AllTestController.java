package com.weixin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ZPC on 2017/8/18.
 */
@Controller
@RequestMapping("allTest")
public class AllTestController {

    private Logger logger = LoggerFactory.getLogger(AllTestController.class);


    @RequestMapping(value = "/allPage00")
    public String allPage00() {
        return "/test/allPage00.jsp";
    }

    @RequestMapping(value = "/allPage01")
    public String allPage01() { return "/test/allPage01.jsp"; }

    @RequestMapping(value = "/allPage02")
    public String allPage02() { return "/test/allPage02.jsp"; }

}
