package dev.revaturemax.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Scope("prototype")
@Table(name = "fave_notes")
public class FavNotes {

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "notes_id")
    private Long notesId;

    @OneToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;



}
