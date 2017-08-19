package com.abuob.eb.web;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "publish-list")
public class UrlPublishListResponse {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<UrlPublishResponse> urlPublishResponseList;

    public UrlPublishListResponse() {
        urlPublishResponseList = new ArrayList<>();
    }

    public boolean addPublishResponse(UrlPublishResponse urlPublishResponse) {
        return urlPublishResponseList.add(urlPublishResponse);
    }

    public List<UrlPublishResponse> getUrlPublishResponseList() {
        return urlPublishResponseList;
    }
}