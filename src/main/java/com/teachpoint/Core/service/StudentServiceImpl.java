package com.teachpoint.Core.service;

import com.teachpoint.Core.dto.ResponseMessage;
import com.teachpoint.Core.dto.StudentDto;
import com.teachpoint.Core.entity.Student;
import com.teachpoint.Core.entity.enums.StudentState;
import com.teachpoint.Core.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private RestTemplate restTemplate;

    public StudentServiceImpl(StudentRepository studentRepository, RestTemplate restTemplate) {
        this.studentRepository = studentRepository;
        this.restTemplate = restTemplate;
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
        StudentDto studentDtoResponse = modelMapper.map(studentResponse, StudentDto.class);

        return studentDtoResponse;
    }


    public StudentDto getStudent(String email) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Student> student = studentRepository.findByEmail(email);
        StudentDto studentDto = modelMapper.map(student.get(), StudentDto.class);

        return studentDto;
    }


    public ResponseEntity<ResponseMessage> activateStudent(Long studentId, String otpCode) {
        try {
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            Student student = optionalStudent.get();

            if(student.getState().equals(StudentState.ACTIVE)) {
                return ResponseEntity.badRequest().body( ResponseMessage.ALREADY_ACTIVE);
            }

            String otpValidationLink = "http://TEACHPOINT-NOTIFICATION/api/v1/validateOtp/" + studentId + "/?otpnum=" + otpCode;
            ResponseEntity<String> otpResponse = restTemplate.getForEntity(otpValidationLink, String.class);
            student.setState(StudentState.ACTIVE);
            System.out.println(student.toString() + "  updated");
            studentRepository.save(student);
            return ResponseEntity.ok().body(new ResponseMessage(otpResponse.getStatusCodeValue(), otpResponse.getBody()));

        } catch (HttpStatusCodeException hsce) {
            return ResponseEntity.badRequest().body(new ResponseMessage(hsce.getStatusCode().value(), hsce.getMessage()));
        }

    }
}
