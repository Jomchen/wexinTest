package com.weixin.controller;

import com.weixin.service.QueueService;
import com.weixin.service.TopicOneService;
import com.weixin.service.TopicTwoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zpc on 2017/5/2.
 * 消息队列的测试
 */
@RequestMapping(value = "/myActivityMq")
@Controller
public class MyActivityMqController {

    @Autowired
    private QueueService queueService;
    @Autowired
    @Qualifier("topicOneService")
    private TopicOneService topicOneService;
    @Autowired
    @Qualifier("topicTwoService")
    private TopicTwoService topicTwoService;

    Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 消息队列生产者
     * @return
     */
    @RequestMapping(value = "/sendQueueMessage", method = RequestMethod.GET)
    @ResponseBody
    public String sendQueueMessage(String data) {

        data = (null == data ? String.valueOf(System.currentTimeMillis()) : data);

        logger.info("【" + Thread.currentThread().getName() + "线程在控制层 向队列发送了信息 START】。。。");
        queueService.sendMessage("我在生产队列信息：" + data);
        logger.info("【" + Thread.currentThread().getName() + "线程在控制层 向队列发送了信息 END】。。。");
        return "sendQueue success";
    }
    /**
     * 消息队列消费者
     * @return
     */
    @RequestMapping(value = "/receiveQueueMessage", method = RequestMethod.GET)
    @ResponseBody
    public String receiveQueueMessage() {
        logger.info("【" + Thread.currentThread().getName() + "线程在控制层 向队列处理了信息 START】。。。");
        queueService.receive();
        logger.info("【" + Thread.currentThread().getName() + "线程在控制层 向队列处理了信息 End】。。。");
        return "receiveQueue success";
    }


    /**
     * 订阅模式 1 信息生产者
     * @param msg
     * @return
     */
    @RequestMapping(value = "/sendTopicOneMessage", method = RequestMethod.GET)
    @ResponseBody
    public String sendTopicOneMessage(String msg) {
        logger.warn(Thread.currentThread().getName() + "线程在控制层 向订阅模式 1 发送了消息 START");
        topicOneService.sendMessage(msg);
        logger.warn(Thread.currentThread().getName() + "线程在控制层 向订阅模式 1 发送了消息 END");
        return "sendTopicOne success";
    }
    /**
     * 订阅模式 1 信息消费者
     * @param msg
     * @return
     */
    @RequestMapping(value = "/receiveTopicOneMessage", method = RequestMethod.GET)
    @ResponseBody
    public String receiveTopicOneMessage(String msg) {
        logger.warn(Thread.currentThread().getName() + "线程在控制层 向订阅模式 1 处理消息 START");
        topicOneService.receive();
        logger.warn(Thread.currentThread().getName() + "线程在控制层 向订阅模式 1 处理消息 END");
        return "receiveTopicOne success";
    }
    /**
     * 订阅模式 2 信息生产者
     * @param msg
     * @return
     */
    @RequestMapping(value = "/sendTopicTwoMessage", method = RequestMethod.GET)
    @ResponseBody
    public String sendTopicTwoMessage(String msg) {
        logger.warn(Thread.currentThread().getName() + "线程在控制层 向订阅模式 2 发送了消息 START");
        topicTwoService.sendMessage(msg);
        logger.warn(Thread.currentThread().getName() + "线程在控制层 向订阅模式 2 发送了消息 END");
        return "sendTopicTwo success";
    }
    /**
     * 订阅模式 2 信息消费者
     * @param msg
     * @return
     */
    @RequestMapping(value = "/receiveTopicTwoMessage", method = RequestMethod.GET)
    @ResponseBody
    public String receiveTopicTwoMessage(String msg) {
        logger.warn(Thread.currentThread().getName() + "线程在控制层 向订阅模式 2 处理消息 START");
        topicTwoService.receive();
        logger.warn(Thread.currentThread().getName() + "线程在控制层 向订阅模式 2 处理消息 END");
        return "receiveTopicTwo success";
    }

}
