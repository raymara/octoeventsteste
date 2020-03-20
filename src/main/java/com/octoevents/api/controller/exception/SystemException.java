package com.octoevents.api.controller.exception;

public class SystemException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    public SystemException() {
    }
    
    public SystemException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public SystemException(final String message) {
        super(message);
    }
    
    public SystemException(final Throwable cause) {
        super(cause);
    }
    
    protected SystemException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}