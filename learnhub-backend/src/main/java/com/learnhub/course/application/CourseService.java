package com.learnhub.course.application;

import com.learnhub.course.application.dto.CourseResponse;
import com.learnhub.course.application.dto.CreateCourseRequest;
import com.learnhub.course.application.dto.UpdateCourseRequest;
import com.learnhub.course.domain.Course;
import com.learnhub.course.domain.CourseRepository;
import com.learnhub.shared.domain.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> findAll() {
        return courseRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> findAllIncludingDeleted() {
        return courseRepository.findAllIncludingDeleted().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CourseResponse findById(Long id) {
        var course = courseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Curso", id));
        return toResponse(course);
    }

    @Transactional(readOnly = true)
    public CourseResponse findByIdIncludingDeleted(Long id) {
        var course = courseRepository.findByIdIncludingDeleted(id)
            .orElseThrow(() -> new ResourceNotFoundException("Curso", id));
        return toResponse(course);
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> findByCategoria(Long categoriaId) {
        return courseRepository.findByCategoriaId(categoriaId).stream().map(this::toResponse).toList();
    }

    public CourseResponse create(CreateCourseRequest request) {
        var course = new Course(
            request.nombre(),
            request.descripcion(),
            request.descripcionLarga(),
            request.precio(),
            request.idCategoria(),
            request.horas(),
            request.autor()
        );
        if (request.imagenes() != null) {
            course.setImagenes(request.imagenes());
        }
        return toResponse(courseRepository.save(course));
    }

    public CourseResponse update(Long id, UpdateCourseRequest request) {
        var course = courseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Curso", id));
        course.setNombre(request.nombre());
        course.setDescripcion(request.descripcion());
        course.setDescripcionLarga(request.descripcionLarga());
        course.setPrecio(request.precio());
        course.setIdCategoria(request.idCategoria());
        course.setHoras(request.horas());
        course.setAutor(request.autor());
        if (request.imagenes() != null) {
            course.setImagenes(request.imagenes());
        }
        return toResponse(courseRepository.save(course));
    }

    public void delete(Long id) {
        if (courseRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Curso", id);
        }
        courseRepository.deleteById(id);
    }

    public void softDelete(Long id) {
        if (courseRepository.findByIdIncludingDeleted(id).isEmpty()) {
            throw new ResourceNotFoundException("Curso", id);
        }
        courseRepository.softDeleteById(id);
    }

    public void hardDelete(Long id) {
        if (courseRepository.findByIdIncludingDeleted(id).isEmpty()) {
            throw new ResourceNotFoundException("Curso", id);
        }
        courseRepository.hardDeleteById(id);
    }

    public void restore(Long id) {
        if (courseRepository.findByIdIncludingDeleted(id).isEmpty()) {
            throw new ResourceNotFoundException("Curso", id);
        }
        courseRepository.restoreById(id);
    }

    private CourseResponse toResponse(Course course) {
        return new CourseResponse(
            course.getId(),
            course.getNombre(),
            course.getDescripcion(),
            course.getDescripcionLarga(),
            course.getPrecio(),
            course.getIdCategoria(),
            course.getHoras(),
            course.getAutor(),
            course.getImagenes()
        );
    }
}
