package com.abuob.eb.web.error;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Web error response when querying information about a single topic
 */
@JacksonXmlRootElement(localName = "url-publish")
public class UrlPublishTopicIdErrorResponse extends UrlPublishErrorResponse {

    @JacksonXmlProperty(localName = "topicid")
    private Long topicId;

    public UrlPublishTopicIdErrorResponse(Long topicId, String error, String cause) {
        super(error,cause);
        this.topicId = topicId;
    }

    public Long getTopicId() {
        return topicId;
    }
}
