package com.weixin.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by zpc on 2017/3/19.
 */
public class WeChatTest {

    public static void main(String[] args) throws ParseException, InterruptedException {
        String dateStr = "2017-04-11 11:50:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Queue queue = new PriorityQueue();
        Queue<String> arrayDeque2 = new ArrayDeque<>();
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.offer("第一");
        arrayDeque.offer("第二");
        arrayDeque.offer("第三");
        arrayDeque.offer("第四");
        System.out.println(arrayDeque);


    }

}


