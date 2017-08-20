package com.weixin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by ZPC on 2017/8/18.
 */
@Controller
@RequestMapping("allTest")
public class AllTestController {

    private Logger logger = LoggerFactory.getLogger(AllTestController.class);

    @RequestMapping(value = "/allLogin", method = RequestMethod.GET)
    public String allLogin() { return "/shiro/allLogin.jsp"; }

    @RequestMapping(value = "/allLogin", method = RequestMethod.POST)
    public String allLogin(RedirectAttributes redirectAttributes, String username, String password) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        String emsg = null;
        try {
            subject.login(usernamePasswordToken);
        } catch(UnknownAccountException e) {
            System.out.println("网络：用户名不存在");
            emsg = "用户不存在";
        } catch(IncorrectCredentialsException e) {
            System.out.println("网络：密码错误");
            emsg = "密码错误";
        } catch (AuthenticationException e) {
            System.out.println("网络：其它错误");
            emsg = "其它错误";
        }

        redirectAttributes.addFlashAttribute("emsg", emsg);

        return null == emsg ? "/shiro/success.jsp" : "/shiro/faile.jsp";

    }

    @RequestMapping(value = "/allPage00")
    public String allPage00() {
        return "/shiro/allPage00.jsp";
    }

    @RequestMapping(value = "/allPage01")
    public String allPage01() { return "/shiro/allPage01.jsp"; }

    @RequestMapping(value = "/allPage02")
    public String allPage02() { return "/shiro/allPage02.jsp"; }

}
