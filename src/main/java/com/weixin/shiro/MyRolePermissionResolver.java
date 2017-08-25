package com.weixin.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by ZPC on 2017/8/25.
 */
public class MyRolePermissionResolver implements RolePermissionResolver {

    @Override
    public Collection<Permission> resolvePermissionsInRole(String s) {

        if (s.contains("r1")) {
            return Arrays.asList((Permission)new WildcardPermission("classroom:*"));
        }

        return null;
    }

}
