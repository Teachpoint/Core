package com.teachpoint.Core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teachpoint.CommonLibrary.Messages.ResponseMessage;
import com.teachpoint.Core.dto.TeacherDto;
import com.teachpoint.Core.entity.Grade;
import com.teachpoint.Core.entity.Student;
import com.teachpoint.Core.entity.Teacher;
import com.teachpoint.Core.repository.GradeRepository;
import com.teachpoint.Core.repository.TeacherRepository;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    private StorageService storageService;

    private RestTemplate restTemplate;

    private GradeRepository gradeRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              StorageService storageService,
                              RestTemplate restTemplate,
                              GradeRepository gradeRepository) {
        this.teacherRepository = teacherRepository;
        this.storageService = storageService;
        this.restTemplate = restTemplate;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public ResponseEntity<TeacherDto> createTeacher(TeacherDto teacherDto, MultipartFile profilePhoto, MultipartFile cv) {

        String photoId = storageService.saveFile(profilePhoto, "jpg");
        String cvId = storageService.saveFile(cv, "pdf");
        ModelMapper modelMapper = new ModelMapper();
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        teacher.setCv(cvId);
        teacher.setProfilePhoto(photoId);
        teacher.setState(0);

        TeacherDto teacherDtoResponse = modelMapper.map(teacherRepository.save(teacher), TeacherDto.class);
        return ResponseEntity.ok().body(teacherDtoResponse);
    }

    @Override
    public ResponseEntity<String> activateTeacher(Long teacherId, String otpCode) {
        try {
            Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
            System.out.println(optionalTeacher.get().toString());
            Teacher teacher = optionalTeacher.get();

            if(teacher.getState() == 2) {
                return ResponseEntity.badRequest().body( ResponseMessage.ALREADY_ACTIVE.toString());
            }

            String otpValidationLink = "http://TEACHPOINT-NOTIFICATION/api/v1/validateOtp/" + teacherId + "/?otpnum=" + otpCode;
            ResponseEntity<String> otpResponse = restTemplate.getForEntity(otpValidationLink, String.class);
            teacher.setState(2);
            System.out.println(teacher.toString() + "  updated");
            teacherRepository.save(teacher);
            System.out.println("otpResponse "+otpResponse.getBody());
            System.out.println("otpResponse "+otpResponse.getBody().toString());



            //temporary mapping
//            ResponseMessage otpResponseMessage = new ObjectMapper().readValue(, ResponseMessage.class);
            return ResponseEntity.ok().body(otpResponse.getBody());

        } catch (HttpStatusCodeException hsce) {
            return ResponseEntity.badRequest().body(new ResponseMessage(hsce.getStatusCode().value(), hsce.getMessage()).toString());
//            return ResponseEntity.badRequest().body(ResponseMessage.INTERNAL_ERROR.toString());
        }

    }

    @Override
    public ResponseEntity<String> getTeacher(Long teacherId) {
        return null;
    }

    @Override
    public ResponseMessage attendGradeToTeacher(Long teacherId, Long gradeId) {

        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        Optional<Grade> gradeOptional = gradeRepository.findById(gradeId);
        if(teacherOptional.isPresent() & gradeOptional.isPresent()){
            Teacher teacher = teacherOptional.get();
            Grade grade = gradeOptional.get();
            Set<Grade> gradeSet = teacher.getGrades();
            gradeSet.add(grade);
            teacher.setGrades(gradeSet);
            teacherRepository.save(teacher);
            return ResponseMessage.OK;
        }
        return null;
    }

}
