package dev.revaturemax.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import dev.revaturemax.dto.BatchDayRequest;
import dev.revaturemax.models.BatchDay;
import dev.revaturemax.models.Quiz;
import dev.revaturemax.repositories.BatchDayRepository;
import dev.revaturemax.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BatchDayService {


    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private BatchDayRepository batchDayRepository;

    @Transactional
    public ResponseEntity<String> getAllBatchDays(long batchId){
        List<BatchDay> days1 = batchDayRepository.findBatchDayByBatchId(batchId);
        try {
            return new ResponseEntity<String>(objectMapper.writeValueAsString(days1),HttpStatus.OK);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<String> setBatchDay(BatchDayRequest batchDayRequest){
        LocalDate date = batchDayRequest.getDate();
        long batchId = batchDayRequest.getBatchId();
        Optional<BatchDay> day = batchDayRepository.findBatchDayByBatchIdAndDate(batchId, date);
        if (day!=null && day.isPresent()) {
            day.get().setQuiz(batchDayRequest.getQuiz());
            day.get().setTopics(batchDayRequest.getTopics());
            day.get().setQc(batchDayRequest.getQc());
           batchDayRepository.save(day.get());
        } else {
            BatchDay newDay = new BatchDay(date, batchId, batchDayRequest.getTopics(), batchDayRequest.getQuiz(), batchDayRequest.getQc());
            batchDayRepository.save(newDay);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    // method for assigning a quiz to a batch day refactored from original quiz service

    @Transactional
    public ResponseEntity<String> setNewQuiz(long batchDayId, long quizId) {
        BatchDay batchDay = batchDayRepository.findById(batchDayId).orElse(null);
        Quiz quiz = quizRepository.getOne(quizId);
        batchDay.setQuiz(quiz);
        batchDayRepository.save(batchDay);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
