package com.teachpoint.Core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "classes")
public class Grade {
    public Grade(Long id, String subject, Teacher teacher, int lessonDuration, Instant startTime, Instant creationDate, Instant modifiedDate, Set<Student> students) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.lessonDuration = lessonDuration;
        this.startTime = startTime;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
        this.students = students;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String subject;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Teacher teacher;

    private int lessonDuration;
    private Instant startTime;
    private Instant creationDate;
    private Instant modifiedDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "grades")
    private Set<Student> students;

}
