package com.abuob.eb.exception;

/**
 * Exception indicating the provided topic id is not valid in the application
 */
public class UnknownTopicIdException extends RuntimeException {

    private final Long id;

    public UnknownTopicIdException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
