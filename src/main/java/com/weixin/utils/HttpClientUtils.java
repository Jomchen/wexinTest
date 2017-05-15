package com.weixin.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Created by zpc on 2017/4/5.
 */
public class HttpClientUtils {

    public static final int DO_GET = 1;
    public static final int DO_POST = 2;

    public static final String RESULT_DATA = "resultData";
    public static final String RESULT_CODE = "resultCode";
    public static final String RESULT_MSG = "resultMsg";
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static HttpRequest getHttpType(int httpRequestType) {

        HttpRequest httpRequest = null;
        switch (httpRequestType) {
            case DO_GET: httpRequest = new HttpGet(); break;
            case DO_POST: httpRequest = new HttpPost(); break;
        }

        return httpRequest;
    }


    public static String assembleUrl(String url, Map<String, String> paramMap) {

        if (null == url) { url = ""; }
        if (null != paramMap || !paramMap.isEmpty()) { return url; }
        if (!url.contains("?")) {

            return url;
        }
        if (url.substring(url.length(), url.length() - 1).equals("?")) {

            return url;
        } else {

            return url;
        }


        /*Set<Map.Entry<String, String>> entrySet = paramMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
        }

        return url;*/
    }

    /**
     * GET 请求
     * @param url
     * @param paramMap
     * @return
     */
    public static Map<String, String> diGet(String url, Map<String, String> paramMap) {

        return null;
    }


    /**
     * POST 请求
     * @param url
     * @param paramMap
     * @return
     */
    public static Map<String, String> doPost(String url, Map<String, String> paramMap) {

        Map<String, String> resultMap = new HashMap<String, String>();
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse = null;
        HttpEntity httpEntity;
        List<NameValuePair> nameValuePairList = getParameter(paramMap);
        HttpPost httpPost = new HttpPost(url);
        UrlEncodedFormEntity urlEncodedFormEntity;

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();

        try {

            urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            httpPost.setEntity(urlEncodedFormEntity);
            closeableHttpResponse = closeableHttpClient.execute(httpPost);
            int responseCode = closeableHttpResponse.getStatusLine().getStatusCode();
            httpEntity = closeableHttpResponse.getEntity();

            if (responseCode != HttpStatus.SC_OK) {
                logger.warn("{请求失败，错误码为：" + responseCode + "}");
                resultMap.put(HttpClientUtils.RESULT_CODE, String.valueOf(responseCode));
                resultMap.put(HttpClientUtils.RESULT_MSG, "请求错误");
                throw new RuntimeException("请求不成功");
            }

            if (null == httpEntity) {
                logger.warn("{返回实体为空}");
            } else {
                inputStream = httpEntity.getContent();
                inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                bufferedReader = new BufferedReader(inputStreamReader);
                String strTmp;
                while ((strTmp = bufferedReader.readLine()) != null) {
                    stringBuffer.append(strTmp);
                }

                logger.warn("返回实体为：{" + stringBuffer.toString() + "}");
                resultMap.put(HttpClientUtils.RESULT_DATA, stringBuffer.toString());
            }
        } catch (UnsupportedEncodingException e) {
            logger.warn("错误：{" + e.getMessage() + "}");
            resultMap.put(HttpClientUtils.RESULT_MSG, e.getMessage());
        } catch (IOException e) {
            logger.warn("错误：{" + e.getMessage() + "}");
            resultMap.put(HttpClientUtils.RESULT_MSG, e.getMessage());
        } catch (RuntimeException e) {
            logger.warn("错误：{" + e.getMessage() + "}");
            resultMap.put(HttpClientUtils.RESULT_MSG, e.getMessage());
        } catch (Exception e) {
            logger.warn("错误：{" + e.getMessage() + "}");
            resultMap.put(HttpClientUtils.RESULT_MSG, e.getMessage());
        } finally {
            try {
                if (null != bufferedReader) bufferedReader.close();
                if (null != inputStreamReader) inputStreamReader.close();
                if (null != inputStream) inputStream.close();
                if (null != closeableHttpResponse) closeableHttpResponse.close();

                closeableHttpClient.close();
            } catch (IOException e) {
                logger.warn("资源关闭错误：{" + e.getMessage() + "}");
                resultMap.put(HttpClientUtils.RESULT_MSG, e.getMessage());
            }

            return resultMap;
        }
    }


    private static List<NameValuePair> getParameter(Map<String, String> paramMap) {

        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, String>> entrySet = paramMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();
            NameValuePair nameValuePair = new BasicNameValuePair(key, value);
            nameValuePairList.add(nameValuePair);
        }

        return nameValuePairList;
    }

}
