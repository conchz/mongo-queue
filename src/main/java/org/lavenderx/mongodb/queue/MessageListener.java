package org.lavenderx.mongodb.queue;

import com.mongodb.BasicDBObject;

import java.util.concurrent.Executor;

/**
 * @author baymax
 * @since 1.0
 */
public interface MessageListener {

    void receiveMessage(BasicDBObject message);

    Executor getExecutor();
}
