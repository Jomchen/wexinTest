package com.weixin.task;


import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * Created by zpc on 2017/4/7.
 */

@Component
public class TimerTaskTwo {

    @Scheduled(cron  = "0/5 * * * * ?")
    public void testJob2() {
        System.out.println("【这是第二个计时器】。。。");
    }

}
