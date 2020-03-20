package com.octoevents.api.controller.exception;

public class BusinessException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public BusinessException() {
    }
    
    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public BusinessException(final String message) {
        super(message);
    }
    
    public BusinessException(final Throwable cause) {
        super(cause);
    }
    
    protected BusinessException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
