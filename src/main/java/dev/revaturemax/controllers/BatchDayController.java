package dev.revaturemax.controllers;

import dev.revaturemax.dto.BatchDayRequest;
import dev.revaturemax.services.BatchDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch-days")
public class BatchDayController {

    @Autowired
    private BatchDayService batchDayService;

    // get and put mapping for batch days -- refactored from curriculum mapping in original batch controller
    @GetMapping(produces = "application/json")
    public ResponseEntity<String> getBatchDays(@RequestParam("batch") long batchId){
        return batchDayService.getAllBatchDays(batchId);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<String> setBatchDay(@RequestBody BatchDayRequest batchDayRequest){
        return batchDayService.setBatchDay(batchDayRequest);
    }

    // quiz post method for batch day refactored from original batch controller
    @PostMapping(value = "/{day-id}/quizzes/{quiz-id}", consumes = "application/json")
    public ResponseEntity<String> postQuiz(@PathVariable("day-id") long batchDayId,
                                           @PathVariable("quiz-id") long quizId)
    {

        return batchDayService.setNewQuiz(batchDayId, quizId);
    }


}
