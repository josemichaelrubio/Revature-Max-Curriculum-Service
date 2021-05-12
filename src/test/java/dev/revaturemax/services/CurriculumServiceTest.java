package dev.revaturemax.services;

import dev.revaturemax.controllers.CurriculumController;
import dev.revaturemax.models.*;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    private static final List<Tech> mockTechs2 = new ArrayList<>();

    private static final Curriculum mockCurriculum = new Curriculum(4, "java-azure", mockTechs);

    private static final Logger logger = LoggerFactory.getLogger(CurriculumServiceTest.class);


    @Test
    void testGetAllTech() {
        mockTechs.add(new Tech(1L, "Java"));
        mockTechs.add(new Tech(2L, "OOP"));

        when(curriculumRepository.getOne(4L)).thenReturn(mockCurriculum);

        List<Tech> actual = curriculumService.getAllTech(4L);
        assertEquals(mockTechs, actual);
    }

    @Test
    void testGetTechs() {
        mockTechs.add(new Tech(1L, "Java"));
        mockTechs.add(new Tech(2L, "OOP"));;

        when(techRepository.findAll()).thenReturn(mockTechs);

        List<Tech> actual = curriculumService.getTechs();
        assertEquals(mockTechs, actual);
    }

    @Test
    void testCreateTech() {
        Tech tech = (new Tech(1L, "Java"));

        when(techRepository.save(tech)).thenReturn(tech);

        Tech actual = curriculumService.createTech(tech);
        assertEquals(tech, actual);
    }

    @Test
    void testDeleteTech() {
        curriculumService.deleteTech(1L);
        verify(techRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetTopics() {
        List<Topic> topics = new ArrayList<>();
        Tech java = new Tech(1L, "Java");
        topics.add(new Topic(1L, java, "Collections"));
        topics.add(new Topic(2L, java, "Testing"));

        when(topicRepository.findAllByTechId(1L)).thenReturn(topics);

        List<Topic> actual = curriculumService.getTopics(1L);
        assertEquals(topics, actual);
    }

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
    void testCreateTopic() {
        List<Topic> topics = new ArrayList<>();
        Tech java = new Tech(1L, "Java");
        topics.add(new Topic(1L, java, "Collections"));
        topics.add(new Topic(2L, java, "Testing"));
        Topic newTopic = new Topic(java, "Strings");

        when(techRepository.getOne(1L)).thenReturn(java);
        when(techRepository.save(java)).thenReturn(java);

        curriculumService.createTopic(1L, newTopic);
        verify(techRepository, times(1)).getOne(1L);
        verify(techRepository, times(1)).save(java);
    }

    @Test
    void testUpdateTopics() {
        List<Topic> topics = new ArrayList<>();
        Tech java = new Tech(1L, "Java");
        topics.add(new Topic(1L, java, "Collections"));

        List<Topic> newTopics = new ArrayList<>();
        newTopics.add(new Topic(2L, java, "Testing"));
        newTopics.add(new Topic(2L, java, "Strings"));

        when(techRepository.getOne(1L)).thenReturn(java);
        when(techRepository.save(java)).thenReturn(java);

        curriculumService.updateTopics(1L, newTopics);
        verify(techRepository, times(1)).getOne(1L);
        verify(techRepository, times(1)).save(java);
    }

    @Test
    void testRemoveTopicPresentInTech() {
        Tech java = new Tech(1L, "Java");
        Topic badTopic = new Topic(4L, java, "Strings");
        List<Topic> topics = new ArrayList<>();
        topics.add(badTopic);
        java.setTopics(topics);

        when(topicRepository.getOne(4L)).thenReturn(badTopic);
        when(techRepository.getOne(1L)).thenReturn(java);
        when(techRepository.save(java)).thenReturn(java);

        curriculumService.removeTopic(1L, 4L);
        verify(topicRepository, times(1)).getOne(4L);
        verify(techRepository, times(1)).getOne(1L);
        verify(techRepository, times(1)).save(java);
    }

    @Test
    void testGetOneQuiz() {
        mockTechs.add(new Tech(1L, "Java"));
        mockTechs.add(new Tech(2L, "OOP"));
        Quiz quiz1 = new Quiz(1L, "Quiz1", mockTechs);

        when(quizRepository.getOne(1L)).thenReturn(quiz1);
        Quiz actual = curriculumService.getOneQuiz(1L);
        assertEquals(quiz1, actual);
    }

    @Test
    void testGetMultipleQuizzes() {
        mockTechs.add(new Tech(1L, "Java"));
        mockTechs.add(new Tech(2L, "OOP"));
        Quiz quiz1 = new Quiz(1L, "Quiz1", mockTechs);
        mockTechs2.add(new Tech(3L, "HTML"));
        mockTechs2.add(new Tech(4L, "JavaScript"));
        Quiz quiz2 = new Quiz(2l, "Quiz2", mockTechs2);
        Set<Long> ids = new HashSet<>();
        ids.add(1L);
        ids.add(2L);
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(quiz1);
        quizzes.add(quiz2);

        when(quizRepository.findAllById(ids)).thenReturn(quizzes);

        List<Quiz> actual = curriculumService.getMultipleQuizzes(ids);
        assertEquals(quizzes, actual);
    }

    @Test
    void testUpdateQuizzes() {
        mockTechs.add(new Tech(1L, "Java"));
        mockTechs.add(new Tech(2L, "OOP"));
        Quiz quiz1 = new Quiz(1L, "Quiz1", mockTechs);

        when(quizRepository.getOne(1L)).thenReturn(quiz1);
        when(quizRepository.save(quiz1)).thenReturn(quiz1);

        ResponseEntity<HttpStatus> expected = new ResponseEntity<>(HttpStatus.OK);
        ResponseEntity<HttpStatus> actual = curriculumService.updateQuizzes(1L, mockTechs);
        verify(quizRepository, times(1)).getOne(1L);
        verify(quizRepository, times(1)).save(quiz1);
        assertEquals(expected, actual);
    }

    @Test
    void testRemoveTechFromQuiz() {
        mockTechs.add(new Tech(1L, "OOP"));
        Tech badTech = new Tech(2L, "Java");
        mockTechs.add(badTech);
        Quiz quiz1 = new Quiz(1L, "Quiz1", mockTechs);

        when(techRepository.getOne(2L)).thenReturn(badTech);
        when(quizRepository.getOne(1L)).thenReturn(quiz1);
        when(quizRepository.save(quiz1)).thenReturn(quiz1);

        ResponseEntity<HttpStatus> expected = new ResponseEntity<>(HttpStatus.OK);
        ResponseEntity<HttpStatus> actual = curriculumService.removeQuiz(2L, 1L);
        verify(techRepository, times(1)).getOne(2L);
        verify(quizRepository, times(1)).getOne(1L);
        verify(quizRepository, times(1)).save(quiz1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetMultipleQCsWithQCIds() {
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

    @Test
    void testGetAllQuizzes() {
        mockTechs.add(new Tech(1L, "Java"));
        mockTechs.add(new Tech(2L, "OOP"));
        Quiz quiz1 = new Quiz(1L, "Quiz1", mockTechs);
        mockTechs2.add(new Tech(3L, "HTML"));
        mockTechs2.add(new Tech(4L, "JavaScript"));
        Quiz quiz2 = new Quiz(2l, "Quiz2", mockTechs2);
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(quiz1);
        quizzes.add(quiz2);

        when(quizRepository.findAll()).thenReturn(quizzes);

        List<Quiz> actual = curriculumService.getAllQuizzes();
        assertEquals(quizzes, actual);
    }

    @Test
    void testGetAllQC() {
        mockTechs.add(new Tech(1L, "Java"));
        mockTechs.add(new Tech(2L, "OOP"));
        QC qc1 = new QC(1L, "QC1", mockTechs);
        mockTechs2.add(new Tech(3L, "HTML"));
        mockTechs2.add(new Tech(4L, "JavaScript"));
        QC qc2 = new QC(2l, "QC2", mockTechs2);
        List<QC> qcs = new ArrayList<>();
        qcs.add(qc1);
        qcs.add(qc2);

        when(qcRepository.findAll()).thenReturn(qcs);

        List<QC> actual = curriculumService.getAllQC();
        assertEquals(qcs, actual);
    }

}
