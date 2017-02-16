package org.lavenderx.mongodb.queue;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author baymax
 * @since 1.0
 */
public class SimpleProducer implements Producer {

    private MongoClient mongoClient;
    private MongoDatabase queueDb;

    public SimpleProducer() {
        this(MongoClientHelper.getMongoClient(), MongoClientHelper.getMongoConfig().getQueueDbName());
    }

    public SimpleProducer(MongoClient mongoClient, String queueDbName) {
        this.mongoClient = mongoClient;
        this.queueDb = this.mongoClient.getDatabase(queueDbName);
    }

    @Override
    public void send(String topic, BasicDBObject message) {

    }

    @Override
    public boolean safeSend(String topic, BasicDBObject message) {
        return false;
    }
}
