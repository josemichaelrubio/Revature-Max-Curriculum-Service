package dev.revaturemax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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

<<<<<<< HEAD
=======
    @JsonIgnore
    @OneToMany(mappedBy = "tech", cascade = CascadeType.ALL)
    private List<Topic> topics = new ArrayList<>();     // perhaps, comment this out in the future(according to matthew)

    @JsonIgnore
    @OneToMany(mappedBy ="tech", cascade = CascadeType.ALL)
    private List<TechReview> questions = new ArrayList<>();

>>>>>>> 946628ef83dd84e82954d4a495093fcb4de897ef
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

<<<<<<< HEAD
    public Tech(String name){

=======
    public Tech(Long id, String name) {
        this.id = id;
>>>>>>> 946628ef83dd84e82954d4a495093fcb4de897ef
        this.name = name;
    }

    public Tech(String name, List<Topic> topics, List<TechReview> questions) {
        this.name = name;
        this.topics = topics;
        this.questions = questions;
    }

    public Tech() {
    }
}
