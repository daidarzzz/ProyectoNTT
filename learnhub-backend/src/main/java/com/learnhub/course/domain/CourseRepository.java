package com.learnhub.course.domain;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    List<Course> findByCategoriaId(Long categoriaId);
    Course save(Course course);
    void deleteById(Long id);
}
