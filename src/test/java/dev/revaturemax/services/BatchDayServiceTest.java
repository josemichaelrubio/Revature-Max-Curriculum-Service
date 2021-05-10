package dev.revaturemax.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import dev.revaturemax.dto.BatchDayRequest;
import dev.revaturemax.models.*;
import dev.revaturemax.repositories.BatchDayRepository;
import dev.revaturemax.repositories.QuizRepository;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BatchDayServiceTest {

    @InjectMocks
    private BatchDayService batchDayService;





    @MockBean
    private BatchDayRepository batchDayRepository;
    @MockBean
    private QuizRepository quizRepository;
    @MockBean
    private ObjectMapper objectMapper;

    @Test
    void getAllBatchDays_JsonProcessingException() throws JsonProcessingException {
        List<BatchDay> bds = new ArrayList<>();
        BatchDay b = new BatchDay(3l,null,null,null,null);
        bds.add(b);
        when(batchDayRepository.findBatchDayByBatchId(3l)).thenReturn(bds);
        when(objectMapper.writeValueAsString(bds)).thenThrow(JsonProcessingException.class);
        ResponseEntity<List<BatchDay>> re = batchDayService.getAllBatchDays(3l);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,re.getStatusCode());
    }

    @Test
    void getAllBatchDays() throws JsonProcessingException {

        LocalDate date = LocalDate.of(2020, 12, 3);
        ObjectMapper objectMapper = new ObjectMapper();
        List<BatchDay> days = new ArrayList<BatchDay>();
        List<Topic> top = new ArrayList<Topic>();
        Topic t = new Topic(1l, null, "BUDFUDLY");
        Topic t1 = new Topic(1l, null, "FUDBIDDLY");
        Tech tc = new Tech();
        tc.setId(1l);
        List<Tech> techs = new ArrayList<Tech>();
        techs.add(tc);
        Quiz q = new Quiz(2l, "first quiz", techs);
        QC qc = new QC(1l, "first Qc", techs);

        top.add(t);
        BatchDay d = new BatchDay(3l, date, top, q, qc);
        days.add(d);
        top.add(t1);
        BatchDay d1 = new BatchDay(3l, date, top, q, qc);
        days.add(d1);

        when(batchDayRepository.findBatchDayByBatchId(3l)).thenReturn(days);
        ResponseEntity<String> expected = new ResponseEntity<String>(objectMapper.writeValueAsString(days),
                HttpStatus.OK);

        ResponseEntity<List<BatchDay>> actual = batchDayService.getAllBatchDays(3l);

        boolean check = actual.getStatusCode().equals(expected.getStatusCode());
        assertAll(
                () -> assertEquals(expected.getStatusCode(), actual.getStatusCode()),
                () -> assertEquals(days, actual.getBody())
        );
    }

    @Test
    void setBatchDay() {
        BatchDayRequest bdr = new BatchDayRequest();
        bdr.setBatchId(1);
        when(batchDayRepository.findBatchDayByBatchIdAndDate(1,null)).thenReturn(null);
        when(batchDayRepository.save(new BatchDay())).thenReturn(null);
        assertEquals(HttpStatus.OK,batchDayService.setBatchDay(bdr).getStatusCode());
    }

    @Test
    void setNewQuiz() {
        BatchDay bd = new BatchDay();
        bd.setId(9l);
        Quiz quiz = new Quiz();
        quiz.setId(3l);

        when(batchDayRepository.findById(9l)).thenReturn(Optional.of(bd));
        when(quizRepository.getOne(3l)).thenReturn(quiz);
        when(batchDayRepository.save(bd)).thenReturn(null);

        ResponseEntity<String> actual = batchDayService.setNewQuiz(9l,3l);
        assertEquals(HttpStatus.ACCEPTED,actual.getStatusCode());
    }




}

