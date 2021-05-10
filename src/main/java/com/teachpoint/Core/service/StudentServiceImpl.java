package com.teachpoint.Core.service;

import com.teachpoint.Core.dto.ResponseMessage;
import com.teachpoint.Core.dto.StudentDto;
import com.teachpoint.Core.entity.Grade;
import com.teachpoint.Core.entity.enums.StudentState;
import com.teachpoint.Core.repository.GradeRepository;
import com.teachpoint.Core.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import com.teachpoint.Core.entity.Student;

import java.time.Instant;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private RestTemplate restTemplate;
    private GradeRepository gradeRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              RestTemplate restTemplate,
                              GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.restTemplate = restTemplate;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        System.out.println(studentDto.toString());
        ModelMapper modelMapper = new ModelMapper();
        Student student = modelMapper.map(studentDto, Student.class);
        student.setState(StudentState.INITIAL);
        student.setCreationDate(Instant.now());

        System.out.println(student.toString());
        Student studentResponse = studentRepository.save(student);

        if (studentResponse == null) {
            System.out.println("testing ");
        }

        StudentDto studentDtoResponse = modelMapper.map(studentResponse, StudentDto.class);

        return studentDtoResponse;
    }


    public StudentDto getStudent(String email) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Student> student = studentRepository.findByEmail(email);
        StudentDto studentDto = modelMapper.map(student.get(), StudentDto.class);

        return studentDto;
    }


    public ResponseEntity<String> activateStudent(Long studentId, String otpCode) {
        try {
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            System.out.println(optionalStudent.get().toString());
            Student student = optionalStudent.get();

            if(student.getState().equals(StudentState.ACTIVE)) {
                return ResponseEntity.badRequest().body( ResponseMessage.ALREADY_ACTIVE.toString());
            }

            String otpValidationLink = "http://TEACHPOINT-NOTIFICATION/api/v1/validateOtp/" + studentId + "/?otpnum=" + otpCode;
                ResponseEntity<String> otpResponse = restTemplate.getForEntity(otpValidationLink, String.class);
            student.setState(StudentState.ACTIVE);
            System.out.println(student.toString() + "  updated");
            studentRepository.save(student);
            System.out.println("otpResponse "+otpResponse);
            return otpResponse;

        } catch (HttpStatusCodeException hsce) {
            return ResponseEntity.badRequest().body(new ResponseMessage(hsce.getStatusCode().value(), hsce.getMessage()).toString());
        }

    }


    //Make it clean code
    @Override
    public ResponseMessage attendStudentToGrade(Long studentId, Long gradeId) {

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Grade> gradeOptional = gradeRepository.findById(gradeId);
        if(studentOptional.isPresent() & gradeOptional.isPresent()){
            Student student = studentOptional.get();
            Grade grade = gradeOptional.get();
            student.getGrades().add(grade);
            studentRepository.save(student);
            return ResponseMessage.OK;
        }
            return null;
    }
}
