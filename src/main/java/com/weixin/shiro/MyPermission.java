package com.weixin.shiro;

import org.apache.shiro.authz.Permission;

/**
 * Created by ZPC on 2017/8/21.
 */
public class MyPermission implements Permission {

    private String resourceId;
    private String operator;
    private String instanceId;

    public MyPermission() {
    }

    public MyPermission(String permissionStr) {
        String[] strs = permissionStr.split("\\+");
        if (strs.length > 1) {
            this.setResourceId(strs[1]);
        }
        if (null == this.getResourceId() || "".equals(this.getResourceId())) {
            this.setResourceId("*");
        }
        if (strs.length > 2) {
            this.setOperator(strs[2]);
        }
        if (strs.length > 3) {
            this.setInstanceId(strs[3]);
        }
        if (null == this.getInstanceId() || "".equals(this.getInstanceId())) {
            this.setInstanceId("*");
        }

    }

    /**
     * 判断是否有相应的权限
     * @param permission
     * @return
     */
    @Override
    public boolean implies(Permission permission) {

        if (!(permission instanceof MyPermission)) {
            return false;
        }
        MyPermission mp = (MyPermission)permission;
        if (!this.getResourceId().equals("*") && !mp.getResourceId().equals(this.resourceId)) {
            return false;
        }
        if (!this.getOperator().equals("*") && !mp.getOperator().equals(this.operator)) {
            return false;
        }
        if (!this.getInstanceId().equals("*") && !mp.getInstanceId().equals(this.instanceId)) {
            return false;
        }

        return true;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getOperator() {
        return operator;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

}
