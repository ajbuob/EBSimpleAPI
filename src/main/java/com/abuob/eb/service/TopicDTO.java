package com.abuob.eb.service;

/**
 * Data transfer object used by TopicQueryService
 */
public class TopicDTO {

    private Long topicId;

    private String urlTitle;

    private String urlClass;

    public TopicDTO() {
    }

    public TopicDTO(Long topicId, String urlTitle, String urlClass) {
        this.topicId = topicId;
        this.urlTitle = urlTitle;
        this.urlClass = urlClass;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    public String getUrlClass() {
        return urlClass;
    }

    public void setUrlClass(String urlClass) {
        this.urlClass = urlClass;
    }
}