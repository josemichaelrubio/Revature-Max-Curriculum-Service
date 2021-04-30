package dev.revaturemax.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Component
@Scope("prototype")
@Table(name = "tech_review")
public class TechReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tech_id")
    private Tech tech;

    private String question;

    @Override
    public String toString() {
        return "TechReview{" +
                "id=" + id +
                ", tech=" + tech +
                ", question='" + question + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechReview that = (TechReview) o;
        return Objects.equals(id, that.id) && Objects.equals(tech, that.tech) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tech, question);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tech getTech() {
        return tech;
    }

    public void setTech(Tech tech) {
        this.tech = tech;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public TechReview(Long id, Tech tech, String question) {
        this.id = id;
        this.tech = tech;
        this.question = question;
    }

    public TechReview() {
    }
}
