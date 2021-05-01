package dev.revaturemax.controllers;

import dev.revaturemax.models.Quiz;
import dev.revaturemax.models.Tech;
import dev.revaturemax.models.Topic;
import dev.revaturemax.services.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    // This is where CRUD methods for curriculum goes (mostly admin functions)



    // CRUD methods for technologies
    @GetMapping("/{curriculum-id}/techs")
    public ResponseEntity<List<Tech>> getCurriculumTechs(@PathVariable long id){
        return ResponseEntity.ok().body(curriculumService.getAllTech(id));
    }

    @GetMapping("/techs")
    public ResponseEntity<List<Tech>> getTechs(){
        return ResponseEntity.ok().body(curriculumService.getTechs());
    }

    @PostMapping("/techs")
    public ResponseEntity<Tech> postNewTopicTag(@RequestBody Tech tech){
        Tech newTech = curriculumService.createTech(tech);
        return new ResponseEntity<>(newTech, HttpStatus.CREATED);
    }

    @DeleteMapping("/techs/{id}")
    public ResponseEntity<HttpStatus> deleteTopicTag(@PathVariable long id){
        curriculumService.deleteTech(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    // Get mapping -- queries with a list of topic id's
    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getMultipleTopics(@RequestParam Set<Long> topicIds){
        return ResponseEntity.ok().body(curriculumService.getMultipleTopics(topicIds));
    }

    /*
     * mapping for /techs/{tech-id}/topics
     * Designed for CRUD individual topics
     */

    @GetMapping("/techs/{id}/topics")
    public ResponseEntity<List<Topic>> getAllTopics(@PathVariable long techId){
        return ResponseEntity.ok().body(curriculumService.getTopics(techId));
    }

    @PostMapping("/techs/{id}/topics")
    public ResponseEntity<HttpStatus> postNewTopic(@PathVariable long techId,
                                                   @RequestBody Topic topic)
    {
        curriculumService.createTopic(techId, topic);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    /*
     * Put mapping for /techs/{tech-id}/topics
     * Designed for updating the list of topics associated with a tech
     * @param new List of topics from front-end
     */

    @PutMapping("/techs/{tech-id}/topics")
    public ResponseEntity<HttpStatus> updateTopic(@PathVariable long id,
                                                  @RequestBody List<Topic> topics)
    {
        curriculumService.updateTopics(id, topics);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/techs/{tech-id}/topics/{topic-id}")
    public ResponseEntity<HttpStatus> deleteTopic(@PathVariable(name = "tech-id") long techId,
                                                  @PathVariable(name = "topic-id") long topicId)
    {

        curriculumService.removeTopic(techId, topicId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    // method for getting quizzes with a list of quiz id's in the query param (i.e. /curriculum/quizzes?id=1,2,3,4,5
    @GetMapping(value = "/quizzes", produces = "application/json")
    public ResponseEntity<List<Quiz>> getMultipleQuizzes(@RequestParam Set<Long> ids){
        return ResponseEntity.ok().body(curriculumService.getMultipleQuizzes(ids));
    }


    // method for pulling quiz by an id
    @GetMapping("/quizzes/{quiz-id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable("quiz-id") long quizId){
        return ResponseEntity.ok().body(curriculumService.getOneQuiz(quizId));
    }


    /*
     * Put and Delete mapping for /quizzes/{quiz-id}/techs
     * Designed for updating the list of techs associated with a quiz
     */

    @PutMapping(value = "/quizzes/{quiz-id}/techs", consumes = "application/json")
    public ResponseEntity<HttpStatus> putQuiz(@PathVariable("quiz-id") long quizId,
                                          @RequestBody List<Tech> techs)
    {
        return curriculumService.updateQuizzes(quizId, techs);
    }

    @DeleteMapping(value = "/quizzes/{quiz-id}/techs/{tech-id}")
    public ResponseEntity<HttpStatus> removeQuiz(@PathVariable("tech-id") long techId,
                                             @PathVariable("quiz-id") long quizId)
    {
        return curriculumService.removeQuiz(techId, quizId);
    }

}
