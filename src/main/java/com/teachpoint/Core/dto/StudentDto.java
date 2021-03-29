package com.teachpoint.Core.dto;

import com.teachpoint.Core.validator.ValidUserName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidUserName
public class StudentDto {

    private long id;

    @NotBlank(message = "Student name is mandatory")
    private String name;

    @NotBlank(message = "Student surname is mandatory")
    private String surname;

    private Date dateOfBirth;

    private Integer gender;

    @NotBlank(message = "Student email is mandatory")
    private String phoneNumber;

    @NotBlank(message = "Student email is mandatory")
    private String email;
}
