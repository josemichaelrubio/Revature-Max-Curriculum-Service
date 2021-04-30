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
@Table(name = "curriculum")
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curriculum_id")
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "curriculum_tech",
            joinColumns = @JoinColumn(name = "curriculum_id"),
            inverseJoinColumns = @JoinColumn(name = "tech_id"))
    private List<Tech> techs = new ArrayList<>();

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", techs=" + techs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curriculum that = (Curriculum) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(techs, that.techs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, techs);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Curriculum(long id, String name, List<Tech> techs) {
        this.id = id;
        this.name = name;
        this.techs = techs;
    }

    public Curriculum() {
    }
}
