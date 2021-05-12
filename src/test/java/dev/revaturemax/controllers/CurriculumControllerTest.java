package dev.revaturemax.controllers;


import dev.revaturemax.models.Curriculum;
import dev.revaturemax.models.Tech;
import dev.revaturemax.models.TechReview;
import dev.revaturemax.models.Topic;
import dev.revaturemax.services.CurriculumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(curriculumController).build();

//        techNull = new Tech();
//
//        listOfTopics = new ArrayList<>();
//        listOfTopics.add(new Topic());
//        listOfTopics.add(2L);
//
//        listOfQuestions = new ArrayList<>();




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

    @Test
    void testGetCurriculumTechs() throws Exception {

        List<Tech> techs = new ArrayList<>();
        techs.add(new Tech(1L, "Tech1"));
        techs.add(new Tech(2L, "Tech2"));

        when(curriculumService.getAllTech(1L)).thenReturn(techs);
        mockMvc.perform(get("/curriculum/1/techs"))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Tech1")))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Tech2")));
    }

    @Test
    void testGetTechs() throws Exception {

        List<Tech> techs = new ArrayList<>();
        techs.add(new Tech(1L, "Tech1"));
        techs.add(new Tech(2L, "Tech2"));

        when(curriculumService.getTechs()).thenReturn(techs);

        mockMvc.perform(get("/curriculum/techs"))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Tech1")))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Tech2")));
    }

    // The below test does not work as intended, and needs revision to be useful.
    @Test
    void testPostNewTopicTag() throws Exception {

        Tech newTech = new Tech(1L, "Tech1");
        when(curriculumService.createTech(newTech)).thenReturn(newTech);

        mockMvc.perform(post("/curriculum/techs"))
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    void testDeleteTopicTag() throws Exception {

        mockMvc.perform(delete("/curriculum/techs/1"))
                .andExpect(MockMvcResultMatchers.status().is(202));
    }

}
