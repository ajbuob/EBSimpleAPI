package com.abuob.eb.web;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Web response object to return information about a single topic
 */
@JacksonXmlRootElement(localName = "url-publish")
public class UrlPublishResponse {

    @JacksonXmlProperty(localName = "topicid")
    private Long topicId;

    @JacksonXmlProperty(localName = "urltitle")
    private String urlTitle;

    @JacksonXmlProperty(localName = "urlclass")
    private String urlClass;

    public UrlPublishResponse(Long topicId, String urlTitle, String urlClass) {
        this.topicId = topicId;
        this.urlTitle = urlTitle;
        this.urlClass = urlClass;
    }

    public Long getTopicId() {
        return topicId;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public String getUrlClass() {
        return urlClass;
    }

}
