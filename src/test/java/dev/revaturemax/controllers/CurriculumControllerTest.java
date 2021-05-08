package dev.revaturemax.controllers;


import dev.revaturemax.models.Tech;
import dev.revaturemax.models.TechReview;
import dev.revaturemax.models.Topic;
import dev.revaturemax.services.CurriculumService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // this parameter is for web applications
public class CurriculumControllerTest {

    private MockMvc mockMvc;

    Tech techUno;
    Tech techDos;
    Tech techNull;

    List<Topic> listOfTopics;
    List<TechReview> listOfQuestions;

    List<Tech> listOfTech;


    @MockBean
    CurriculumService curriculumService;

    @InjectMocks
    CurriculumController curriculumController;

    @BeforeEach
    public void setup(){
//        MockitoAnnotations.openMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(curriculumController).build();
//
//        techNull = new Tech();
//
//        listOfTopics = new ArrayList<>();
//        listOfTopics.add(new Topic());
//        listOfTopics.add(2L);
//
//        listOfQuestions = new ArrayList<>();
//
//
//
//
//        techUno = new Tech("java", null,null,null);
//        techUno.setId(1);
//
//
//
//        techDos = new Tech("sql", "14 aspen ave","ssf", "ca","94080","650-987-4567","main@aspen.org","1234");
//        techDos.setId(2);
//        listOfTech = new ArrayList<>();
//        listOfTech.add(techUno);
//        listOfTech.add(techDos);


    }


}
