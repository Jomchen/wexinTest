package com.weixin.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * Created by zpc on 2017/5/2.
 * 消息队列的监听器会处理队列信息
 * 消费者在处理的的时候就会没有信息
 */
public class QueueMessageListener implements MessageListener {

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
