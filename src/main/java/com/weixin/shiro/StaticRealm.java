package com.weixin.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZPC on 2017/8/20.
 */
public class StaticRealm extends AuthorizingRealm {

    public static Map<String, String> staticMap;

    static {
        staticMap = new HashMap<String, String>();
        staticMap.put("static00", "static00");
        staticMap.put("static01", "static01");
        staticMap.put("static02", "static02");
        staticMap.put("kh", "123");
    }


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("r1");
        info.addRole("r2");
        info.addStringPermission("+user+*");
        info.addObjectPermission(new MyPermission("+topic+create"));
        info.addObjectPermission(new MyPermission("topic+delete+1"));
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        String password = new String((char[])authenticationToken.getCredentials());
        if (!staticMap.containsKey(username)) {
            throw new UnknownAccountException("STATIC验证：" + "用户不存在");
        }
        if (!staticMap.get(username).equals(password)) {
            throw new IncorrectCredentialsException("STATIC验证：" + "密码错误");
        }

        return new SimpleAuthenticationInfo(username, password, "staticRealm");
    }

}
