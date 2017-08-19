package com.abuob.eb.web.error;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Web error response when querying information about a topic by class
 */
@JacksonXmlRootElement(localName = "url-publish")
public class UrlPublishClassErrorResponse extends UrlPublishErrorResponse {

    @JacksonXmlProperty(localName = "urlclass")
    private String className;

    public UrlPublishClassErrorResponse(String className , String error, String cause) {
        super(error,cause);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
