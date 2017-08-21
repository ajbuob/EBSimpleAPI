package com.abuob.eb.repository;

import com.abuob.eb.service.TopicDTO;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TopicRepository implementation which loads data into memory
 * from supplied XML file path. The file path is specified in an external property file.
 **/
@Repository
@Profile("dev")
@ConfigurationProperties(prefix = "eb")
public class InMemoryTopicRepository implements TopicRepository {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryTopicRepository.class);

    private static final String XML_URL_PUBLISH = "url-publish";
    private static final String XML_TOPIC_ID = "topicid";
    private static final String XML_URL_TITLE = "urltitle";
    private static final String XML_URL_CLASS = "urlclass";

    private final List<TopicDTO> topicDTOList;

    private String xmlFilePath;
    public InMemoryTopicRepository() {
        topicDTOList = Lists.newArrayList();
    }

    @PostConstruct
    public void init() throws FileNotFoundException, XMLStreamException {
        if (xmlFilePath == null) {
            logger.error("XML File Path missing");
            throw new IllegalArgumentException("Failed to initialize XML File Path");
        }
        parseXml(xmlFilePath);
    }

    @Override
    public TopicDTO findTopicInfoById(long id) {
        logger.debug("Searching repository for topics with id: {}", id);
        return topicDTOList.stream()
                .filter(topic -> Objects.equals(topic.getTopicId(), id)).findFirst().orElse(null);
    }

    @Override
    public List<TopicDTO> findAllTopicInfo() {
        logger.debug("Searching repository for all topics");
        return topicDTOList;
    }

    @Override
    public List<Long> findTopicIdsByClass(String className) {
        logger.debug("Searching repository for topics with class: {}", className);
        return topicDTOList.stream()
                .filter(topic -> Objects.equals(topic.getUrlClass(), className))
                .map(TopicDTO::getTopicId)
                .collect(Collectors.toList());
    }

    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    /**
     * Private utility method to load XML file contents into memory
     *
     * @param xmlFilePath The path to the xml file
     **/
    private void parseXml(String xmlFilePath) throws FileNotFoundException, XMLStreamException {
        TopicDTO topicDTO = null;
        String elementContent = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(xmlFilePath));

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    if (XML_URL_PUBLISH.equals(reader.getLocalName())) {
                        topicDTO = new TopicDTO();
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    elementContent = reader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    switch (reader.getLocalName()) {
                        case XML_URL_PUBLISH:
                            topicDTOList.add(topicDTO);
                            break;
                        case XML_TOPIC_ID:
                            topicDTO.setTopicId(Long.valueOf(elementContent));
                            break;
                        case XML_URL_TITLE:
                            topicDTO.setUrlTitle(elementContent);
                            break;
                        case XML_URL_CLASS:
                            topicDTO.setUrlClass(elementContent);
                            break;
                    }
                    break;
            }

        }
    }
}