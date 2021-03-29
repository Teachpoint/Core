package com.teachpoint.Core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;
    private String email;
    private int state;
    private String firstName;
    private String lastName;
    private int gender;
    private Date dateOfBirth;
    private String profilePhoto;
    private String cv;
    private String about;
    private Instant creationDate;
    private Instant modifiedDate;

}

