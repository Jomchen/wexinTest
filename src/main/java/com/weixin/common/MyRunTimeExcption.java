package com.weixin.common;

/**
 * Created by zpc on 2017/5/9.
 */
public class MyRunTimeExcption extends RuntimeException {

    public MyRunTimeExcption() {
        super();
    }

    public MyRunTimeExcption(String message) {
            super(message);
    }

}
