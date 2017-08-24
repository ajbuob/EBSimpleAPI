package com.abuob.eb.web;

import com.abuob.eb.service.TopicDTO;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "publish-list")
public class UrlPublishListResponse {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "url-publish")
    private final List<UrlPublishResponse> urlPublishResponseList;

    public UrlPublishListResponse() {
        urlPublishResponseList = new ArrayList<>();
    }

    public UrlPublishListResponse(List<TopicDTO> topicDTOList) {
        urlPublishResponseList = new ArrayList<>();
        UrlPublishResponse urlPublishResponse;

        for (TopicDTO topicDTO : topicDTOList) {
            urlPublishResponse =
                    new UrlPublishResponse(topicDTO.getTopicId(), topicDTO.getUrlTitle(), topicDTO.getUrlClass());
            urlPublishResponseList.add(urlPublishResponse);
        }
    }

    public void addPublishResponse(UrlPublishResponse urlPublishResponse) {
        urlPublishResponseList.add(urlPublishResponse);
    }

    public List<UrlPublishResponse> getUrlPublishResponseList() {
        return urlPublishResponseList;
    }
}