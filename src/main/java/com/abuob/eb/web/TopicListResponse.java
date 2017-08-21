package com.abuob.eb.web;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "topic-list")
public class TopicListResponse {

    @JacksonXmlProperty(localName = "topicid")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<Long> topicIdList;

    public TopicListResponse() {
        this.topicIdList= new ArrayList<>();
    }

    public TopicListResponse(List<Long> topicIdList) {
        this.topicIdList = topicIdList;
    }

    public void addTopicId(long topicId) {
        topicIdList.add(topicId);
    }

    public List<Long> getTopicIdList() {
        return topicIdList;
    }
}