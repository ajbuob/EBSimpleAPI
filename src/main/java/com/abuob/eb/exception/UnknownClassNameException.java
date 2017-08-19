package com.abuob.eb.exception;

/**
 * Exception indicating the provided class name is not valid in the application
 */
public class UnknownClassNameException extends RuntimeException {

    private final String className;

    public UnknownClassNameException(String message, String className) {
        super(message);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
