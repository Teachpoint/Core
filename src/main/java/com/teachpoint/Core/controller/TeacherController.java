package com.teachpoint.Core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teachpoint.Core.dto.ResponseMessage;
import com.teachpoint.Core.dto.TeacherDto;
import com.teachpoint.Core.service.TeacherService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/ee")
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping(value = "/teachers", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_OCTET_STREAM_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.MULTIPART_MIXED_VALUE})
    public ResponseEntity<ResponseMessage> register(@RequestHeader("content-type") String language,
                                                        @RequestParam String teacherText,
                                                    @RequestPart MultipartFile profileImage,
                                                    @RequestPart MultipartFile cv
    ) {
        System.out.println(language);
        System.out.println("ACCEPTED");
        System.out.println(teacherText);
        System.out.println(profileImage.getSize());

        TeacherDto teacherDto = new TeacherDto();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            teacherDto = objectMapper.readValue(teacherText, TeacherDto.class);
            System.out.println(teacherDto.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        teacherService.createTeacher(teacherDto, profileImage, cv);
        return null;
    }

}
