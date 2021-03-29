package com.teachpoint.Core.controller;


import com.teachpoint.Core.dto.ResponseMessage;
import com.teachpoint.Core.dto.StudentDto;
import com.teachpoint.Core.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/core", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class StudentController {

    private StudentService studentService;
    private RestTemplate restTemplate;

    /**
     * StudentService bean Dependecy Injection
     *
     * @param studentService
     */
    public StudentController(StudentService studentService, RestTemplate restTemplate) {
        this.studentService = studentService;
        this.restTemplate = restTemplate;
    }


    /**
     * Student creation API
     * <p>
     * ex.
     * {
     * "userName": "Shako",
     * "name": "Shakir",
     * "surname": "Gulmammadov",
     * "dateOfBirth": "1989-01-28",
     * "sex": "Male",
     * "phoneNumber": "0702011396",
     * "email": "sgulmammadov@gmail.com"
     * }
     *
     * @param studentDto
     * @return created studentDto
     */
    @PostMapping("/students")
    public ResponseEntity<StudentDto> saveStudent(@Valid @RequestBody StudentDto studentDto) {

        StudentDto studentDtoResponse = studentService.saveStudent(studentDto);
        ResponseEntity<StudentDto> responseEntity = new ResponseEntity<>(studentDtoResponse, HttpStatus.OK);

        return responseEntity;
    }


    /**
     * @param studentId Student unique name
     * @return
     */
    @PostMapping("/generateOTP/{studentId}")
    public ResponseEntity<String> generateOTP(@PathVariable String studentId) {

        String otpLink = "http://TEACHPOINT-NOTIFICATION/api/v1/generateOTP/" + studentId;
        System.out.println(otpLink);
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(otpLink, String.class);
            return responseEntity;
        } catch (HttpStatusCodeException hsce) {

            return ResponseEntity.badRequest().body(hsce.getResponseBodyAsString());

        }

    }


    /**
     * @param studentId   Student unique name
     * @param otpCode generated otp Code
     * @return
     */
    @GetMapping("/activateStudent/{studentId}")
    public ResponseEntity<ResponseMessage> activateStudent(@PathVariable Long studentId, @RequestParam String otpCode) {
        return studentService.activateStudent(studentId, otpCode);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleStudentFailure(MethodArgumentNotValidException ex) {
        String errorDesc = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(new ResponseMessage(-400, errorDesc));
    }


}
