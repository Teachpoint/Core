package com.teachpoint.Core.controller;

import com.teachpoint.Core.dto.ResponseMessage;
//import com.teachpoint.Core.entity.Student;
import com.teachpoint.Core.entity.enums.StudentState;
import com.teachpoint.Core.repository.StudentRepository;
//import com.teachpoint.Core.service.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

@ComponentScan(basePackages = "com.teachpoint.Core")
@SpringBootTest
@AutoConfigureMockMvc
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
//    StudentServiceImpl studentService;

    @Mock
    RestTemplate restTemplate;

//    @Test
//    void createStudent() throws Exception {
//
//        StudentDto studentDto = new StudentDto(0, "Shakiiir", "Gulmammadov", new SimpleDateFormat("yyyy-MM-dd").parse("1989-01-28"), 1, "0702011396", "sgulmammaoov@gmail.com");
//
//        Student student = new Student(122111L, StudentState.INITIAL, "Shakir", "Gulmammadov", new SimpleDateFormat("yyyy-MM-dd").parse("1989-01-28"), 1, "0702011396", "sgulmammadov@gmail.com", Instant.now(), Instant.now());
//        Mockito.doReturn(student).when(studentRepository).save(ArgumentMatchers.any());
//
//        StudentDto returnedStudentDto = studentService.saveStudent(studentDto);
//        Assertions.assertNotNull(returnedStudentDto, "Saved student can not be null");
//        Assertions.assertEquals(student.getId(), returnedStudentDto.getId(), "The id should be increment");
//        Assertions.assertEquals(student.getEmail(), returnedStudentDto.getEmail(), "The id should be increment");
//        Assertions.assertEquals(student.getPhoneNumber(), returnedStudentDto.getPhoneNumber(), "The id should be increment");
//        Assertions.assertEquals(student.getName(), returnedStudentDto.getName(), "The id should be increment");
//    }


//    @Test
//    void activateStudent() throws Exception {
//
//        Long studentId = 1000L;
//        String otpCode = "111222";
//
//        Student student = new Student(122111L, StudentState.INITIAL, "Shakir", "Gulmammadov", new SimpleDateFormat("yyyy-MM-dd").parse("1989-01-28"), 1, "0702011396", "sgulmammadov@gmail.com", Instant.now(), Instant.now());
//        Optional<Student> studentOptional = Optional.ofNullable(student);
//
////        Mockito.when(restTemplate.getForEntity("http://TEACHPOINT-NOTIFICATION/api/v1/validateOtp/" + studentId + "?otpnum=" + otpCode, ResponseMessage.class))
////                .thenReturn(ResponseEntity.ok().body(new ResponseMessage(200, "Valid OTP code")));
////        Mockito.when(restTemplate.getForEntity("http://TEACHPOINT-NOTIFICATION/api/v1/validateOtp/" + studentId + "?otpnum=" + otpCode, ResponseMessage.class))
////        .thenReturn(ResponseEntity.ok().body(new ResponseMessage(200, "Valid OTP code")));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//        HttpEntity<?> entity = new HttpEntity<>(headers);
//doReturn(ResponseEntity.ok().body(new ResponseMessage(200, "Valid OTP code"))).when(restTemplate)
//        .exchange("http://TEACHPOINT-NOTIFICATION/api/v1/validateOtp/" + studentId + "?otpnum=" + otpCode,
//                HttpMethod.GET, entity, String.class);
//
//
//        doReturn(studentOptional).when(studentRepository).findById(studentId);
//        doReturn(student).when(studentRepository).save(ArgumentMatchers.any());
//        ResponseEntity<String> responseEntity = studentService.activateStudent(studentId, otpCode);
//
//        System.out.println(responseEntity);
//
//        Assertions.assertNotNull(responseEntity.getStatusCodeValue(), "Error on Student activation");
//        Assertions.assertEquals(200, responseEntity.getStatusCodeValue(), "Activation student api problem");
//    }
}
