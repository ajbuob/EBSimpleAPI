package com.abuob.eb.service;

import com.abuob.eb.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicQueryServiceImpl implements TopicQueryService {

    private TopicRepository topicRepository;

    @Autowired
    public TopicQueryServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public TopicDTO findTopicInfoById(long id) {
        return topicRepository.findTopicInfoById(id);
    }

    @Override
    public List<TopicDTO> findAllTopicInfo() {
        return topicRepository.findAllTopicInfo();
    }

    @Override
    public List<Long> findTopicIdsByClass(String className) {
        return topicRepository.findTopicIdsByClass(className);
    }
}
