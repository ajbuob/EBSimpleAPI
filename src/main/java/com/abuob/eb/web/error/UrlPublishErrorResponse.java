package com.abuob.eb.web.error;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Abstract web error response to be extended by all errors
 */
abstract class UrlPublishErrorResponse {

    @JacksonXmlProperty(localName = "error")
    private final String error;

    @JacksonXmlProperty(localName = "cause")
    private final String cause;

    UrlPublishErrorResponse(String error, String cause) {
        this.error = error;
        this.cause = cause;
    }

    public String getError() {
        return error;
    }

    public String getCause() {
        return cause;
    }
}