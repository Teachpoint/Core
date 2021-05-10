package com.teachpoint.Core.service;

import com.teachpoint.CommonLibrary.Messages.ResponseMessage;
import com.teachpoint.Core.dto.TeacherDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {

    ResponseEntity<TeacherDto> createTeacher(TeacherDto teacherDto, MultipartFile profilePhoto, MultipartFile cv);

    ResponseEntity<String> activateTeacher(Long teacherId, String otpCode);

    ResponseEntity<String> getTeacher(Long teacherId);

    ResponseMessage attendGradeToTeacher(Long teacherId, Long gradeId);
}
