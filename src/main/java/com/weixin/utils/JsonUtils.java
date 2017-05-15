package com.weixin.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by zpc on 2017/4/10.
 */
public class JsonUtils {

    /**
     * @param obj 对象
     * @return 返回对象的json
     */
    public static String objectToJson(Object obj ){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
    /**
     * 把model对象转换成JSONObject.
     * @author XUJUN
     * @date 2013-1-10
     * @param obj model对象
     * @return JSONObject
     */
    public static JSONObject toJSONObject(Object obj){
        return JSONObject.fromObject(obj);
    }
    /**
     * 把集合类型的转换成JSONArray.
     * @author XUJUN
     * @date 2013-1-10
     * @param obj 集合类型的
     * @return JSONArray
     */
    /*public static JSONArray toJSONArrayList(Object obj){
        return JSONArray.fromObject(obj);
    }*/



    /**
     * @param jsonStr
     * @param clazz
     * @return 返回对象
     */
    public static Object jsonToObject(String jsonStr ,Class<?> clazz){
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, clazz);
    }

    public static Object JSONToObject(String jsonStr ,Class<?> clazz){
        JSONObject jsonobject = JSONObject.fromObject(jsonStr);
        return JSONObject.toBean(jsonobject, clazz);
    }

    /**
     * @param map
     * @return 把map转成String
     */
    public static String MapToJson(Map<String, ? extends Object> map){
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    /**
     * @param jsonStr json串
     * @return 把json转成map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseJSON2Map(String jsonStr){
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    public static Map<String, Object> json2Map(String jsonStr){
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<Object> list = new ArrayList<Object>();
                Iterator<?> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    Object json2 = it.next();
                    list.add(json2);
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    /**
     * 把json转换成list
     * @param json
     * @return
     */
    public static List<Long>  Json2List( String json){
        Gson gson = new Gson();
        List<Long> jts = gson.fromJson(json, new TypeToken<List<Long>>(){}.getType());
        return jts;
    }

    /**
     * 把json转换成list
     * @param json
     * @return
     */
    public static Map<String,List<String>>  Json2Listerer( String json){
        Gson gson = new Gson();
        Map<String,List<String>> jts = gson.fromJson(json, new TypeToken<Map<String,List<String>>>(){}.getType());
        return jts;
    }

    /**
     * 把json转换成list<Object>
     * @author LiJun
     * @param <T>
     * @date 2013-1-30
     * @param json
     * @return
     */
    public static <T> T json2ObjList(String json, TypeToken<T> t){
        Gson gson = new Gson();
        T jts = gson.fromJson(json, t.getType());
        return jts;
    }

}
