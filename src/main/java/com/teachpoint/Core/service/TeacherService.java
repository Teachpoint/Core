package com.teachpoint.Core.service;

import com.teachpoint.Core.dto.TeacherDto;
import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {

    TeacherDto createTeacher(TeacherDto teacherDto, MultipartFile profilePhoto, MultipartFile cv);

}
