package com.weixin.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * Created by ZPC on 2017/8/21.
 */
public class MyPermissionRecolver implements PermissionResolver {

    @Override
    public Permission resolvePermission(String s) {
        if (s.startsWith("+")) {
            return new MyPermission(s);
        }
        return new WildcardPermission(s);
    }

}
