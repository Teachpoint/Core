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
        ResponseEntity<StudentDto> responseEntity = new ResponseEntity<>(studentDtoResponse, HttpStatus.CREATED);

        return responseEntity;
    }


    /**
     * @param objectId Student or Teacher unique name
     * @return
     */
    @PostMapping("/generateOTP/{objectId}")
    public ResponseEntity<String> generateOTP(@PathVariable String objectId) {

        String otpLink = "http://TEACHPOINT-NOTIFICATION/api/v1/generateOTP/" + objectId;
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
    public ResponseEntity<String> activateStudent(@PathVariable Long studentId, @RequestParam String otpCode) {
        return studentService.activateStudent(studentId, otpCode);
    }


    /**
     * @param studentId   Student unique name
     * @param gradeId class id
     * @return
     */
    @PutMapping("/attend/{studentId}/{gradeId}")
    public ResponseEntity<ResponseMessage> attendToGrade(@PathVariable Long studentId, @PathVariable Long gradeId) {
        ResponseMessage response = studentService.attendStudentToGrade(studentId, gradeId);

        if (response.getResponseCode() == 200){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleStudentFailure(MethodArgumentNotValidException ex) {
        String errorDesc = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(new ResponseMessage(-400, errorDesc));
    }


}
