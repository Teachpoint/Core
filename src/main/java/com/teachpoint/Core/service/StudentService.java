package com.teachpoint.Core.service;

import com.teachpoint.Core.dto.ResponseMessage;
import com.teachpoint.Core.dto.StudentDto;
import org.springframework.http.ResponseEntity;

public interface StudentService {

    StudentDto saveStudent(StudentDto studentDto);

//    List<Student> getStdents();

//    StudentDto getStudent(String  email);

    ResponseEntity<String> activateStudent(Long  studentId, String otpCode);

    ResponseMessage attendStudentToGrade(Long studentId, Long gradeId);
}
