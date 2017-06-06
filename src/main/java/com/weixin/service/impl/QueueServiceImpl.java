package com.weixin.service.impl;

import com.weixin.service.QueueService;
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
@Service("queueService")
public class QueueServiceImpl implements QueueService {

    /*
     * 父类接口应该写为Destination
     * 如果是点对点实现类为Queue，如果是订阅模式实现类则应该是Topic
     */
    @Autowired
    @Qualifier("demoQueueDestination")
    private Destination demoQueueDestination;
    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsQueueTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());


    public void sendMessage(Destination destination, String msg) {
        logger.warn(Thread.currentThread().getName() + "向队列发送了消息为：" + msg);
        jmsQueueTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                /*ObjectMessage objectMessage = session.createObjectMessage();
                objectMessage.setObject(customer);
                return objectMessage;*/

                /*TextMessage textMessage = session.createTextMessage(msg);
                textMessage.setStringProperty("head", "这是标题");
                textMessage.setText("这是设置内容");*/

                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setObject("customer", new Integer(3));
                mapMessage.setString("data", msg);
                return mapMessage;
            }
        });
    }


    public void sendMessage(String msg) {

        /*Destination destination = jmsTemplate.getDefaultDestination();*/
        logger.warn(Thread.currentThread().getName() + "向队列发送了消息为：" + msg);
        jmsQueueTemplate.send(demoQueueDestination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                /*Customer customer = new Customer(1, "李刚", 22, "北京", new Date());
                ObjectMessage objectMessage = session.createObjectMessage();
                objectMessage.setObject(customer);
                return objectMessage;*/

                /*TextMessage textMessage = session.createTextMessage(msg);
                textMessage.setStringProperty("head", "这是标题");
                textMessage.setText("这是设置内容");*/

                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setObject("customer", new Integer(3));
                mapMessage.setString("data", msg);
                return mapMessage;
            }
        });
    }


    public Message receive(Destination destination) {

        logger.warn(Thread.currentThread().getName() + "向队列处理了消息为");
        MapMessage mapMessage = (MapMessage)jmsQueueTemplate.receive(destination);
        try {
            String str = mapMessage.getString("data");
            Object obj = mapMessage.getObject("customer");
            logger.warn("【消息接收端处理消息为：" + str + "----" + obj + "】。。。");
        } catch (JMSException e) {
            logger.warn("消费者获取参数出错了！！" + e.getMessage());
        }

        return mapMessage;
    }


    @Override
    public Message receive() {

        logger.warn(Thread.currentThread().getName() + "向队列处理了消息为");
        MapMessage mapMessage = (MapMessage)jmsQueueTemplate.receive(demoQueueDestination);
        try {
            String str = mapMessage.getString("data");
            Object obj = mapMessage.getObject("customer");
            logger.warn("【消息接收端处理消息为：" + str + "----" + obj + "】。。。");
        } catch (JMSException e) {
            logger.warn("消费者获取参数出错了！！" + e.getMessage());
        }

        return mapMessage;
    }

}
