package com.teachpoint.Core.repository;

import com.teachpoint.Core.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
