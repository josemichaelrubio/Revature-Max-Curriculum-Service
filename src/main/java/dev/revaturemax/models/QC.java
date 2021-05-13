package dev.revaturemax.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
    private long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "qc_tech",
            joinColumns = @JoinColumn(name = "qc_id"),
            inverseJoinColumns = @JoinColumn(name="tech_id"))
    private List<Tech> techs = new ArrayList<>();

    public QC(){}
    public QC(long id, String name, List<Tech> techs) {
        this.id = id;
        this.name = name;
        this.techs = techs;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QC qc = (QC) o;
        return id == qc.id && name.equals(qc.name) && techs.equals(qc.techs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, techs);
    }

    @Override
    public String toString() {
        return "QC{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", techs=" + techs +
                '}';
    }
}
