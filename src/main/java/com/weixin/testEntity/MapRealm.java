package com.weixin.testEntity;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZPC on 2017/8/19.
 */
public class MapRealm implements Realm {

    public static Map<String, String> realmMap;

    static {
        realmMap = new HashMap<>();
        realmMap.put("Jomchen", "123");
        realmMap.put("Kangkang", "12");
        realmMap.put("Jane", "1");
    }

    @Override
    public String getName() {
        return "mapRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        String password = new String((char[])authenticationToken.getCredentials());

        if (!realmMap.containsKey(username)) {
            throw new UnknownAccountException("验证：用户存在");
        }
        if (!realmMap.get(username).equals(password)) {
            throw new IncorrectCredentialsException("验证：密码错误");
        }

        return new SimpleAuthenticationInfo(username, password, getName());

    }
}
