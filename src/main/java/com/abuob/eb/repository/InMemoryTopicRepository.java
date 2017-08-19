package com.abuob.eb.repository;

import com.abuob.eb.service.TopicDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryTopicRepository implements TopicRepository {

    @Override
    public TopicDTO findTopicInfoById(long id) {
        return null;
    }

    @Override
    public List<TopicDTO> findAllTopicInfo() {
        return null;
    }

    @Override
    public List<Long> findTopicIdsByClass(String className) {
        return null;
    }
}
