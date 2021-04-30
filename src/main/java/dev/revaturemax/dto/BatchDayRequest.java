package dev.revaturemax.dto;

import dev.revaturemax.models.QC;
import dev.revaturemax.models.Quiz;
import dev.revaturemax.models.Topic;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class BatchDayRequest {
    LocalDate date;
    Quiz quiz;
    QC qc;
    long batchId;
    List<Topic> topics;
    public BatchDayRequest() {
    }

    public BatchDayRequest(LocalDate date, Quiz quiz, QC qc, long batchId, List<Topic> topics) {
        this.date = date;
        this.quiz = quiz;
        this.qc = qc;
        this.batchId = batchId;
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "BatchDayRequest{" +
                "date=" + date +
                ", quiz=" + quiz +
                ", qc=" + qc +
                ", batchId=" + batchId +
                ", topics=" + topics +
                '}';
    }

    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchDayRequest that = (BatchDayRequest) o;
        return Objects.equals(date, that.date) && Objects.equals(quiz, that.quiz) && Objects.equals(qc, that.qc) && Objects.equals(topics, that.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, quiz, qc, topics);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
