package com.abuob.eb.repository;

import com.abuob.eb.enums.ClassEnum;
import com.abuob.eb.service.TopicDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class InMemoryRepositoryTest {

    @Autowired
    private TopicRepository topicRepository;

    @Test
    public void findAllTopicInfo_Success() {
        List<TopicDTO> topicDTOList = topicRepository.findAllTopicInfo();
        assertThat(topicDTOList).isNotNull();
        assertThat(topicDTOList).isNotEmpty();
        assertThat(topicDTOList).hasSize(27);
    }

    @Test
    public void findTopicInfoById_Success() {
        final long id = 265;
        final String title = "Emil-Aarestrup";
        final String className = ClassEnum.BIOGRAPHY.getValue();

        TopicDTO topicDTO = topicRepository.findTopicInfoById(id);

        assertThat(topicDTO).isNotNull();
        assertThat(topicDTO.getTopicId()).isEqualTo(id);
        assertThat(topicDTO.getUrlTitle()).isEqualTo(title);
        assertThat(topicDTO.getUrlClass()).isEqualTo(className);
    }

    @Test
    public void findTopicInfoById_IdNotExist() {

        final long id = 4423422;
        TopicDTO topicDTO = topicRepository.findTopicInfoById(id);
        assertThat(topicDTO).isNull();
    }

    @Test
    public void findTopicIdsByClass_Success() {

        final String className = ClassEnum.EVENT.getValue();

        List<Long> topicIdList = topicRepository.findTopicIdsByClass(className);

        assertThat(topicIdList).isNotNull();
        assertThat(topicIdList).isNotEmpty();
        assertThat(topicIdList).hasSize(3);
        assertThat(topicIdList).containsExactly(2565L, 1427L, 1393L);
    }

    @Test
    public void findTopicIdsByClass_ClassNotExist() {

        final String className = "unknownClass";

        List<Long> topicIdList = topicRepository.findTopicIdsByClass(className);

        assertThat(topicIdList).isNotNull();
        assertThat(topicIdList).isEmpty();
    }


    @Test
    public void findTopicInfoByClass_Success() {

        final String className = ClassEnum.EVENT.getValue();

        List<TopicDTO> topicDTOList = topicRepository.findTopicByClassName(className);

        assertThat(topicDTOList).isNotNull();
        assertThat(topicDTOList).isNotEmpty();
        assertThat(topicDTOList).hasSize(3);

        TopicDTO topicDTO1 = new TopicDTO(2565L, "Acacian-Schism", className);
        TopicDTO topicDTO2 = new TopicDTO(1427L, "Treaty-of-Abo", className);
        TopicDTO topicDTO3 = new TopicDTO(1393L, "Ableman-v-Booth", className);

        assertThat(topicDTOList).containsExactlyInAnyOrder(topicDTO1, topicDTO2, topicDTO3);
    }

    @Test
    public void findTopicInfoByClass_ClassNotExist() {

        final String className = "unknownClass";

        List<TopicDTO> topicDTOList = topicRepository.findTopicByClassName(className);

        assertThat(topicDTOList).isNotNull();
        assertThat(topicDTOList).isEmpty();
    }
}
