package com.learnhub.review.infrastructure.rest;

import com.learnhub.review.application.ReviewService;
import com.learnhub.review.application.dto.ReviewRequest;
import com.learnhub.review.application.dto.ReviewResponse;
import com.learnhub.user.domain.User;
import com.learnhub.user.domain.UserRole;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/resenas")
    public ResponseEntity<ReviewResponse> create(@AuthenticationPrincipal User user,
                                                  @Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(reviewService.create(user.getId(), request));
    }

    @PutMapping("/resenas/{id}")
    public ResponseEntity<ReviewResponse> update(@AuthenticationPrincipal User user,
                                                  @PathVariable Long id,
                                                  @Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.update(id, user.getId(), request));
    }

    @DeleteMapping("/resenas/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal User user,
                                       @PathVariable Long id) {
        boolean isAdmin = user.getRol() == UserRole.ADMIN;
        reviewService.delete(id, user.getId(), isAdmin);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cursos/{cursoId}/resenas")
    public ResponseEntity<List<ReviewResponse>> findByCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(reviewService.findByCurso(cursoId));
    }

    @GetMapping("/cursos/{cursoId}/resenas/media")
    public ResponseEntity<Double> averageByCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(reviewService.averageByCurso(cursoId));
    }
}
