package com.weixin.utils;

import java.util.*;

/**
 * Created by zpc on 2017/3/19.
 */
public class WeChatUtils {


    /**
     * 字典排序
     * @param list
     * @return
     */
    public synchronized static String wechatDictionarySorting(List<String> list) {

        if (null == list || list.isEmpty()) { return ""; }
        StringBuffer stringBuffer = new StringBuffer();
        Collections.sort(list);
        for (int i = 0; i < list.size(); i ++) {
            stringBuffer.append(list.get(i));
        }

        return stringBuffer.toString();
    }


    /**
     * 微信支付传参字典排序
     * @param map
     * @return
     */
    public synchronized static String dictionarySorting(Map<String, String> map) {

        if (null != map && !map.isEmpty()) {
            Collection<String> collection = map.keySet();
            List<String> list = new ArrayList<String>(collection);
            Collections.sort(list);

            StringBuilder dataString = new StringBuilder();
            for(int i = 0; i < list.size(); i ++) {
                String key = list.get(i);
                String value = map.get(key);
                if (null != map.get(key) && !map.get(key).isEmpty()) {
                    if (i == 0) {
                        dataString.append(key).append("=").append(value);
                    } else {
                        dataString.append("&").append(key).append("=").append(value);
                    }
                }
            }

            return dataString.toString();
        } else {
            return "";
        }
    }



    /** 微信支付反馈后字典排序
     * @param map
     * @return
     */
    public synchronized static String dictionarySortingWxFeedBack(Map<String, String> map) {

        if (null != map && !map.isEmpty()) {
            Collection<String> collection = map.keySet();
            List<String> list = new ArrayList<String>(collection);
            Collections.sort(list);

            StringBuilder dataString = new StringBuilder();
            for(int i = 0; i < list.size(); i ++) {
                String key = list.get(i);
                String value = map.get(key);
                if (null != map.get(key) && !map.get(key).isEmpty()) {
                    if (!"sign".equals(key)) {
                        if (i == 0) {
                            dataString.append(key).append("=").append(value);
                        } else {
                            dataString.append("&").append(key).append("=").append(value);
                        }
                    }
                }
            }

            return dataString.toString();
        } else {
            return "";
        }
    }

}
