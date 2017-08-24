package com.abuob.eb.service;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicDTO topicDTO = (TopicDTO) o;
        return Objects.equals(topicId, topicDTO.topicId) &&
                Objects.equals(urlTitle, topicDTO.urlTitle) &&
                Objects.equals(urlClass, topicDTO.urlClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicId, urlTitle, urlClass);
    }
}