package com.teachpoint.Core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private String phoneNumber;
    private String email;
    private int state;
    private String firstName;
    private String lastName;
    private int gender;
    private Date dateOfBirth;
    private String about;

}
