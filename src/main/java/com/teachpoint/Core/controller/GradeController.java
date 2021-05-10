package com.teachpoint.Core.controller;


import com.teachpoint.Core.dto.GradeDto;
import com.teachpoint.Core.dto.ResponseMessage;
import com.teachpoint.Core.service.GradeService;
import com.teachpoint.Core.service.TeacherService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/core", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class GradeController {

    private GradeService gradeService;
    private TeacherService teacherService;

    public GradeController(GradeService gradeService, TeacherService teacherService) {
        this.gradeService = gradeService;
        this.teacherService = teacherService;
    }

    @PostMapping("/{teacher_id}/grades")
    public ResponseEntity<String> saveStudent(@PathVariable (value = "teacher_id") Long teacherId,
                                             @RequestBody GradeDto gradeDto) {
        System.out.println("grade "+gradeDto);
        GradeDto gradeResponse = gradeService.saveGrade(gradeDto, teacherId);
        if(gradeResponse != null){
            return ResponseEntity.ok().body(gradeResponse.toString());
        }
        return ResponseEntity.badRequest().body(ResponseMessage.INTERNAL_ERROR.toString());

    }

    @PostMapping("/grades")
    public ResponseEntity<String> saveGrade(@RequestBody GradeDto gradeDto) {
        System.out.println("grade "+gradeDto);
        GradeDto gradeResponse = gradeService.saveGrade(gradeDto);
        if(gradeResponse != null){
            return ResponseEntity.ok().body(gradeResponse.toString());
        }
        return ResponseEntity.badRequest().body(ResponseMessage.INTERNAL_ERROR.toString());

    }

}
