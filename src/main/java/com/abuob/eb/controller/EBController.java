package com.abuob.eb.controller;

import com.abuob.eb.enums.ClassEnum;
import com.abuob.eb.exception.UnknownClassNameException;
import com.abuob.eb.exception.UnknownTopicIdException;
import com.abuob.eb.service.TopicDTO;
import com.abuob.eb.service.TopicQueryService;
import com.abuob.eb.web.TopicListResponse;
import com.abuob.eb.web.UrlPublishListResponse;
import com.abuob.eb.web.UrlPublishResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoint controller for querying eb information
 */
@RestController
@RequestMapping("/eb")
public class EBController {

    private static final Logger logger = LoggerFactory.getLogger(EBController.class);

    private final TopicQueryService topicQueryService;

    @Autowired
    public EBController(TopicQueryService topicQueryService) {
        this.topicQueryService = topicQueryService;
    }


    @GetMapping(value = "topic/{topic_id}", produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<UrlPublishResponse> findTopicInfoFromId(@PathVariable("topic_id") long topicId) {

        logger.debug("Trying to find topic info for id: {}", topicId);
        TopicDTO topicDTO = topicQueryService.findTopicInfoById(topicId);
        if (topicDTO == null) {
            logger.error("topic id: {} not found in the application", topicId);
            throw new UnknownTopicIdException(topicId + " not present in the application. Unable to continue", topicId);
        }
        UrlPublishResponse urlPublishResponse =
                new UrlPublishResponse(topicDTO.getTopicId(), topicDTO.getUrlTitle(), topicDTO.getUrlClass());

        logger.debug("Found topic info for id: {}", topicId);
        return new ResponseEntity<>(urlPublishResponse, HttpStatus.OK);
    }

    @GetMapping(value = "class/{class_name}", produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<TopicListResponse> findTopicInfoFromClass(@PathVariable("class_name") String className) {

        logger.debug("Trying to find topic info for class: {}", className);

        //Check if the submitted class name is valid
        if (ClassEnum.findByValue(className.trim().toLowerCase()) == null) {
            logger.error("class: {} not valid in this application. Unable to continue");
            throw new UnknownClassNameException(className + " not allowed in this application", className);
        }

        List<Long> topicIdList = topicQueryService.findTopicIdsByClass(className);

        TopicListResponse topicListResponse = new TopicListResponse(topicIdList);

        logger.debug("Found topic info for class: {}", className);
        return new ResponseEntity<>(topicListResponse, HttpStatus.OK);
    }

    @GetMapping(value = "all/topic", produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<UrlPublishListResponse> findAllTopicInfo() {

        UrlPublishListResponse urlPublishListResponse = new UrlPublishListResponse();

        logger.debug("Trying to find all topic info");
        List<TopicDTO> topicDTOList = topicQueryService.findAllTopicInfo();

        for (TopicDTO topicDTO : topicDTOList) {
            UrlPublishResponse urlPublishResponse =
                    new UrlPublishResponse(topicDTO.getTopicId(), topicDTO.getUrlTitle(), topicDTO.getUrlClass());
            urlPublishListResponse.addPublishResponse(urlPublishResponse);
        }
        logger.debug("Found all topic info");
        return new ResponseEntity<>(urlPublishListResponse, HttpStatus.OK);
    }
}