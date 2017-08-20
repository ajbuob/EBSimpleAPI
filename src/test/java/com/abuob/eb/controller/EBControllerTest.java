package com.abuob.eb.controller;

import com.abuob.eb.enums.ClassEnum;
import com.abuob.eb.service.TopicDTO;
import com.abuob.eb.service.TopicQueryService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EBControllerTest {

    private static final String XPATH_URL_PUBLISH_TOPIC_ID = "/url-publish/topicid";
    private static final String XPATH_URL_PUBLISH_TITLE = "/url-publish/urltitle";
    private static final String XPATH_URL_PUBLISH_CLASS = "/url-publish/urlclass";

    private static final String XPATH_URL_PUBLISH_ERROR = "/url-publish/error";
    private static final String XPATH_URL_PUBLISH_CAUSE = "/url-publish/cause";

    private static final String XPATH_URL_TOPIC_LIST_TOPIC_ID = "topic-list/topicid";

    private static final String MISSING_URL = "URL NOT FOUND";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicQueryService topicQueryServiceMock;

    @Test
    public void findTopicInfoFromId_TopicNotFound() throws Exception {

        long expectedTopicId = 345257;
        when(topicQueryServiceMock.findTopicInfoById(expectedTopicId)).thenReturn(null);

        mockMvc.perform(get("/eb/topic/" + expectedTopicId).accept(MediaType.TEXT_XML_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(xpath(XPATH_URL_PUBLISH_TOPIC_ID).number((double) expectedTopicId))
                .andExpect(xpath(XPATH_URL_PUBLISH_ERROR).string(MISSING_URL))
                .andExpect(xpath(XPATH_URL_PUBLISH_CAUSE).string("topic " + expectedTopicId + " not in the database"));
        verify(topicQueryServiceMock).findTopicInfoById(expectedTopicId);
    }

    @Test
    public void findTopicInfoFromId_Success() throws Exception {

        long expectedTopicId = 563452;
        String expectedTitle = "Watergate";
        String expectedClass = "event";
        TopicDTO topicDTO = new TopicDTO(expectedTopicId, expectedTitle, expectedClass);

        when(topicQueryServiceMock.findTopicInfoById(expectedTopicId)).thenReturn(topicDTO);

        mockMvc.perform(get("/eb/topic/" + expectedTopicId).accept(MediaType.TEXT_XML_VALUE))
                .andExpect(status().isOk())
                .andExpect(xpath(XPATH_URL_PUBLISH_TOPIC_ID).number((double) expectedTopicId))
                .andExpect(xpath(XPATH_URL_PUBLISH_TITLE).string(expectedTitle))
                .andExpect(xpath(XPATH_URL_PUBLISH_CLASS).string(expectedClass));
        verify(topicQueryServiceMock).findTopicInfoById(expectedTopicId);
    }

    @Test
    public void findTopicInfoFromClass_TopicNotFound() throws Exception {

        String expectedClassName = "unknownClassName";

        mockMvc.perform(get("/eb/class/" + expectedClassName).accept(MediaType.TEXT_XML_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(xpath(XPATH_URL_PUBLISH_CLASS).string(expectedClassName))
                .andExpect(xpath(XPATH_URL_PUBLISH_ERROR).string(MISSING_URL))
                .andExpect(xpath(XPATH_URL_PUBLISH_CAUSE).string("class: " + expectedClassName + " is not valid"));
        verify(topicQueryServiceMock, times(0)).findTopicIdsByClass(expectedClassName);
    }

    @Test
    public void findTopicInfoFromClass_Success() throws Exception {

        String expectedClassName = ClassEnum.SCIENCE.getValue();

        long id1 = 66293L;
        long id2 = 125L;
        long id3 = 9758L;
        long id4 = 44228L;

        List<Long> expectedTopicIdList = Lists.newArrayList(id1, id2, id3, id4);
        when(topicQueryServiceMock.findTopicIdsByClass(expectedClassName)).thenReturn(expectedTopicIdList);

        mockMvc.perform(get("/eb/class/" + expectedClassName).accept(MediaType.TEXT_XML_VALUE))
                .andExpect(status().isOk())
                .andExpect(xpath(XPATH_URL_TOPIC_LIST_TOPIC_ID).nodeCount(4))
                .andExpect(xpath(XPATH_URL_TOPIC_LIST_TOPIC_ID + "[1]").number((double)id1))
                .andExpect(xpath(XPATH_URL_TOPIC_LIST_TOPIC_ID + "[2]").number((double)id2))
                .andExpect(xpath(XPATH_URL_TOPIC_LIST_TOPIC_ID + "[3]").number((double)id3))
                .andExpect(xpath(XPATH_URL_TOPIC_LIST_TOPIC_ID + "[4]").number((double)id4));

        verify(topicQueryServiceMock, times(1)).findTopicIdsByClass(expectedClassName);
    }

    @Test
    public void findAllTopicInfo_Success() throws Exception {

    }
}
