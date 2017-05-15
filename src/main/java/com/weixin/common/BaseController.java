package com.weixin.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zpc on 2017/3/19.
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);


    /**
     * 获取流传来的字符串
     * @param request
     * @return
     * @throws Exception
     */
    public String getStreamToStr(HttpServletRequest request) throws Exception {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            inputStream = request.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String strTmp = "";
            while((bufferedReader.readLine() != null)) {
                stringBuffer.append(strTmp);
                stringBuffer.append("\r\n");
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        }

        return stringBuffer.toString();
    }


    /**
     * 获取所有参数成一个集合
     * @param request
     * @return
     */
    protected Map<String, String> getAllParameter(HttpServletRequest request) {

        Map<String, String> resultMap = new HashMap<String, String>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            String value = request.getParameter(key);
            resultMap.put(key, value);
        }

        return resultMap;
    }

}
