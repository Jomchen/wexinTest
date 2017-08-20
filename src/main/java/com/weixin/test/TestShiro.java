package com.weixin.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * Created by ZPC on 2017/8/19.
 */
public class TestShiro {
    public static void main(String[] args) {

        SecurityManager securityManager = new IniSecurityManagerFactory("classpath:shiro.ini").getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("Jomchen", "2123");
        try {
            subject.login(usernamePasswordToken);
        } catch(UnknownAccountException e) {
            System.out.println("测试：用户名不存在");
        } catch(IncorrectCredentialsException e) {
            System.out.println("测试：密码错误");
        } catch (AuthenticationException e) {
            System.out.println("测试：其它错误");
        }
    }
}
