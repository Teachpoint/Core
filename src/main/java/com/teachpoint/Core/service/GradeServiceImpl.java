package com.teachpoint.Core.service;

import com.teachpoint.Core.dto.GradeDto;
import com.teachpoint.Core.entity.Grade;
import com.teachpoint.Core.entity.Student;
import com.teachpoint.Core.entity.Teacher;
import com.teachpoint.Core.repository.GradeRepository;
import com.teachpoint.Core.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    private GradeRepository gradeRepository;
    private TeacherRepository teacherRepository;

    public GradeServiceImpl(GradeRepository gradeRepository, TeacherRepository teacherRepository) {
        this.gradeRepository = gradeRepository;
        this.teacherRepository = teacherRepository;
    }


    @Override
    public GradeDto saveGrade(GradeDto gradeDto, long teacherId) {

        ModelMapper modelMapper = new ModelMapper();
        Grade grade = modelMapper.map(gradeDto, Grade.class);

        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);

        if(!optionalTeacher.isEmpty()){
            Teacher teacher = optionalTeacher.get();
            grade.setTeacher(teacher);

            Grade resposeGrade =  gradeRepository.save(grade);
            GradeDto responseGradeDto = modelMapper.map(resposeGrade, GradeDto.class);
            return responseGradeDto;
        }
        return null;
    }

    @Override
    public GradeDto saveGrade(GradeDto gradeDto) {

        ModelMapper modelMapper = new ModelMapper();
        Grade grade = modelMapper.map(gradeDto, Grade.class);

            Grade resposeGrade =  gradeRepository.save(grade);
            GradeDto responseGradeDto = modelMapper.map(resposeGrade, GradeDto.class);
            return responseGradeDto;

    }

    @Override
    public Optional<Grade> findGrade(long id) {
        return gradeRepository.findById(id);
    }
}
