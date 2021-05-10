package com.teachpoint.Core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teachpoint.CommonLibrary.Messages.ResponseMessage;
import com.teachpoint.Core.dto.TeacherDto;
import com.teachpoint.Core.service.TeacherService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/core")
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @PostMapping(value = "/teachers", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_OCTET_STREAM_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.MULTIPART_MIXED_VALUE})
    public ResponseEntity<TeacherDto> register(@RequestHeader("content-type") String language,
                                                    @RequestParam String teacherText,
                                                    @RequestPart MultipartFile profileImage,
                                                    @RequestPart MultipartFile cv
    ) {


        TeacherDto teacherDto = new TeacherDto();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            teacherDto = objectMapper.readValue(teacherText, TeacherDto.class);
            System.out.println(teacherDto.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return teacherService.createTeacher(teacherDto, profileImage, cv);
    }


    /**
     * @param teacherId   Student unique name
     * @param otpCode generated otp Code
     * @return
     */
    @GetMapping("/activateTeacher/{teacherId}")
    public ResponseEntity<String> activateTeacher(@PathVariable Long teacherId, @RequestParam String otpCode) {
        return teacherService.activateTeacher(teacherId, otpCode);
    }


    /**
     * @param teacherId   Teacher unique name
     * @param gradeId class id
     * @return
     */
    @PutMapping("/attend/{teacherId}/{gradeId}")
    public ResponseEntity<ResponseMessage> attendToGrade(@PathVariable Long teacherId, @PathVariable Long gradeId) {
        ResponseMessage response = teacherService.attendGradeToTeacher(teacherId, gradeId);

        if (response.getResponseCode() == 200){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

}
