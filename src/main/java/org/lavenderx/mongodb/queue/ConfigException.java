package org.lavenderx.mongodb.queue;

/**
 * @author baymax
 * @since 1.0
 */
public class ConfigException extends RuntimeException {

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(Throwable cause) {
        super(cause);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
