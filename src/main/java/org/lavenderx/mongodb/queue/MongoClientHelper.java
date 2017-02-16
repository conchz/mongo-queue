package org.lavenderx.mongodb.queue;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * @author baymax
 * @since 1.0
 */
public class MongoClientHelper {

    private static MongoClient mongoClient;
    private static Config mongoConfig;
    private static Throwable initException;

    static {
        try {
            Config config = Config.parse("/mongodb-queue.properties");
            mongoConfig = config;

            String uri = "mongodb://"
                    + config.getUsername() + ":"
                    + config.getPassword() + "@"
                    + config.getMongoServers() + "/?authSource="
                    + config.getQueueDbName();
            MongoClientURI mongoClientURI = new MongoClientURI(uri, config.getMongoOptionsBuilder());
            mongoClient = (MongoClient) new Mongo.Holder().connect(mongoClientURI);
        } catch (Throwable t) {
            initException = t;
        }

    }

    public static MongoClient getMongoClient() {
        if (initException != null) {
            throw new IllegalStateException(initException);
        }

        return mongoClient;
    }

    public static Config getMongoConfig() {
        if (initException != null) {
            throw new IllegalStateException(initException);
        }

        return mongoConfig;
    }
}
