package com.abuob.eb.web;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "url-publish")
public class TopicResponse {

    @JacksonXmlProperty(localName = "topicid")
    private Long topicId;

    @JacksonXmlProperty(localName = "urltitle")
    private String urlTitle;

    @JacksonXmlProperty(localName = "urlclass")
    private String urlClass;

    public TopicResponse(Long topicId, String urlTitle, String urlClass) {
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
