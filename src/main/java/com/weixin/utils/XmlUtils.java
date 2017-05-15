package com.weixin.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zpc on 2017/3/19.
 */
public class XmlUtils {

    private static final Logger logger = LoggerFactory.getLogger(XmlUtils.class);



    /**
     * 获取根节点下的第一层数据，并转为Map<String, String>
     * @param sourceString 接收到的XML源字符串
     * @param target 您要获取的节点名字
     * @return 节点内的内容
     */
    public synchronized static Map<String, String> getResultMap(String sourceString) {
        Map<String, String> map = null;

        try {
            Document document = DocumentHelper.parseText(sourceString); // 将xml格式字符串转化为DOM对象
            Element rootElement = document.getRootElement();    // 获取根结点对象

            // 循环根节点，获取其子节点
            for (Iterator iter = rootElement.elementIterator(); iter.hasNext();) {
                Element element = (Element) iter.next(); // 获取标签对象
                String tagName = element.getName();     // 获取该标签对象的名称
                String tagContent = element.getTextTrim();  // 获取该标签对象的内容
                logger.warn(tagName + ": " + tagContent + "  "); // 输出内容
                if (null == map) {
                    map = new HashMap<String, String>();
                }
                map.put(tagName, tagContent);
            }
        } catch (DocumentException e) {
            logger.warn("【XML解析错误。。。。。。】");
            return null;
        }

        return map;
    }





    /**
     * 将Map<String, String>转换为要发送给微信的xml格式的字符串
     * @param parameterMap 您要传入的微信xml有关的标签值和值
     * @return 返回xml的字符串
     */
    public synchronized static String stringToWxForXML(Map<String, String> parameterMap) {

        if(null != parameterMap && parameterMap.size() >0) {
            StringBuffer resultXml = new StringBuffer();
            Set<Map.Entry<String, String>> set = parameterMap.entrySet();
            Iterator<Map.Entry<String, String>> iterator = set.iterator();

            resultXml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><xml>");
            while(iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String elementName = entry.getKey();
                String elementValue = entry.getValue();
                resultXml.append("<").append(elementName).append(">");
                resultXml.append(elementValue);
                resultXml.append("</").append(elementName).append(">");
            }
            resultXml.append("</xml>");

            ergodic(resultXml.toString());
            return resultXml.toString();
        } else {
            return "";
        }
    }






    /**
     * 将集合转换为微信反馈回来或响应微信的XML数据字符串
     * @param dataMap 要求的xml的对应键值对集合
     * @return
     */
    public synchronized static String resultData(Map<String, String> dataMap) {

        Set<Map.Entry<String, String>> set = dataMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        StringBuilder resultData = new StringBuilder();

        resultData.append("<xml>");
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            resultData.append("<"); resultData.append(key); resultData.append(">");
            resultData.append("<![CDATA[");resultData.append(value);resultData.append("]]>");
            resultData.append("</"); resultData.append(key); resultData.append(">");
        }
        resultData.append("</xml>");

        ergodic(resultData.toString());
        return resultData.toString();

    }






    /**
     * 完全遍历XML数据
     * @param sourceString 您传入的XML源数据
     */
    public synchronized static void ergodic(String sourceString) {
        try {
            Document document = DocumentHelper.parseText(sourceString); // 将xml格式字符串转化为DOM对象
            Element rootElement = document.getRootElement();    // 获取根结点对象

            // 循环根节点，获取其子节点
            logger.warn("<xml>\n");
            for (Iterator iter = rootElement.elementIterator(); iter.hasNext();) {
                Element element = (Element) iter.next(); // 获取标签对象
                String tagName = element.getName();     // 获取该标签对象的名称
                String tagContent = element.getTextTrim();  // 获取该标签对象的内容
                logger.warn("<" + tagName + ">");
                logger.warn(tagContent);
                logger.warn("</" + tagName + ">\n");
            }
            logger.warn("</xml>\n");
        } catch (DocumentException e) {
            logger.warn("【遍历方法，XML解析错误。。。。。。】");
        }
    }


}
