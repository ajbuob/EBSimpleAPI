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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EBControllerTest {

    private static final String XPATH_URL_PUBLISH = "/url-publish";
    private static final String XPATH_TOPIC_ID = "/topicid";
    private static final String XPATH_TITLE = "/urltitle";
    private static final String XPATH_CLASS = "/urlclass";

    private static final String XPATH_URL_PUBLISH_TOPIC_ID = XPATH_URL_PUBLISH + XPATH_TOPIC_ID;
    private static final String XPATH_URL_PUBLISH_TITLE = XPATH_URL_PUBLISH + XPATH_TITLE;
    private static final String XPATH_URL_PUBLISH_CLASS = XPATH_URL_PUBLISH + XPATH_CLASS;

    private static final String XPATH_URL_PUBLISH_ERROR = XPATH_URL_PUBLISH + "/error";
    private static final String XPATH_URL_PUBLISH_CAUSE = XPATH_URL_PUBLISH + "/cause";

    private static final String XPATH_URL_TOPIC_LIST_TOPIC_ID = "/topic-list/topicid";
    private static final String XPATH_URL_PUBLISH_LIST_URL_PUBLISH = "/publish-list/url-publish";


    private static final String MISSING_URL = "URL NOT FOUND";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicQueryService topicQueryServiceMock;

    @Test
    public void findTopicInfoFromId_TopicNotFound() throws Exception {

        long expectedTopicId = 345257;
        when(topicQueryServiceMock.findTopicInfoById(expectedTopicId)).thenReturn(null);

        mockMvc.perform(get("/eb/topic/" + expectedTopicId).accept(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML_VALUE))
                .andExpect(xpath(XPATH_URL_PUBLISH_TOPIC_ID).number((double) expectedTopicId))
                .andExpect(xpath(XPATH_URL_PUBLISH_ERROR).string(MISSING_URL))
                .andExpect(xpath(XPATH_URL_PUBLISH_CAUSE).string("topic " + expectedTopicId + " not in the database"));

        verify(topicQueryServiceMock, times(1)).findTopicInfoById(expectedTopicId);
    }

    @Test
    public void findTopicInfoFromId_Success() throws Exception {

        long expectedTopicId = 563452;
        String expectedTitle = "First Moon Landing";
        String expectedClass = "event";
        TopicDTO topicDTO = new TopicDTO(expectedTopicId, expectedTitle, expectedClass);

        when(topicQueryServiceMock.findTopicInfoById(expectedTopicId)).thenReturn(topicDTO);

        mockMvc.perform(get("/eb/topic/" + expectedTopicId).accept(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML_VALUE))
                .andExpect(xpath(XPATH_URL_PUBLISH_TOPIC_ID).number((double) expectedTopicId))
                .andExpect(xpath(XPATH_URL_PUBLISH_TITLE).string(expectedTitle))
                .andExpect(xpath(XPATH_URL_PUBLISH_CLASS).string(expectedClass));

        verify(topicQueryServiceMock, times(1)).findTopicInfoById(expectedTopicId);
    }

    @Test
    public void findTopicInfoFromClass_TopicNotFound() throws Exception {

        String expectedClassName = "unknownClassName";

        mockMvc.perform(get("/eb/class/" + expectedClassName).accept(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML_VALUE))
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

        mockMvc.perform(get("/eb/class/" + expectedClassName).accept(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML_VALUE))
                .andExpect(xpath(XPATH_URL_TOPIC_LIST_TOPIC_ID).nodeCount(4))
                .andExpect(xpath(XPATH_URL_TOPIC_LIST_TOPIC_ID + "[1]").number((double) id1))
                .andExpect(xpath(XPATH_URL_TOPIC_LIST_TOPIC_ID + "[2]").number((double) id2))
                .andExpect(xpath(XPATH_URL_TOPIC_LIST_TOPIC_ID + "[3]").number((double) id3))
                .andExpect(xpath(XPATH_URL_TOPIC_LIST_TOPIC_ID + "[4]").number((double) id4));

        verify(topicQueryServiceMock, times(1)).findTopicIdsByClass(expectedClassName);
    }

    @Test
    public void findAllTopicInfo_Success() throws Exception {

        final long id1 = 32453L;
        final long id2 = 44L;
        final long id3 = 5425L;

        final String title1 = "title2";
        final String title2 = "title3";
        final String title3 = "title1";

        final String className1 = "className3";
        final String className2 = "className2";
        final String className3 = "className1";

        TopicDTO topicDTO1 = new TopicDTO(id1, title1, className1);
        TopicDTO topicDTO2 = new TopicDTO(id2, title2, className2);
        TopicDTO topicDTO3 = new TopicDTO(id3, title3, className3);

        List<TopicDTO> topicDTOList = Lists.newArrayList(topicDTO1, topicDTO2, topicDTO3);
        when(topicQueryServiceMock.findAllTopicInfo()).thenReturn(topicDTOList);

        mockMvc.perform(get("/eb/all/topic").accept(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML_VALUE))
                .andExpect(xpath(XPATH_URL_PUBLISH_LIST_URL_PUBLISH).nodeCount(3))

                .andExpect(xpath(XPATH_URL_PUBLISH_LIST_URL_PUBLISH + "[1]" + XPATH_TOPIC_ID).number((double) id1))
                .andExpect(xpath(XPATH_URL_PUBLISH_LIST_URL_PUBLISH + "[1]" + XPATH_TITLE).string(title1))
                .andExpect(xpath(XPATH_URL_PUBLISH_LIST_URL_PUBLISH + "[1]" + XPATH_CLASS).string(className1))

                .andExpect(xpath(XPATH_URL_PUBLISH_LIST_URL_PUBLISH + "[2]" + XPATH_TOPIC_ID).number((double) id2))
                .andExpect(xpath(XPATH_URL_PUBLISH_LIST_URL_PUBLISH + "[2]" + XPATH_TITLE).string(title2))
                .andExpect(xpath(XPATH_URL_PUBLISH_LIST_URL_PUBLISH + "[2]" + XPATH_CLASS).string(className2))

                .andExpect(xpath(XPATH_URL_PUBLISH_LIST_URL_PUBLISH + "[3]" + XPATH_TOPIC_ID).number((double) id3))
                .andExpect(xpath(XPATH_URL_PUBLISH_LIST_URL_PUBLISH + "[3]" + XPATH_TITLE).string(title3))
                .andExpect(xpath(XPATH_URL_PUBLISH_LIST_URL_PUBLISH + "[3]" + XPATH_CLASS).string(className3));

        verify(topicQueryServiceMock, times(1)).findAllTopicInfo();
    }

    @Test
    public void unknownPath_Expect404Status() throws Exception {

        String unknownPth = "/some/unknown/path";

        mockMvc.perform(get("/eb" + unknownPth).accept(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isNotFound());
    }
}