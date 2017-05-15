package com.weixin.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zpc on 2017/4/7.
 */
public class TimerTaskFirst {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void testJob() {
        System.out.println("这是计时器，当期时间为：" + sdf.format(new Date()));
    }

}
