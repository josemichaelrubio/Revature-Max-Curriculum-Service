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
@Table(name = "qc")
public class QC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qc_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "qc_tech",
            joinColumns = @JoinColumn(name = "qc_id"),
            inverseJoinColumns = @JoinColumn(name="tech_id"))
    private List<Tech> techs = new ArrayList<>();

    @Override
    public String toString() {
        return "QC{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", techs=" + techs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QC qc = (QC) o;
        return Objects.equals(id, qc.id) && Objects.equals(name, qc.name) && Objects.equals(techs, qc.techs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, techs);
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

    public List<Tech> getTechs() {
        return techs;
    }

    public void setTechs(List<Tech> techs) {
        this.techs = techs;
    }

    public QC(Long id, String name, List<Tech> techs) {
        this.id = id;
        this.name = name;
        this.techs = techs;
    }

    public QC() {
    }
}