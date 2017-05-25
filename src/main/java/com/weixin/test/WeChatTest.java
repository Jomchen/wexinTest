package com.weixin.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

        String output = "json";                                         // 输出格式为json或者xml
        String ret_coordtype = "gcj02ll";                               // 可选参数，添加后返回国测局经纬度坐标或百度米制坐标
        String ak = "wh9qde51lnbHoQIez8p954oM77aDhT5N";                 // AK
        String sk = "qGN2OnRRds1OuIsWQk95xpjj3serjdSG";                 // SK
        String callback = "renderReverse";                              // 将json格式的返回值通过callback函数返回以实现jsonp功能，showLocation(JavaScript函数名)

        String coordtype = "gcj02ll";                                   // 坐标的类型，目前支持的坐标类型包括：bd09ll（百度经纬度坐标，默认）、bd09mc（百度米制坐标）、gcj02ll（国测局经纬度坐标）、wgs84ll（ GPS经纬度）
        String location = "38.76623,116.43213";                         // 根据经纬度坐标获取地址。支持批量，多组坐标间用|分隔，单次请求最多解析20组坐标。超过20组取前20组解析。批量解析需使用batch参数。批量解析仅召回行政区划数据。38.76623,116.43213lat<纬度>,lng<经度>
        String batch = "false";                                         // 请求为批量时必须，batch=true；若batch=false或为空，请求只解析第一组坐标。
        String pois = "0";                                              // 是否显示指定位置周边的poi，0为不显示，1为显示。当值为1时，默认显示周边1000米内的poi。
        String radius = "1000";                                         // poi召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。

        Map<String, String> paramMap = new TreeMap<>();
        paramMap.put("callback", callback);
        paramMap.put("pois", pois);
        paramMap.put("location", location);
        paramMap.put("ret_coordtype", ret_coordtype);
        paramMap.put("output", output);
        paramMap.put("zk", "ZK");
        paramMap.put("coordtype", coordtype);
        paramMap.put("ak", ak);
        paramMap.put("sn", "SNSNSNSNSNSNSNSNSNSN");

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.putAll(paramMap);
        System.out.println(linkedHashMap);
    }



}


