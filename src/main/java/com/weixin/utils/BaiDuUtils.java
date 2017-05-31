package com.weixin.utils;

import com.weixin.common.MyRunTimeExcption;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zpc on 2017/5/31.
 */
public class BaiDuUtils {
    private Logger logger = LoggerFactory.getLogger(BaiDuUtils.class);

    /**
     * 通过经纬度获取地址
     */
    public static final String GET_ADDRESS_BY_LATITUDE_AND_LONGITUDE_URL = "http://api.map.baidu.com/geocoder/v2/";

    /* **** 通用参数 **** */
    String output;                              // 建议传      输出格式为json或者xml(默认xml)
    String retCoordtype;                        // 建议传      可选参数，添加后返回国测局经纬度坐标或百度米制坐标(默认无)
    String ak;                                  // 必传        百度账号提供AK
    String sk;                                  // 视情况      计算加密sn时需要用到(ak，sk由百度用户的账号提供)
    String callback;                            // 否必传      将json格式的返回值通过callback函数返回以实现jsonp功能，默认无，showLocation(JavaScript函数名)


    /**
     * @param lat 纬度
     * @param lng 经度
     * @return
     */
    public String getGetAddressByLatitudeAndLongitude(String lat, String lng) throws Exception {

        /* ************** 此接口参数 ************** */
        String coordtype = "gcj02ll";                                   // 建议传      坐标的类型，目前支持的坐标类型包括：bd09ll（百度经纬度坐标，默认）、bd09mc（百度米制坐标）、gcj02ll（国测局经纬度坐标）、wgs84ll（ GPS经纬度）
        String location = lat + "," + lng;                              // 必传       根据经纬度坐标获取地址。支持批量，多组坐标间用|分隔，单次请求最多解析20组坐标。超过20组取前20组解析。批量解析需使用batch参数。批量解析仅召回行政区划数据。格式：lat<纬度>,lng<经度> 范例：38.76623,116.43213
        String batch = "false";                                         // 否必传      请求为批量时必须，batch=true；若batch=false或为空，请求只解析第一组坐标。
        String pois = "0";                                              // 否必传      是否显示指定位置周边的poi，0为不显示，1为显示。当值为1时，默认显示周边1000米内的poi。
        String radius = "1000";                                         // 否必传      poi召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。

        logger.warn("经度为：" + lng + "-----" + "纬度为：" + lat);

        String reg = "^\\d+(\\.)?\\d+$";
        if (StringUtils.isBlank(lat)) { throw new MyRunTimeExcption("传入的纬度错误，其值为：" + lat); }
        if (!lat.matches(reg)) { throw new MyRunTimeExcption("传入的纬度值不是数字类型，其值为：" + lat); }
        if (StringUtils.isBlank(lng)) { throw new MyRunTimeExcption("传入的经度值错误，其值为：" + lng); }
        if (!lng.matches(reg)) { throw new MyRunTimeExcption("传入的经度值不是数字类型，其值为：" + lng); }

        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("callback", callback);
        treeMap.put("location", location);
        treeMap.put("output", output);
        treeMap.put("pois", pois);
        treeMap.put("ret_coordtype", retCoordtype);
        treeMap.put("coordtype", coordtype);
        treeMap.put("ak", ak);
        String parameter = toQueryString(treeMap);
        String urlTmp = "/geocoder/v2/?" + parameter + sk;
        String tempStr = URLEncoder.encode(urlTmp, "UTF-8");
        String sn = MD5(tempStr);

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.putAll(treeMap);
        linkedHashMap.put("sn", sn);
        Map<String, String> resultMap = HttpClientUtils.doPost(
                BaiDuUtils.GET_ADDRESS_BY_LATITUDE_AND_LONGITUDE_URL,
                linkedHashMap);

        String addressData = resultMap.get(HttpClientUtils.RESULT_DATA);

        logger.warn("百度地图调用地址为：\n" + addressData);
        return addressData;
    }


    // 对Map内所有value作utf8编码，拼接返回结果
    private String toQueryString(Map<?, ?> data)
            throws UnsupportedEncodingException {
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
