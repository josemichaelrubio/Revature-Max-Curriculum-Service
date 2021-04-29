package dev.revaturemax.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Component
@Scope("prototype")
@Table(name = "fav_notes")
public class FavNotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fav_notes_id")
    private long id;

    @Column(name = "employee_id")
    private long employeeId;

    @Column(name = "notes_id")
    private long notesId;

    @Override
    public String toString() {
        return "FavNotes{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", notesId=" + notesId +
                ", topic=" + topic +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavNotes favNotes = (FavNotes) o;
        return Objects.equals(id, favNotes.id) && Objects.equals(employeeId, favNotes.employeeId) && Objects.equals(notesId, favNotes.notesId) && Objects.equals(topic, favNotes.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, notesId, topic);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public long getNotesId() {
        return notesId;
    }

    public void setNotesId(Long notesId) {
        this.notesId = notesId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @OneToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public FavNotes(Long employeeId, Long notesId, Topic topic) {
        this.employeeId = employeeId;
        this.notesId = notesId;
        this.topic = topic;
    }

    public FavNotes() {
    }
}
