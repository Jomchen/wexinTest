package com.weixin.utils;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Created by zpc on 2017/7/27.
 */
public class MyRedis {

    public  static void testString(Jedis jedis) {
        //设置 redis 字符串数据
        jedis.set("name00", "Hello Linux");
        // 获取存储的数据并输出
        System.out.println("Stored string in redis:: "+ jedis.get("name00"));
    }

    public static void testList(Jedis jedis) {
        /*//存储数据到列表中
        jedis.lpush("name-list", "Linux");
        jedis.lpush("name-list", "Java");
        jedis.lpush("name-list", "C");*/
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("name-list", 0 ,5);
        for(int i=0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }
    }

    public static void testKeys(Jedis jedis) {
        // 获取数据并输出
        Set<String> setList = jedis.keys("*");
        for (String s : setList) {
            System.out.println("This key is :: " + s);
        }
    }


    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());

        testKeys(jedis);
    }

}
