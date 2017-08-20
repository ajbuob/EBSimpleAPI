package com.abuob.eb.repository;

import com.abuob.eb.service.TopicDTO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Profile("dev")
public class InMemoryTopicRepository implements TopicRepository {

    private final List<TopicDTO> topicDTOList;

    @Value("${eb.testXml.path}")
    private String xmlTestFilePath;

    public InMemoryTopicRepository() {
        topicDTOList = Lists.newArrayList();
    }

    public InMemoryTopicRepository(List<TopicDTO> topicDTOList) {
        this.topicDTOList = topicDTOList;
    }

    @Override
    public TopicDTO findTopicInfoById(long id) {
        return topicDTOList.stream()
                .filter(topic -> Objects.equals(topic.getTopicId(), id)).findFirst().orElse(null);
    }

    @Override
    public List<TopicDTO> findAllTopicInfo() {
        return topicDTOList;
    }

    @Override
    public List<Long> findTopicIdsByClass(String className) {
        return topicDTOList.stream()
                .filter(topic -> Objects.equals(topic.getUrlClass(), className))
                .map(TopicDTO::getTopicId)
                .collect(Collectors.toList());
    }
}
