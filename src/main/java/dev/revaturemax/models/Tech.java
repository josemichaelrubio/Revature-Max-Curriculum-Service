package dev.revaturemax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Component
@Scope("prototype")
@Table(name = "tech")
public class Tech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tech_id")
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Topic> topics = new ArrayList<>();

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<TechReview> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TechReview> questions) {
        this.questions = questions;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<TechReview> questions = new ArrayList<>();

    public Tech(Long id, String name, List<Topic> topics, List<TechReview> questions) {
        this.id = id;
        this.name = name;
        this.topics = topics;
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Tech{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tech tech = (Tech) o;
        return Objects.equals(id, tech.id) && Objects.equals(name, tech.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tech(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public Tech(String name){
        this.name = name;
    }

    public Tech() {
    }
}
