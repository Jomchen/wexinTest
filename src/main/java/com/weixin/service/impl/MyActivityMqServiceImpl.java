package com.weixin.service.impl;

import com.weixin.service.MyActivityMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * Created by zpc on 2017/5/9.
 */
@Service("MyActivityMqService")
public class MyActivityMqServiceImpl implements MyActivityMqService {

    @Autowired
    private JmsTemplate jmsTemplate;

    /*父类接口应该写为Destination
    如果是点对点实现类为Queue，如果是订阅模式实现类则应该是Topic*/
    @Autowired
    @Qualifier("demoQueueDestination")
    private Destination demoQueueDestination;

    private Logger logger = LoggerFactory.getLogger(getClass());


    public void sendMessage(Destination destination, String msg) {
        logger.warn(Thread.currentThread().getName() + "向队列" + destination.toString() + "发送了消息为：" + msg);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                /*ObjectMessage objectMessage = session.createObjectMessage();
                objectMessage.setObject(customer);
                return objectMessage;*/

                /*TextMessage textMessage = session.createTextMessage(msg);
                return textMessage;*/

                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setObject("customer", new Integer(3));
                mapMessage.setString("key", "cus");
                return mapMessage;
            }
        });
    }


    public void sendMessage(String msg) {

        /*Destination destination = jmsTemplate.getDefaultDestination();*/

        jmsTemplate.send(demoQueueDestination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                /*Customer customer = new Customer(1, "李刚", 22, "北京", new Date());
                ObjectMessage objectMessage = session.createObjectMessage();
                objectMessage.setObject(customer);
                return objectMessage;*/

                /*TextMessage textMessage = session.createTextMessage(msg);
                return textMessage;*/

                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setObject("customer", new Integer(3));
                mapMessage.setString("key", "cus");
                return mapMessage;
            }
        });
    }


    public Message receive(Destination destination) {

        MapMessage mapMessage = (MapMessage)jmsTemplate.receive(destination);
        try {
            Integer integer = (Integer)mapMessage.getObject("customer");
            logger.warn("【消息接收端处理消息为：" + integer + "】。。。");
        } catch (JMSException e) {
            logger.warn("接收参数出错了！！" + e.getMessage());
        }

        return mapMessage;
    }


    @Override
    public Message receive() {

        MapMessage mapMessage = (MapMessage)jmsTemplate.receive(demoQueueDestination);
        try {
            Integer integer = (Integer)mapMessage.getObject("customer");
            logger.warn("【消息接收端处理消息为：" + integer + "】。。。");
        } catch (JMSException e) {
            logger.warn("接收参数出错了！！" + e.getMessage());
        }

        return mapMessage;
    }

}
