package dev.revaturemax.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Component
@Scope("prototype")
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "tech_id")
    private Tech techId;

    private String name;

    public Topic() { }

    public Topic(long id, Tech techId, String name) {
        this.id = id;
        this.techId = techId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tech getTechId() {
        return techId;
    }

    public void setTechId(Tech techId) {
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
        Topic topic = (Topic) o;
        return id == topic.id && Objects.equals(techId, topic.techId) && Objects.equals(name, topic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, techId, name);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", techId=" + techId +
                ", name='" + name + '\'' +
                '}';
    }
}
