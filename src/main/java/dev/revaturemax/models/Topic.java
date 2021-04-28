package dev.revaturemax.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
public class Topic {

    private long id;

    private Tech tech;

    private String name;

    public Topic() { }

    public Topic(long id, Tech tech, String name) {
        this.id = id;
        this.tech = tech;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tech getTech() {
        return tech;
    }

    public void setTech(Tech tech) {
        this.tech = tech;
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
        Topic topic = (Topic) o;
        return id == topic.id && Objects.equals(tech, topic.tech) && Objects.equals(name, topic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tech, name);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", tech=" + tech +
                ", name='" + name + '\'' +
                '}';
    }
}
