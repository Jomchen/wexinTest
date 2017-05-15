package com.weixin.service;

import javax.jms.Destination;
import javax.jms.Message;

/**
 * Created by zpc on 2017/5/9.
 */
public interface MyActivityMqService {

    void sendMessage(final Destination destination, final String msg);
    void sendMessage(final String msg);
    Message receive(Destination destination);
    Message receive();

}
