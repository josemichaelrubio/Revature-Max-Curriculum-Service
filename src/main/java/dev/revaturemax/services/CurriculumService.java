package dev.revaturemax.services;

import dev.revaturemax.models.QC;
import dev.revaturemax.models.Quiz;
import dev.revaturemax.models.Tech;
import dev.revaturemax.models.Topic;
import dev.revaturemax.projections.TopicDTO;
import dev.revaturemax.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CurriculumService {

    @Autowired
    private CurriculumRepository curriculumRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TechRepository techRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QCRepository qcRepository;

    // service crud methods for technologies specific to a curriculum
    public List<Tech> getAllTech(long curriculumId){
        return curriculumRepository.getOne(curriculumId).getTechs();
    }

    // service crud methods for technologies
    public List<Tech> getTechs(){
        return techRepository.findAll();
    }
    public Tech createTech(Tech tech){
        return techRepository.save(tech);
    }
    public void deleteTech(long id){
        techRepository.deleteById(id);
    }


    // service crud methods for topics within a specific technology
    public List<Topic> getTopics(long techId){
        return topicRepository.findAllByTechId(techId);
//        return techRepository.getOne(techId).getTopics();
    }

    // getting multiple topics from a set of ids
    public List<TopicDTO> getMultipleTopics(Set<Long> topicIds){
        return topicRepository.findAllById(topicIds);
    }

    @Transactional
    public void createTopic(long techId, Topic topic){
        Tech tech = techRepository.getOne(techId);
        List<Topic> topics = tech.getTopics();
        topics.add(topic);
        tech.setTopics(topics);
        techRepository.save(tech);
    }

    @Transactional
    public void updateTopics(long techId, List<Topic> topics){
        Tech updateTech = techRepository.getOne(techId);
        updateTech.setTopics(topics);
        techRepository.save(updateTech);
    }
    @Transactional
    public void removeTopic(long techId, long topicId){
        Topic badTopic = topicRepository.getOne(topicId);
        Tech newTech = techRepository.getOne(techId);
        if(newTech.getTopics().remove(badTopic)){
            techRepository.save(newTech);
        }

    }


    // service crud methods for quizzes within a specific technology
    public Quiz getOneQuiz(long quizId) {
        return quizRepository.getOne(quizId);
    }

    public List<Quiz> getMultipleQuizzes(Set<Long> ids){
        return quizRepository.findAllById(ids);
    }

    @Transactional
    public ResponseEntity<HttpStatus> updateQuizzes( long quizId, List<Tech> techs) {

        Quiz quiz = quizRepository.getOne(quizId);

        quiz.setTechs(techs);

        quizRepository.save(quiz);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<HttpStatus> removeQuiz(long techId, long quizId) {

        Tech badTech = techRepository.getOne(techId);

        Quiz newQuiz = quizRepository.getOne(quizId);

        if(newQuiz.getTechs().remove(badTech)){
            quizRepository.save(newQuiz);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


	public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
	}

    public List<QC> getAllQC() {
        return qcRepository.findAll();
    }
}
