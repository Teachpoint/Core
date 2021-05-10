package com.teachpoint.Core.service;

import com.teachpoint.Core.dto.GradeDto;
import com.teachpoint.Core.entity.Grade;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface GradeService {

    GradeDto saveGrade(GradeDto gradeDto, long teacherId);

    GradeDto saveGrade(GradeDto gradeDto);

    Optional<Grade> findGrade(long id);
}
