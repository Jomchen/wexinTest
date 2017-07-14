package com.weixin.test;

import com.weixin.entity.ColorEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by zpc on 2017/5/25.
 */
public class WeChatTest2 {

    public static void main(String[] args) throws ParseException {

        String startStr = "2017-01-01 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = sdf.parse(startStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, 365);
        System.out.println(sdf.format(calendar.getTime()));


    }

}
