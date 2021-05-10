package com.teachpoint.Core.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.teachpoint.Core.dto.ResponseMessage;
import com.teachpoint.Core.dto.StudentDto;
import com.teachpoint.Core.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;

@ComponentScan(basePackages = "com.teachpoint.Core")
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createStudent() throws Exception {

        StudentDto studentDto = new StudentDto(0, "Shakir", "Gulmammadov", new SimpleDateFormat("yyyy-MM-dd").parse("1989-01-28"), 1, "0702011396", "sgulmammaoov@gmail.com");

        Mockito.doReturn(studentDto).when(studentService).saveStudent(ArgumentMatchers.any());

        StudentDto returnedStudentDto = studentService.saveStudent(studentDto);
        Assertions.assertNotNull(returnedStudentDto, "Saved student can not be null");
        Assertions.assertEquals(0, returnedStudentDto.getId(), "The id should be increment");
    }


    @Test
    void activateStudent() throws Exception {

        Long studentId = 1000L;
        String otpCode = "111222";
                Mockito.doReturn(ResponseEntity.ok().body(new ResponseMessage(200, "Valid OTP code"))).when(studentService).activateStudent(studentId, otpCode);


                ResponseEntity<String> responseEntity = studentService.activateStudent(studentId, otpCode);

                Assertions.assertNotNull(responseEntity.getStatusCodeValue(), "Error on Student activation");
                Assertions.assertEquals(200, responseEntity.getStatusCodeValue(), "Activation student api problem");
    }

}
