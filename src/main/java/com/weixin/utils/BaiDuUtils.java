package com.weixin.utils;

import com.weixin.common.MyRunTimeExcption;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zpc on 2017/5/31.
 */
public class BaiDuUtils {
    private Logger logger = LoggerFactory.getLogger(BaiDuUtils.class);

    public static final String OUTPUT_JSON = "json";
    public static final String OUTPUT_XML = "xml";
    public static final String RET_COORDTYPE_GCJ02LL = "gcj02ll";
    public static final String RET_COORDTYPE_BD09MC = "bd09mc";

    /*
     * 坐标的类型，目前支持的坐标类型包括：
     * bd09ll（百度经纬度坐标，默认）
     * bd09mc（百度米制坐标）
     * gcj02ll（国测局经纬度坐标）
     * wgs84ll（ GPS经纬度）
     */
    public static final String COORDTYPE_BD09LL = "bd09ll";
    public static final String COORDTYPE_BD09MC = "bd09mc";
    public static final String COORDTYPE_GCJ02LL = "gcj02ll";
    public static final String COORDTYPE_wgs84ll = "wgs84ll";


    /**
     * 通过经纬度获取地址 & 通过地址获取经纬度
     */
    public static final String GET_ADDRESS_OR_LATITUDE_AND_LONGITUDE_URL = "http://api.map.baidu.com/geocoder/v2/";

    /* **** 通用参数 **** */
    String output;                              // 建议传      输出格式为json或者xml(默认json)
    String retCoordtype;                        // 建议传      可选参数，添加后返回国测局经纬度坐标或百度米制坐标(gcj02ll、bd09mc、默认为gcj02ll)
    String ak;                                  // 必传        百度账号提供AK
    String sk;                                  // 视情况      计算加密sn时需要用到(ak，sk由百度用户的账号提供)
    String callback;                            // 否必传      将json格式的返回值通过callback函数返回以实现jsonp功能，默认无，showLocation(JavaScript函数名)


    /**
     * 通过地址查询经纬度
     * @param address 地理位置
     * @param city 城市[ 可以不传入 ]
     * @param output 输出格式 [ json | xml]
     * @param retCoordtype 返回坐标类型 [ gcj02ll | bd09mc]
     */
    public String getLatitudeAndLongitudeByAddress(
            String address,
            String city,
            String output,
            String retCoordtype) throws Exception {

        String outputTmp = this.getOutput();
        String retCoordtypeTmp = this.getRetCoordtype();
        if (!StringUtils.isBlank(output)) {
            outputTmp = output;
        }
        if (!StringUtils.isBlank(retCoordtype)) {
            retCoordtypeTmp = retCoordtype;
        }
        if (StringUtils.isBlank(address)) {
            throw new MyRunTimeExcption("没有传入地理位置");
        }

        TreeMap<String, String> treeMap = new TreeMap<>();
        if (!StringUtils.isBlank(city)) {
            treeMap.put("city", city);
        }
        treeMap.put("callback", callback);
        treeMap.put("output", outputTmp);
        treeMap.put("address", address);
        treeMap.put("ret_coordtype", retCoordtypeTmp);
        treeMap.put("ak", ak);

        String parameter = toQueryString(treeMap);
        String urlTmp = "/geocoder/v2/?" + parameter + sk;
        String tempStr = URLEncoder.encode(urlTmp, "UTF-8");
        String sn = MD5(tempStr);

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.putAll(treeMap);
        linkedHashMap.put("sn", sn);
        Map<String, String> resultData = HttpClientUtils.doPost(
                BaiDuUtils.GET_ADDRESS_OR_LATITUDE_AND_LONGITUDE_URL,
                linkedHashMap
        );

        logger.warn("百度地图通过地址得到的经纬度信息为：\n" + resultData.get("resultData"));
        return resultData.get("resultData");
    }

