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
    private long id;

    private LocalDate date;

    @Column(name = "batch_id")
    private long batchId;

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
    public BatchDay(Long id, LocalDate date, List<Topic> topics, Quiz quiz,QC qc) {
        this.id = id;
        this.date = date;
        this.topics = topics;
        this.quiz = quiz;
        this.qc = qc;
    }

    public BatchDay(LocalDate date, Long batchId, List<Topic> topics, Quiz quiz, QC qc) {
        this.date = date;
        this.batchId = batchId;
        this.topics = topics;
        this.quiz = quiz;
        this.qc = qc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchDay batchDay = (BatchDay) o;
        return id == batchDay.id && batchId == batchDay.batchId && date.equals(batchDay.date) && topics.equals(batchDay.topics) && quiz.equals(batchDay.quiz) && qc.equals(batchDay.qc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, batchId, topics, quiz, qc);
    }

    @Override
    public String toString() {
        return "BatchDay{" +
                "id=" + id +
                ", date=" + date +
                ", batchId=" + batchId +
                ", topics=" + topics +
                ", quiz=" + quiz +
                ", qc=" + qc +
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

    public QC getQc() {
        return qc;
    }

    public void setQc(QC qc) {
        this.qc = qc;
    }

    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }
}
