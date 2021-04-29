package dev.revaturemax.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Component
@Scope("prototype")
@Table(name = "batch_day")
public class BatchDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_day_id")
    private Long id;

    private LocalDate date;

    @ManyToMany
    @JoinTable(name = "batch_day_topic",
            joinColumns = @JoinColumn(name = "batch_day_id"),
            inverseJoinColumns = @JoinColumn(name="topic_id"))
    private List<Topic> topics = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToOne
    @JoinColumn(name = "qc_id")
    private QC qc;

    public BatchDay() {
    }

    public BatchDay(Long id, LocalDate date, List<Topic> topics, Quiz quiz) {
        this.id = id;
        this.date = date;
        this.topics = topics;
        this.quiz = quiz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchDay batchDay = (BatchDay) o;
        return Objects.equals(id, batchDay.id) && Objects.equals(date, batchDay.date) && Objects.equals(topics, batchDay.topics) && Objects.equals(quiz, batchDay.quiz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, topics, quiz);
    }

    @Override
    public String toString() {
        return "BatchDay{" +
                "id=" + id +
                ", date=" + date +
                ", topics=" + topics +
                ", quiz=" + quiz +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
