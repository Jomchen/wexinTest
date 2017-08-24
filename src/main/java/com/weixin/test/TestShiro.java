package com.weixin.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * Created by ZPC on 2017/8/19.
 */
public class TestShiro {

    public static void main(String[] args) {
        testMyPermission();
    }


    public static Subject shiroLogin(String username, String password) {
        SecurityManager securityManager = new IniSecurityManagerFactory("classpath:shiro.ini").getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);

        try {
            subject.login(usernamePasswordToken);
        } catch(UnknownAccountException e) {
            System.out.println("测试：用户名不存在");
            throw e;
        } catch(IncorrectCredentialsException e) {
            System.out.println("测试：密码错误");
            throw e;
        } catch (AuthenticationException e) {
            System.out.println("测试：其它错误");
            throw e;
        }

        return subject;
    }


    public static void testShiroAll() {
        Subject subject = TestShiro.shiroLogin("static00", "static00");
        Object object = subject.getPrincipal();
        PrincipalCollection principalCollection = subject.getPrincipals();
        System.out.println("--->" + object);
        System.out.println("--->" + principalCollection.getRealmNames());
    }

    public static void testMyPermission() {
        Subject subject = TestShiro.shiroLogin("kh", "123");
        System.out.println(subject.isPermitted("+user+create"));
    }

}


