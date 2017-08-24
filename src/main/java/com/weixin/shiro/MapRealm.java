package com.weixin.shiro;

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
        realmMap.put("map00", "map00");
        realmMap.put("map01", "map01");
        realmMap.put("map02", "map02");
        realmMap.put("Jomchen", "Jomchen");
        realmMap.put("Kangkang", "Kangkang");
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
            throw new UnknownAccountException("MAP验证：用户不存在");
        }
        if (!realmMap.get(username).equals(password)) {
            throw new IncorrectCredentialsException("MAP验证：密码错误");
        }

        return new SimpleAuthenticationInfo(username, password, getName());

    }
}
