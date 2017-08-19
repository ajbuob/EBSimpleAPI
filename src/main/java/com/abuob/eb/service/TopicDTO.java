package com.abuob.eb.service;

public class TopicDTO {

    private Long topicId;

    private String urlTitle;

    private String urlClass;

    public TopicDTO(Long topicId, String urlTitle, String urlClass) {
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
