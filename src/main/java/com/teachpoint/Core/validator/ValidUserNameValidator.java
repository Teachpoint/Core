package com.teachpoint.Core.validator;

import com.teachpoint.Core.dto.StudentDto;
import com.teachpoint.Core.entity.Student;
import com.teachpoint.Core.repository.StudentRepository;
import com.teachpoint.Core.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ValidUserNameValidator implements ConstraintValidator<ValidUserName, StudentDto> {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public boolean isValid(StudentDto studentDto, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("validator run "+studentDto);
        Optional<Student> student = studentRepository.findByEmail(studentDto.getEmail());

        if (student.isEmpty() ) {
            System.out.println(" student is null   ");
            return true;
        }
        return false;
    }
}
