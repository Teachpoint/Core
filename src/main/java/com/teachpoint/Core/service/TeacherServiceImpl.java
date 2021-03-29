package com.teachpoint.Core.service;

import com.teachpoint.Core.dto.TeacherDto;
import com.teachpoint.Core.entity.Student;
import com.teachpoint.Core.entity.Teacher;
import com.teachpoint.Core.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    private final Path root = Paths.get("C://profile_photos");

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto, MultipartFile profilePhoto, MultipartFile cv) {

        UUID photoId = UUID.randomUUID();
        UUID cvId = UUID.randomUUID();
        System.out.println(photoId);
        System.out.println(cvId);
        try {
            Files.copy(profilePhoto.getInputStream(), this.root.resolve(photoId.toString() + ".jpg"));
            Files.copy(cv.getInputStream(), this.root.resolve(cvId.toString() + ".pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ModelMapper modelMapper = new ModelMapper();
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        teacher.setCv(cvId.toString());
        teacher.setProfilePhoto(photoId.toString());
        teacher.setState(0);


        teacherRepository.save(teacher);

        return null;
    }
}
