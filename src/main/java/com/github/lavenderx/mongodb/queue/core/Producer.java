package com.github.lavenderx.mongodb.queue.core;

import com.mongodb.BasicDBObject;

/**
 * @author baymax
 * @since 1.0
 */
public interface Producer {

    /**
     * 快速发送消息
     *
     * @param topic
     * @param message
     */
    void send(String topic, BasicDBObject message);

    /**
     * 全方式发送，等待服务端响应结果
     *
     * @param topic
     * @param message
     * @return
     */
    boolean safeSend(String topic, BasicDBObject message);
}
