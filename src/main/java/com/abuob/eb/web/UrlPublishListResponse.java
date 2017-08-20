package com.abuob.eb.web;

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

    public void addPublishResponse(UrlPublishResponse urlPublishResponse) {
        urlPublishResponseList.add(urlPublishResponse);
    }

    public List<UrlPublishResponse> getUrlPublishResponseList() {
        return urlPublishResponseList;
    }
}