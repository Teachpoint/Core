package com.teachpoint.Core.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Embeddable
public class ResponseMessage implements Serializable {

    private Integer responseCode;
    private String responseDescription;

    public static ResponseMessage OK = new ResponseMessage(200, "DONE");
    public static ResponseMessage ALREADY_ACTIVE = new ResponseMessage(-400, "Student state already active");
    public static ResponseMessage INTERNAL_ERROR = new ResponseMessage(-500, "Smething went wrong. Please contact to support");

    @Override
    public String toString() {
        return "{" +
                "responseCode=" + responseCode +
                ", responseDescription='" + responseDescription + '\'' +
                '}';
    }
}
