package com.weixin.controller;

import com.weixin.service.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * Created by zpc on 2017/5/2.
 * 消息队列的测试
 */
@RequestMapping(value = "/myActivityMq")
@Controller
public class MyActivityMqController {

    @Autowired
    private QueueService queueService;

    Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 消息队列生产者
     * @return
     */
    @RequestMapping(value = "/SendMessage", method = RequestMethod.GET)
    @ResponseBody
    public String SendMessage(String data) {

        data = (null == data ? String.valueOf(System.currentTimeMillis()) : data);

        logger.info("【" + Thread.currentThread().getName() + "线程 发送到jms Start】。。。");
        queueService.sendMessage("我在生产队列信息：" + data);
        logger.info("【" + Thread.currentThread().getName() + "线程 发送到jms End】。。。");
        return "send success";
    }


    /**
     * 消息队列消费者
     * @return
     */
    @RequestMapping(value = "/ReceiveMessage", method = RequestMethod.GET)
    @ResponseBody
    public String ReceiveMessage() {
        logger.info("【" + Thread.currentThread().getName() + "线程 从jms消费 Start】。。。");
        queueService.receive();
        logger.info("【" + Thread.currentThread().getName() + "线程 从jms消费 End】。。。");
        return "receive success";
    }

}
