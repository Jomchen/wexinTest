package com.weixin.common;

import com.weixin.utils.JsonUtils;

/**
 * Created by zpc on 2017/5/2.
 */
public class ResultObj implements AbstractParent{


    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_FAIL = 0;


    public Object obj;

    public int code;

    public String msg;

    public ResultObj() {}

    public ResultObj(Integer code) {
        this.code = code;
    }

    public Object getObj() {
        return obj;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toJson() {
        return JsonUtils.objectToJson(this);
    }

}
