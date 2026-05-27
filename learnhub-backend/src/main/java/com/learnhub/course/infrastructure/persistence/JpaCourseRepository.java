package com.learnhub.course.infrastructure.persistence;

import com.learnhub.course.domain.Course;
import com.learnhub.course.domain.CourseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCourseRepository implements CourseRepository {

    private final SpringDataCourseRepository springRepo;
    private final CourseEntityMapper mapper;

    public JpaCourseRepository(SpringDataCourseRepository springRepo, CourseEntityMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public List<Course> findAll() {
        return springRepo.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Course> findByCategoriaId(Long categoriaId) {
        return springRepo.findByIdCategoria(categoriaId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Course save(Course course) {
        var entity = mapper.toEntity(course);
        var saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }
}
