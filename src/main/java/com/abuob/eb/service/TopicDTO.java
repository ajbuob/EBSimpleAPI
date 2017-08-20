package com.abuob.eb.service;

/**
 * Data transfer object used by TopicQueryService
 */
public class TopicDTO {

    private final Long topicId;

    private final String urlTitle;

    private final String urlClass;

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
