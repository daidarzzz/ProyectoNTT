package com.learnhub.course.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataCourseRepository extends JpaRepository<CourseEntity, Long> {
    @Query("SELECT c FROM CourseEntity c WHERE c.idCategoria = ?1 AND c.deleted = false")
    List<CourseEntity> findByIdCategoriaActive(Long idCategoria);

    @Query("SELECT c FROM CourseEntity c WHERE c.deleted = false")
    List<CourseEntity> findAllActive();

    @Query("SELECT c FROM CourseEntity c WHERE c.id = ?1 AND c.deleted = false")
    Optional<CourseEntity> findByIdActive(Long id);
}
