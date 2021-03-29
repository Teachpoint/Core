package com.teachpoint.Core.exception;

import com.teachpoint.Core.dto.ResponseMessage;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class StudentException extends RuntimeException{

private ResponseMessage errorResponse;

public StudentException(ResponseMessage errorResponse){
super(errorResponse.getResponseDescription());
this.errorResponse = errorResponse;
    System.out.println("exxxxxxxxxxxx");
}
}
