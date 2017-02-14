package com.github.lavenderx.mongodb.queue.core;

/**
 * @author baymax
 * @since 1.0
 */
public interface Consumer {

    /**
     * 消息订阅
     *
     * @param topic
     * @param listener
     */
    void subscribe(String topic, MessageListener listener);

    /**
     * 关闭Consumer
     */
    void shutdown();

}
