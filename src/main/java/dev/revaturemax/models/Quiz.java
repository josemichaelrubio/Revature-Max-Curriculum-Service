package dev.revaturemax.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Scope("prototype")
public class Quiz {

    private long quizId;

    private String name;

    private List<Tech> techs = new ArrayList<>();

    public Quiz() { }

    public Quiz(long quizId, String name, List<Tech> techs) {
        this.quizId = quizId;
        this.name = name;
        this.techs = techs;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tech> getTechs() {
        return techs;
    }

    public void setTechs(List<Tech> techs) {
        this.techs = techs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return quizId == quiz.quizId && Objects.equals(name, quiz.name) && Objects.equals(techs, quiz.techs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizId, name, techs);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizId=" + quizId +
                ", name='" + name + '\'' +
                ", techs=" + techs +
                '}';
    }
}
