package org.lavenderx.mongodb.queue;

import com.mongodb.MongoClientOptions;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author baymax
 * @since 1.0
 */
public class Config {

    private String username;
    private String password;
    private String queueDbName;
    private String mongoServers;
    private MongoClientOptions.Builder mongoOptionsBuilder;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQueueDbName() {
        return queueDbName;
    }

    public void setQueueDbName(String queueDbName) {
        this.queueDbName = queueDbName;
    }

    public String getMongoServers() {
        return mongoServers;
    }

    public void setMongoServers(String mongoServers) {
        this.mongoServers = mongoServers;
    }

    public MongoClientOptions.Builder getMongoOptionsBuilder() {
        return mongoOptionsBuilder;
    }

    public void setMongoOptionsBuilder(MongoClientOptions.Builder mongoOptionsBuilder) {
        this.mongoOptionsBuilder = mongoOptionsBuilder;
    }

    public static Config parse(String confName) {
        try (InputStream inputStream = Config.class.getResourceAsStream(confName)) {
            Properties props = new Properties();
            props.load(inputStream);

            Config config = new Config();

            String username = props.getProperty("mongodb.username");
            if (username == null) {
                throw new ConfigException("Mongo authentication username can not be NULL");
            }
            config.setUsername(username);

            String password = props.getProperty("mongodb.password");
            if (password == null) {
                throw new ConfigException("Mongo authentication password can not be NULL");
            }
            config.setPassword(password);

            String queueDbName = props.getProperty("mongodb.authentication-database");
            if (queueDbName == null) {
                throw new ConfigException("Mongo authentication database must be specified");
            }
            config.setQueueDbName(queueDbName);

            String servers = props.getProperty("mongodb.servers", "localhost:27017");
            config.setMongoServers(servers);

            config.setMongoOptionsBuilder(parserMongoClientOptionsBuilder(props));

            return config;
        } catch (Exception e) {
            throw new ConfigException("Fail to load config [confName = " + confName + "]", e);
        }
    }

    private static MongoClientOptions.Builder parserMongoClientOptionsBuilder(Properties props) {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();

        String alwaysUseMBeans = props.getProperty("alwaysUseMBeans");
        String connectionsPerHost = props.getProperty("connectionsPerHost");
        String connectTimeout = props.getProperty("connectTimeout");
        String cursorFinalizerEnabled = props.getProperty("cursorFinalizerEnabled");
        String description = props.getProperty("description");
        String heartbeatConnectTimeout = props.getProperty("heartbeatConnectTimeout");
        String heartbeatFrequency = props.getProperty("heartbeatFrequency");
        String heartbeatSocketTimeout = props.getProperty("heartbeatSocketTimeout");
        String maxConnectionIdleTime = props.getProperty("maxConnectionIdleTime");
        String maxConnectionLifeTime = props.getProperty("maxConnectionLifeTime");
        String maxWaitTime = props.getProperty("maxWaitTime");
        String minConnectionsPerHost = props.getProperty("minConnectionsPerHost");
        String requiredReplicaSetName = props.getProperty("requiredReplicaSetName");
        String socketKeepAlive = props.getProperty("socketKeepAlive");
        String socketTimeout = props.getProperty("socketTimeout");

        if (alwaysUseMBeans != null) {
            builder.alwaysUseMBeans(Boolean.valueOf(alwaysUseMBeans));
        }

        if (connectionsPerHost != null) {
            builder.connectionsPerHost(Integer.valueOf(connectionsPerHost));
        }

        if (connectTimeout != null) {
            builder.connectTimeout(Integer.valueOf(connectTimeout));
        }

        if (cursorFinalizerEnabled != null) {
            builder.cursorFinalizerEnabled(Boolean.valueOf(cursorFinalizerEnabled));
        }

        if (description != null) {
            builder.description(description);
        }

        if (heartbeatConnectTimeout != null) {
            builder.heartbeatConnectTimeout(Integer.valueOf(heartbeatConnectTimeout));
        }

        if (heartbeatFrequency != null) {
            builder.heartbeatFrequency(Integer.valueOf(heartbeatFrequency));
        }

        if (heartbeatSocketTimeout != null) {
            builder.heartbeatSocketTimeout(Integer.valueOf(heartbeatSocketTimeout));
        }

        if (maxConnectionIdleTime != null) {
            builder.maxConnectionIdleTime(Integer.valueOf(maxConnectionIdleTime));
        }

        if (maxConnectionLifeTime != null) {
            builder.maxConnectionLifeTime(Integer.valueOf(maxConnectionLifeTime));
        }

        if (maxWaitTime != null) {
            builder.maxWaitTime(Integer.valueOf(maxWaitTime));
        }

        if (minConnectionsPerHost != null) {
            builder.minConnectionsPerHost(Integer.valueOf(minConnectionsPerHost));
        }

        if (requiredReplicaSetName != null) {
            builder.requiredReplicaSetName(requiredReplicaSetName);
        }

        if (socketKeepAlive != null) {
            builder.socketKeepAlive(Boolean.valueOf(socketKeepAlive));
        }

        if (socketTimeout != null) {
            builder.socketTimeout(Integer.valueOf(socketTimeout));
        }
        return builder;
    }
}
