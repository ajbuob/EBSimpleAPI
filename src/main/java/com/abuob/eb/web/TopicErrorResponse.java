package com.abuob.eb.web;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class TopicErrorResponse {

    @JacksonXmlProperty(localName = "topic-id")
    private Long topicId;

    @JacksonXmlProperty(localName = "error")
    private String error;

    @JacksonXmlProperty(localName = "cause")
    private String cause;

    public TopicErrorResponse(Long topicId, String error, String cause) {
        this.topicId = topicId;
        this.error = error;
        this.cause = cause;
    }

    public Long getTopicId() {
        return topicId;
    }

    public String getError() {
        return error;
    }

    public String getCause() {
        return cause;
    }
}