    /**
     * @param lat 纬度
     * @param lng 经度
     * @param output 输出格式 [ json | xml]
     * @param retCoordtype 返回坐标类型 [ gcj02ll | bd09mc]
     */
    public String getAddressByLatitudeAndLongitude(
            String lat,
            String lng,
            String output,
            String retCoordtype,
            String coordtype) throws Exception {

        logger.warn("经度为：" + lng + "-----" + "纬度为：" + lat);

        /* ************** 此接口参数 ************** */
        String location = lat + "," + lng;                              // 必传       根据经纬度坐标获取地址。支持批量，多组坐标间用|分隔，单次请求最多解析20组坐标。超过20组取前20组解析。批量解析需使用batch参数。批量解析仅召回行政区划数据。格式：lat<纬度>,lng<经度> 范例：38.76623,116.43213
        String batch = "false";                                         // 否必传      请求为批量时必须，batch=true；若batch=false或为空，请求只解析第一组坐标。
        String pois = "0";                                              // 否必传      是否显示指定位置周边的poi，0为不显示，1为显示。当值为1时，默认显示周边1000米内的poi。
        String radius = "1000";                                         // 否必传      poi召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。

        String outputTmp = this.getOutput();
        String retCoordtypeTmp = this.getRetCoordtype();
        String coordtypeTmp = BaiDuUtils.COORDTYPE_GCJ02LL;
        if (!StringUtils.isBlank(output)) {
            outputTmp = output;
        }
        if (!StringUtils.isBlank(retCoordtype)) {
            retCoordtypeTmp = retCoordtype;
        }
        if (!StringUtils.isBlank(coordtype)) {
            coordtypeTmp = coordtype;
        }

        String reg = "^\\d+(\\.)?\\d+$";
        if (StringUtils.isBlank(lat)) {
            throw new MyRunTimeExcption("传入的纬度错误，其值为：" + lat);
        }
        if (!lat.matches(reg)) {
            throw new MyRunTimeExcption("传入的纬度值不是数字类型，其值为：" + lat);
        }
        if (StringUtils.isBlank(lng)) {
            throw new MyRunTimeExcption("传入的经度值错误，其值为：" + lng);
        }
        if (!lng.matches(reg)) {
            throw new MyRunTimeExcption("传入的经度值不是数字类型，其值为：" + lng);
        }


        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("callback", callback);
        treeMap.put("location", location);
        treeMap.put("output", outputTmp);
        treeMap.put("pois", pois);
        treeMap.put("ret_coordtype", retCoordtypeTmp);
        treeMap.put("coordtype", coordtypeTmp);
        treeMap.put("ak", ak);
        String parameter = toQueryString(treeMap);
        String urlTmp = "/geocoder/v2/?" + parameter + sk;
        String tempStr = URLEncoder.encode(urlTmp, "UTF-8");
        String sn = MD5(tempStr);

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.putAll(treeMap);
        linkedHashMap.put("sn", sn);
        Map<String, String> resultData = HttpClientUtils.doPost(
                BaiDuUtils.GET_ADDRESS_OR_LATITUDE_AND_LONGITUDE_URL,
                linkedHashMap);
        logger.warn("百度地图调用地址为：\n" + resultData.get("resultData"));
        return resultData.get("resultData");
    }


    // 对Map内所有value作utf8编码，拼接返回结果
    private String toQueryString(Map<?, ?> data) throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=")
                    .append(URLEncoder.encode((String) pair.getValue(),
                            "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }


    // 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
    private String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * 根据地址查询经纬度
     * @param address 详细地址
     * @param city 城市，例如 [ 北京市 ]
     * @return Map<String, String> [ lng: 经度 | lat: 纬度]
     */
    public Map<String, String> getCoordinate(String address, String city) {

        Map<String, String> resultMap = new HashMap<>();

        try {
            String mapData = getLatitudeAndLongitudeByAddress(address, city, null, null);
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(mapData);
            Object result = jsonObject.get("result");
            JSONObject resultJson = net.sf.json.JSONObject.fromObject(result);
            Object location = resultJson.get("location");
            JSONObject locationJson = net.sf.json.JSONObject.fromObject(location);
            String lng = locationJson.getString("lng");
            String lat = locationJson.getString("lat");

            resultMap.put("lng", lng);
            resultMap.put("lat", lat);
        } catch (Exception e) {
            logger.warn("【百度地图调取经纬度失败。。。】");
            logger.warn("地图错误信息为：" + e.getMessage());
            return resultMap;
        }

        return resultMap;
    }

    public String getArea(String latitude, String longitude) {
        String address = "";
        try {
            String mapData = this.getAddressByLatitudeAndLongitude(
                    latitude,
                    longitude,
                    null,
                    null,
                    null
            );

            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(mapData);
            Object obj = jsonObject.get("result");
            jsonObject = net.sf.json.JSONObject.fromObject(obj);
            obj = jsonObject.get("addressComponent");
            jsonObject = net.sf.json.JSONObject.fromObject(obj);
            address = (String) jsonObject.get("district");
            if (StringUtils.isBlank(address)) {
                throw new MyRunTimeExcption("address is null");
            }
        } catch (Exception e) {
            logger.warn("【百度地图调取地址失败。。。】");
            logger.warn("地图错误信息为：" + e.getMessage());
        }

        return address;
    }

    public String getOutput() {
        return output;
    }
    public String getRetCoordtype() {
        return retCoordtype;
    }
    public String getAk() {
        return ak;
    }
    public String getSk() {
        return sk;
    }
    public String getCallback() {
        return callback;
    }
    public void setOutput(String output) {
        this.output = output;
    }
    public void setRetCoordtype(String retCoordtype) {
        this.retCoordtype = retCoordtype;
    }
    public void setAk(String ak) {
        this.ak = ak;
    }
    public void setSk(String sk) {
        this.sk = sk;
    }
    public void setCallback(String callback) {
        this.callback = callback;
    }

}
