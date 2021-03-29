package com.teachpoint.Core.entity;

import com.teachpoint.Core.entity.converters.StudentSexAttributeConverter;
import com.teachpoint.Core.entity.converters.StudentStateAttributeConverter;
import com.teachpoint.Core.entity.enums.StudentSex;
import com.teachpoint.Core.entity.enums.StudentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.consumer.internals.SubscriptionState;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long Id;

    @Convert(converter = StudentStateAttributeConverter.class)
    private StudentState state;

    private String name;

    private String surname;

    private Date dateOfBirth;

    private Integer gender;

    private String phoneNumber;

    private String email;

    private Instant creationDate;

    @Column(name = "modified_date")
    private Instant modifiedDate;
}
