package com.abuob.eb.controller;

import com.abuob.eb.enums.ClassEnum;
import com.abuob.eb.exception.UnknownClassNameException;
import com.abuob.eb.exception.UnknownTopicIdException;
import com.abuob.eb.service.TopicDTO;
import com.abuob.eb.service.TopicQueryService;
import com.abuob.eb.web.TopicListResponse;
import com.abuob.eb.web.UrlPublishListResponse;
import com.abuob.eb.web.UrlPublishResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoint controller for querying eb information
 */
@RestController
@RequestMapping("/eb")
public class EBController {

    private TopicQueryService topicQueryService;

    @Autowired
    public EBController(TopicQueryService topicQueryService) {
        this.topicQueryService = topicQueryService;
    }

    @RequestMapping(value = "topic/{topic_id}", method = RequestMethod.GET, produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<UrlPublishResponse> findTopicInfoFromId(@PathVariable("topic_id") long topicId) {

        TopicDTO topicDTO = topicQueryService.findTopicInfoById(topicId);
        if (topicDTO == null) {
            throw new UnknownTopicIdException(topicId + " not present in the application", topicId);
        }
        UrlPublishResponse urlPublishResponse =
                new UrlPublishResponse(topicDTO.getTopicId(), topicDTO.getUrlTitle(), topicDTO.getUrlClass());

        return new ResponseEntity<>(urlPublishResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "class/{class_name}", method = RequestMethod.GET, produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<TopicListResponse> findTopicInfoFromClass(@PathVariable("class_name") String className) {

        //Check if the submitted class name is valid
        if(ClassEnum.findByValue(className.trim().toLowerCase()) == null) {
            throw new UnknownClassNameException(className + " not allowed in this application",className);
        }

        List<Long> topicIdList = topicQueryService.findTopicIdsByClass(className);

        TopicListResponse topicListResponse = new TopicListResponse(topicIdList);

        return new ResponseEntity<>(topicListResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "all/topic", method = RequestMethod.GET, produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<UrlPublishListResponse> findAllTopicInfo() {

        UrlPublishListResponse urlPublishListResponse = new UrlPublishListResponse();

        List<TopicDTO> topicDTOList = topicQueryService.findAllTopicInfo();

        for(TopicDTO topicDTO : topicDTOList) {
            UrlPublishResponse urlPublishResponse =
                    new UrlPublishResponse(topicDTO.getTopicId(),topicDTO.getUrlTitle(),topicDTO.getUrlClass());
            urlPublishListResponse.addPublishResponse(urlPublishResponse);
        }
        return new ResponseEntity<>(urlPublishListResponse, HttpStatus.OK);
    }
}