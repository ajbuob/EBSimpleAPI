package com.abuob.eb.service;

import java.util.List;

/**
 * TopicQueryService is responsible for looking up topic information in the application
 */
public interface TopicQueryService {

    /**
     * Finds the topic with the specified id
     *
     * @param id the identifier of the topic
     * @return the topic information, null if id doesn't exist
     **/
    TopicDTO findTopicInfoById(long id);


    /**
     * Finds all the stored topic information
     *
     * @return all topic information
     **/
    List<TopicDTO> findAllTopicInfo();

    /**
     * Finds all topic ids with the specified class
     *
     * @param className the class of the topic
     * @return all ids associated with the class, empty if no topics are in the class
     **/
    List<Long> findTopicIdsByClass(String className);

    /**
     * Finds the topics with the specified class
     *
     * @param className the class of the topic
     * @return all topics associated with the class, empty if no topics are in the class
     **/
    List<TopicDTO> findTopicByClassName(String className);
}