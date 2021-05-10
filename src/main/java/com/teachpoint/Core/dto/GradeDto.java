package com.teachpoint.Core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDto {

    private String subject;
    private int lessonDuration;
    private Date startTime;

}
