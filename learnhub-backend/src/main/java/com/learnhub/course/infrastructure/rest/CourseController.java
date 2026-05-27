package com.learnhub.course.infrastructure.rest;

import com.learnhub.course.application.CourseService;
import com.learnhub.course.application.dto.CourseResponse;
import com.learnhub.course.application.dto.CreateCourseRequest;
import com.learnhub.course.application.dto.UpdateCourseRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> findAll(
            @RequestParam(required = false) Long categoria,
            @RequestParam(defaultValue = "false") boolean incluirEliminados) {
        if (incluirEliminados) {
            return ResponseEntity.ok(courseService.findAllIncludingDeleted());
        }
        if (categoria != null) {
            return ResponseEntity.ok(courseService.findByCategoria(categoria));
        }
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CreateCourseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody UpdateCourseRequest request) {
        return ResponseEntity.ok(courseService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/soft-delete")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        courseService.softDelete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/hard")
    public ResponseEntity<Void> hardDelete(@PathVariable Long id) {
        courseService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        courseService.restore(id);
        return ResponseEntity.ok().build();
    }
}
