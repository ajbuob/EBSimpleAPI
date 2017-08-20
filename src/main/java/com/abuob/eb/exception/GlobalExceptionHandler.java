package com.abuob.eb.exception;

import com.abuob.eb.web.error.UrlPublishClassErrorResponse;
import com.abuob.eb.web.error.UrlPublishTopicIdErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * AOP Advice to translate application exceptions to the appropriate XML response
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String MISSING_URL = "URL NOT FOUND";

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    UrlPublishTopicIdErrorResponse handleException(UnknownTopicIdException utie) {
        Long id = utie.getId();
        return new UrlPublishTopicIdErrorResponse(id, MISSING_URL, "topic " + id + " not in the database");
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    UrlPublishClassErrorResponse handleException(UnknownClassNameException ucne) {
        String className = ucne.getClassName();
        return new UrlPublishClassErrorResponse(className, MISSING_URL, "class: " + className + " is not valid");
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    String handleException(Exception e) {
        String message = "INTERNAL SERVER ERROR";
        if (e.getMessage() != null) {
            message = message + ":" + e.getMessage();
        }
        return message;
    }
}