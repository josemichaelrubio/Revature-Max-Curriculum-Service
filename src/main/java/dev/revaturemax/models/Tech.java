package dev.revaturemax.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Scope("prototype")
public class Tech {

    private long techId;

    private String name;

    private List<String> questions = new ArrayList<>();

    public Tech() { }

    public Tech(long techId, String name, List<String> questions) {
        this.techId = techId;
        this.name = name;
        this.questions = questions;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public long getTechId() {
        return techId;
    }

    public void setTechId(long techId) {
        this.techId = techId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tech tech = (Tech) o;
        return techId == tech.techId && Objects.equals(name, tech.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(techId, name);
    }

    @Override
    public String toString() {
        return "Tech{" +
                "techId=" + techId +
                ", name='" + name + '\'' +
                ", questions=" + questions +
                '}';
    }
}
