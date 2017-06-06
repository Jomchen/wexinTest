package com.weixin.service;

import javax.jms.Destination;
import javax.jms.Message;

/**
 * Created by zpc on 2017/6/6.
 */
public interface TopicTwoService {

    void sendMessage(final Destination destination, final String msg);
    void sendMessage(final String msg);
    Message receive(Destination destination);
    Message receive();

}
