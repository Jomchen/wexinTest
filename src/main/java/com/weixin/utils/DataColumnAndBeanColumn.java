package com.weixin.utils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zpc on 2017/3/19.
 */
public class DataColumnAndBeanColumn {

    /**
     * 将数据库字段格式转为java属性格式
     * @param columnTemp 数据库格式的字段
     * @return java属性格式的字段
     */
    public synchronized static String convertToJava(String columnTemp) {

        if (null != columnTemp && columnTemp.length() > 0) {
            String[] columnArray = columnTemp.split("_");
            StringBuffer resultData = new StringBuffer();
            if(null != columnArray && columnArray.length > 0) {
                for(int i = 0; i < columnArray.length; i ++) {
                    String word = columnArray[i];
                    String firstChar = word.substring(0, 1);
                    String otherChars = word.substring(1);
                    String toFirstChar;
                    if(i != 0) {
                        toFirstChar = firstChar.toUpperCase();
                    } else {
                        toFirstChar = firstChar;
                    }
                    resultData.append(toFirstChar).append(otherChars);
                }
                return resultData.toString();
            }
            return "";
        }
        return "";

    }





    /**
     * 根据map的键和值进行对对象赋值
     * @param t 传入一个对象以指定兑现各类型
     * @param xmlMap 带键和值的map对象
     * @param <T> 对象类型
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public synchronized static <T> T InjectedObj(T t, Map<String, String> xmlMap, boolean isAllNew)
            throws IllegalAccessException, InstantiationException {

        Class<T> cla = (Class<T>) t.getClass();
        Object obj;
        if (isAllNew) {
            obj = cla.newInstance();
        } else {
            obj = t;
        }
        Field[] fieldArray = cla.getDeclaredFields();

        for (Field field : fieldArray) {
            field.setAccessible(true);
            Set<Map.Entry<String, String>> entrySet = xmlMap.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String elementName = entry.getKey();
                String elementValue = entry.getValue();
                String toElementName = DataColumnAndBeanColumn.convertToJava(elementName);

                if (toElementName.equals(field.getName())) {
                    field.set(obj, elementValue);
                    continue;
                }
            }
        }

        return (T)obj;
    }

}
