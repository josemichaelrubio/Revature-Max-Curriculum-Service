package dev.revaturemax.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class Curriculum {

    private long id;

    private String name;

    private List<Tech> techs;

    private List<Quiz> quizzes;

}
