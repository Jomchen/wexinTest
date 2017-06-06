package com.weixin.service.impl;

import com.weixin.service.TopicTwoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;

/**
 * Created by zpc on 2017/6/6.
 */
@Transactional
@Service("topicTwoService")
public class TopicTwoServiceImpl implements TopicTwoService {

    @Autowired
    @Qualifier("jmsTopicTemplate")
    JmsTemplate jmsTopicTemplate;
    @Autowired
    @Qualifier("demoTopicDestination")
    Destination demoTopicDestination;
    Logger logger = LoggerFactory.getLogger(TopicTwoServiceImpl.class);

    @Override
    public void sendMessage(Destination destination, String msg) {}

    @Override
    public void sendMessage(String msg) {
        logger.warn(Thread.currentThread().getName() + "向订阅模式 2 发送消息为：" + msg);
        jmsTopicTemplate.send(demoTopicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setStringProperty("head", "这是头信息" + msg);
                textMessage.setText("这是消息体，消息为：" + msg);
                return textMessage;
            }
        });
    }

    @Override
    public Message receive(Destination destination) {
        return null;
    }

    @Override
    public Message receive() {
        TextMessage textMessage = (TextMessage)jmsTopicTemplate.receive(demoTopicDestination);
        String head;
        String data;
        try {
            head = textMessage.getStringProperty("head");
            data = textMessage.getText();
            logger.warn("订阅模式 2 消费的消息为：" + head + "---" + data);
        } catch (JMSException e) {
            logger.warn("订阅模式 2 在消费消息的时候出错了！！");
        }
        return textMessage;
    }

}
