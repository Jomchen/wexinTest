package com.weixin.controller;

import com.weixin.service.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

}
