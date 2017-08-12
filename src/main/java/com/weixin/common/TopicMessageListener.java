package com.weixin.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by ZPC on 2017/8/12.
 */
public class TopicMessageListener implements MessageListener {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage)message;
        try {
            String msg = textMessage.getText();
            logger.warn("【监听到了信息：" + msg + "】");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
