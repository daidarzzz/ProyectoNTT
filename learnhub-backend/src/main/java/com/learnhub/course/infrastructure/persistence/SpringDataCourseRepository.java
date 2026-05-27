package com.learnhub.course.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataCourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findByIdCategoria(Long idCategoria);
}
