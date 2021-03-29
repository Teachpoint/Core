package com.teachpoint.Core.repository;

import com.teachpoint.Core.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
