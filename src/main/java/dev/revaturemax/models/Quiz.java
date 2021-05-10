package dev.revaturemax.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Component
@Scope("prototype")
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "quiz_tech",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name="tech_id"))
    private List<Tech> techs = new ArrayList<>();

    public Quiz() { }

    public Quiz(long quizId, String name, List<Tech> techs) {
        this.id = quizId;
        this.name = name;
        this.techs = techs;
    }

    public long getId() {
        return id;
    }

    public void setId(long quizId) {
        this.id = quizId;
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
        return id == quiz.id && Objects.equals(name, quiz.name) && Objects.equals(techs, quiz.techs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, techs);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizId=" + id +
                ", name='" + name + '\'' +
                ", techs=" + techs +
                '}';
    }
}
