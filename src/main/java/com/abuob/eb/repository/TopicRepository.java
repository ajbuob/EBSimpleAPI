package com.abuob.eb.repository;

import com.abuob.eb.service.TopicDTO;

import java.util.List;

/**
 * TopicRepository is responsible for querying and accessing topic information from persisted data structure
 *
 **/
public interface TopicRepository {

    /**
     * Finds the topic with the specified id
     *
     * @param id the identifier of the topic
     * @return the topic information, null if id doesn't exist
     *
     **/
    TopicDTO findTopicInfoById(long id);


    /**
     * Finds all the stored topic information
     *
     * @return all topic information
     *
     **/
    List<TopicDTO> findAllTopicInfo();

    /**
     * Finds the topic with the specified id
     *
     * @param className the class of the topic
     * @return all ids associated with the class, empty if no topics are in the class
     *
     **/
    List<Long> findTopicIdsByClass(String className);
}