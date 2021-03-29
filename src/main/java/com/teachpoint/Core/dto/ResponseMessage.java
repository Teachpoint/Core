package com.teachpoint.Core.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@Embeddable
public class ResponseMessage {

    private Integer responseCode;
    private String responseDescription;

public static ResponseMessage ALREADY_ACTIVE = new ResponseMessage(-400, "Student state already active");
}
