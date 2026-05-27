package com.learnhub.review.application;

import com.learnhub.review.application.dto.ReviewRequest;
import com.learnhub.review.application.dto.ReviewResponse;
import com.learnhub.review.domain.Review;
import com.learnhub.review.domain.ReviewRepository;
import com.learnhub.shared.domain.exception.DuplicateResourceException;
import com.learnhub.shared.domain.exception.ResourceNotFoundException;
import com.learnhub.user.domain.UserRepository;
import com.learnhub.purchase.domain.PurchaseRepository;
import com.learnhub.purchase.domain.PaymentStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         UserRepository userRepository,
                         PurchaseRepository purchaseRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public ReviewResponse create(Long usuarioId, ReviewRequest request) {
        if (reviewRepository.existsByUsuarioIdAndCursoId(usuarioId, request.cursoId())) {
            throw new DuplicateResourceException("Ya has reseñado este curso");
        }

        boolean haComprado = purchaseRepository.findByUsuarioId(usuarioId).stream()
            .anyMatch(p -> p.getIdCurso().equals(request.cursoId())
                && p.getEstadoPago() == PaymentStatus.COMPLETADO);

        if (!haComprado) {
            throw new IllegalStateException("Debes comprar el curso antes de reseñarlo");
        }

        var review = new Review(usuarioId, request.cursoId(), request.puntuacion(), request.comentario());
        return toResponse(reviewRepository.save(review));
    }

    public ReviewResponse update(Long id, Long usuarioId, ReviewRequest request) {
        var review = reviewRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reseña", id));

        if (!review.getIdUsuario().equals(usuarioId)) {
            throw new IllegalStateException("No puedes editar una reseña que no es tuya");
        }

        review.setPuntuacion(request.puntuacion());
        review.setComentario(request.comentario());
        return toResponse(reviewRepository.save(review));
    }

    public void delete(Long id, Long usuarioId, boolean isAdmin) {
        var review = reviewRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reseña", id));

        if (!isAdmin && !review.getIdUsuario().equals(usuarioId)) {
            throw new IllegalStateException("No puedes eliminar una reseña que no es tuya");
        }

        reviewRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> findByCurso(Long cursoId) {
        return reviewRepository.findByCursoId(cursoId).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Double averageByCurso(Long cursoId) {
        var reviews = reviewRepository.findByCursoId(cursoId);
        return reviews.stream().mapToInt(Review::getPuntuacion).average().orElse(0.0);
    }

    private ReviewResponse toResponse(Review review) {
        String nombreUsuario = userRepository.findById(review.getIdUsuario())
            .map(u -> u.getNombre() + " " + u.getApellidos())
            .orElse("Usuario");

        return new ReviewResponse(
            review.getId(),
            review.getIdUsuario(),
            nombreUsuario,
            review.getIdCurso(),
            review.getPuntuacion(),
            review.getComentario(),
            review.getFecha()
        );
    }
}
