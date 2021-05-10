package dev.revaturemax.services;

import dev.revaturemax.controllers.CurriculumController;
import dev.revaturemax.models.Curriculum;
import dev.revaturemax.models.QC;
import dev.revaturemax.models.Tech;
import dev.revaturemax.models.Topic;
import dev.revaturemax.projections.TopicDTO;
import dev.revaturemax.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CurriculumServiceTest {

    @InjectMocks
    CurriculumService curriculumService;

    @MockBean
    private CurriculumRepository curriculumRepository;
    @MockBean
    private TopicRepository topicRepository;
    @MockBean
    private TechRepository techRepository;
    @MockBean
    private QuizRepository quizRepository;
    @MockBean
    private QCRepository qcRepository;

    private static final List<Tech> mockTechs = new ArrayList<>();

    private static final Curriculum mockCurriculum = new Curriculum(4, "java-azure", mockTechs);

    private static final Logger logger = LoggerFactory.getLogger(CurriculumServiceTest.class);


    @Test
    void testGetMultipleTopics_WithValidTechIDs(){

        // creating test variables to return when repo is called
        List<Topic> someTopics = new ArrayList<>();
        List<TopicDTO> mockDTOs = new ArrayList<>();

        // filling test variables with data
        Tech tech1 = new Tech(1L, "Java");
        someTopics.add(new Topic(1L, tech1, "String API"));
        someTopics.add(new Topic(2L, tech1, "Interfaces"));
        someTopics.add(new Topic(3L, tech1, "OOP Pillars"));
        tech1.setTopics(someTopics);

        // creating a set of topic IDS to pass into the service method
        Set<Long> topicIds = new HashSet<>();
        topicIds.add(1L);
        topicIds.add(2L);
        topicIds.add(3L);

        // creating mock DTO return list
        mockDTOs.add(new TopicDTO(1L, tech1.getName()));
        mockDTOs.add(new TopicDTO(2L, tech1.getName()));
        mockDTOs.add(new TopicDTO(3L, tech1.getName()));



        when(topicRepository.findAllById(topicIds)).thenReturn(mockDTOs);

        logger.info(curriculumService.getMultipleTopics(topicIds).toString());

        List<TopicDTO> actual = curriculumService.getMultipleTopics(topicIds);

        logger.info(mockDTOs.toString());

        assertEquals(mockDTOs, actual);

    }

    @Test
    public void getMultipleQCsWithQCIds() {
        List<QC> someQCs = new ArrayList<>();

        List<Tech> techForQC1 = new ArrayList<>();
        techForQC1.add(new Tech(1L, "Java"));
        List<Tech> techForQC2 = new ArrayList<>();
        techForQC2.add(new Tech(2L, "SQL"));


        someQCs.add(new QC(1L, "QC 1", techForQC1));
        someQCs.add(new QC(2L, "QC 2", techForQC2));

        Set<Long> qcIds = new HashSet<>();
        qcIds.add(1L);
        qcIds.add(2L);

        when(qcRepository.findAllById(qcIds)).thenReturn(someQCs);

        List<QC> actual = qcRepository.findAllById(qcIds);

        assertEquals(someQCs, actual);


    }

}
