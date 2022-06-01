package com.stupid.common.api.core.exception;

/**
 * 默认业务异常类
 */
public class DefaultServiceException extends RuntimeException {

    public DefaultServiceException() {
    }

    public DefaultServiceException(String message) {
        super(message);
    }

    public DefaultServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultServiceException(Throwable cause) {
        super(cause);
    }

    public DefaultServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
